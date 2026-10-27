package com.mycompany.quanlyhososinhvien.controller;

import com.mycompany.quanlyhososinhvien.database.DBConnection;
import com.mycompany.quanlyhososinhvien.model.User;
import com.mycompany.quanlyhososinhvien.view.SinhVienPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SinhVienController {

    private final SinhVienPanel view;
    private final User currentUser;
    private DefaultTableModel tableModel;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public SinhVienController(SinhVienPanel view) {
        this(view, null);
    }

    public SinhVienController(SinhVienPanel view, User user) {
        this.view = view;
        this.currentUser = user;
        initController();
    }

    // ===== KHỞI TẠO =====
    private void initController() {
        // 1. Model JTable
        tableModel = new DefaultTableModel(
            new Object[]{"Mã SV", "Họ tên", "Ngày sinh", "Giới tính",
                         "Địa chỉ", "SĐT", "Email", "Lớp"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        view.getTblSinhVien().setModel(tableModel);
        view.getTblSinhVien().setAutoCreateRowSorter(true);

        // 2. Sự kiện nút
        view.getBtnThem().addActionListener(this::btnThemAction);
        view.getBtnSua().addActionListener(this::btnSuaAction);
        view.getBtnXoa().addActionListener(this::btnXoaAction);
        view.getBtnLamMoi().addActionListener(this::btnLamMoiAction);

        // 3. Sự kiện chọn dòng
        view.getTblSinhVien().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                dongDuocChon();
            }
        });

        // 4. Combo Khoa (form) → load lại combo Lớp theo khoa
        view.getCboMaKhoa().addActionListener(e -> khoaFormSelected());

        // 5. Combo Giới tính (form) → khi chọn "Khác..." thì hiện dialog
        view.getCboGioiTinh().addActionListener(e -> gioiTinhSelected());

        // 6. Sự kiện combo lọc
        if (view.getCboFilterKhoa() != null) {
            view.getCboFilterKhoa().addActionListener(e -> onFilterKhoaChanged());
        }
        if (view.getCboFilterLop() != null) {
            view.getCboFilterLop().addActionListener(e -> loadDataFromDB());
        }
        if (view.getCboFilterGioiTinh() != null) {
            view.getCboFilterGioiTinh().addActionListener(e -> loadDataFromDB());
        }
        if (view.getBtnXoaLoc() != null) {
            view.getBtnXoaLoc().addActionListener(e -> resetFilter());
        }

        // 7. Load dữ liệu ban đầu
        loadComboBoxKhoa();
        loadComboBoxFilterKhoa();
        loadComboBoxFilterLop();
        loadComboBoxFilterGioiTinh();
        loadDataFromDB();

        // 8. Phân quyền
        applyPermissions();
        
        // 9. Sự kiện nút Xuất Excel
        if (view.getBtnExport() != null) {
            view.getBtnExport().addActionListener(this::btnExportAction);
        }
    }

    // ===== PHÂN QUYỀN =====
    private void applyPermissions() {
        if (currentUser == null) return;
        if (!"admin".equals(currentUser.getRole())) {
            view.getBtnThem().setEnabled(false);
            view.getBtnSua().setEnabled(false);
            view.getBtnXoa().setEnabled(false);
            view.getBtnThem().setToolTipText("Chỉ Admin mới được thêm!");
            view.getBtnSua().setToolTipText("Chỉ Admin mới được sửa!");
            view.getBtnXoa().setToolTipText("Chỉ Admin mới được xóa!");
        }
    }

    // ===== KHI CHỌN KHOA (FORM) → LOAD LẠI LỚP THEO KHOA =====
    private void khoaFormSelected() {
        Object khoa = view.getCboMaKhoa().getSelectedItem();
        if (khoa == null) {
            view.getCboMaLop().removeAllItems();
            return;
        }

        String maKhoa = khoa.toString();
        view.getCboMaLop().removeAllItems();

        String sql = "SELECT maLop FROM lop WHERE maKhoa = ? ORDER BY maLop";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, maKhoa);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                view.getCboMaLop().addItem(rs.getString("maLop"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi load lớp: " + e.getMessage());
        }
    }

    // ===== KHI CHỌN GIỚI TÍNH "Khác..." → HIỆN DIALOG NHẬP =====
    private void gioiTinhSelected() {
        Object selected = view.getCboGioiTinh().getSelectedItem();
        if (selected == null) return;

        // Chỉ xử lý khi chọn "Khác..."
        if (selected.toString().equals("Khác...")) {
            String input = JOptionPane.showInputDialog(
                view,
                "Nhập giới tính khác (Gay, Les, Không tiết lộ...):",
                "Thêm giới tính",
                JOptionPane.QUESTION_MESSAGE
            );

            if (input != null && !input.trim().isEmpty()) {
                String gtMoi = input.trim();

                // Kiểm tra đã có trong combo chưa
                boolean exists = false;
                for (int i = 0; i < view.getCboGioiTinh().getItemCount(); i++) {
                    if (view.getCboGioiTinh().getItemAt(i).equals(gtMoi)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    view.getCboGioiTinh().addItem(gtMoi);
                }
                view.getCboGioiTinh().setSelectedItem(gtMoi);
            } else {
                // Hủy hoặc để trống → quay về "Nam"
                view.getCboGioiTinh().setSelectedItem("Nam");
            }
        }
    }

    // ===== LOAD COMBO KHOA (FORM) =====
    private void loadComboBoxKhoa() {
        view.getCboMaKhoa().removeAllItems();
        String sql = "SELECT maKhoa FROM khoa ORDER BY maKhoa";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                view.getCboMaKhoa().addItem(rs.getString("maKhoa"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi load khoa: " + e.getMessage());
        }

        // Chọn khoa đầu tiên → listener tự load lớp
        if (view.getCboMaKhoa().getItemCount() > 0) {
            view.getCboMaKhoa().setSelectedIndex(0);
        }
    }

    // ===== LOAD COMBO FILTER KHOA =====
    private void loadComboBoxFilterKhoa() {
        if (view.getCboFilterKhoa() == null) return;
        view.getCboFilterKhoa().removeAllItems();
        view.getCboFilterKhoa().addItem("Tất cả");
        String sql = "SELECT maKhoa FROM khoa ORDER BY maKhoa";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                view.getCboFilterKhoa().addItem(rs.getString("maKhoa"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ===== LOAD COMBO FILTER LỚP =====
    private void loadComboBoxFilterLop() {
        if (view.getCboFilterLop() == null) return;
        view.getCboFilterLop().removeAllItems();
        view.getCboFilterLop().addItem("Tất cả");
        String sql = "SELECT maLop FROM lop ORDER BY maLop";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                view.getCboFilterLop().addItem(rs.getString("maLop"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ===== LOAD COMBO FILTER GIỚI TÍNH (DISTINCT từ DB) =====
    private void loadComboBoxFilterGioiTinh() {
        if (view.getCboFilterGioiTinh() == null) return;

        // Lưu giá trị đang chọn
        Object currentValue = view.getCboFilterGioiTinh().getSelectedItem();

        view.getCboFilterGioiTinh().removeAllItems();
        view.getCboFilterGioiTinh().addItem("Tất cả");

        String sql = "SELECT DISTINCT gioiTinh FROM sinhvien "
                   + "WHERE gioiTinh IS NOT NULL AND gioiTinh <> '' "
                   + "ORDER BY gioiTinh";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                view.getCboFilterGioiTinh().addItem(rs.getString("gioiTinh"));
            }
        } catch (SQLException e) {
        }

        // Khôi phục giá trị đang chọn nếu còn
        if (currentValue != null) {
            for (int i = 0; i < view.getCboFilterGioiTinh().getItemCount(); i++) {
                if (view.getCboFilterGioiTinh().getItemAt(i).equals(currentValue.toString())) {
                    view.getCboFilterGioiTinh().setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    // ===== KHI CHỌN KHOA TRONG FILTER → LỌC LẠI LỚP FILTER =====
    private void onFilterKhoaChanged() {
        if (view.getCboFilterKhoa() == null || view.getCboFilterLop() == null) return;

        Object filterKhoa = view.getCboFilterKhoa().getSelectedItem();
        if (filterKhoa == null) return;

        view.getCboFilterLop().removeAllItems();
        view.getCboFilterLop().addItem("Tất cả");

        String sql;
        if (filterKhoa.toString().equals("Tất cả")) {
            sql = "SELECT maLop FROM lop ORDER BY maLop";
        } else {
            sql = "SELECT maLop FROM lop WHERE maKhoa = ? ORDER BY maLop";
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            if (!filterKhoa.toString().equals("Tất cả")) {
                pst.setString(1, filterKhoa.toString());
            }
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                view.getCboFilterLop().addItem(rs.getString("maLop"));
            }
        } catch (SQLException e) {
        }

        loadDataFromDB();
    }

    // ===== LOAD DỮ LIỆU (CÓ FILTER) =====
    private void loadDataFromDB() {
        tableModel.setRowCount(0);

        Object fKhoa = view.getCboFilterKhoa() != null ?
                      view.getCboFilterKhoa().getSelectedItem() : null;
        Object fLop = view.getCboFilterLop() != null ?
                      view.getCboFilterLop().getSelectedItem() : null;
        Object fGioiTinh = view.getCboFilterGioiTinh() != null ?
                           view.getCboFilterGioiTinh().getSelectedItem() : null;

        StringBuilder sql = new StringBuilder(
            "SELECT sv.maSV, sv.hoTen, sv.ngaySinh, sv.gioiTinh, " +
            "sv.diaChi, sv.soDienThoai, sv.email, sv.maLop " +
            "FROM sinhvien sv LEFT JOIN lop l ON sv.maLop = l.maLop WHERE 1=1"
        );

        if (fKhoa != null && !fKhoa.toString().equals("Tất cả")) {
            sql.append(" AND l.maKhoa = ?");
        }
        if (fLop != null && !fLop.toString().equals("Tất cả")) {
            sql.append(" AND sv.maLop = ?");
        }
        if (fGioiTinh != null && !fGioiTinh.toString().equals("Tất cả")) {
            sql.append(" AND sv.gioiTinh = ?");
        }
        // Sắp xếp theo Tên (từ cuối cùng trong chuỗi họ tên), sau đó đến Họ
        // Hàm REVERSE giúp đảo ngược chuỗi, CHARINDEX tìm khoảng trắng đầu tiên, từ đó lấy ra Tên.
        sql.append(" ORDER BY sv.maLop, "
                 + "RIGHT(sv.hoTen, CHARINDEX(' ', REVERSE(sv.hoTen) + ' ') - 1), "
                 + "sv.hoTen");
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql.toString())) {

            int idx = 1;
            if (fKhoa != null && !fKhoa.toString().equals("Tất cả"))
                pst.setString(idx++, fKhoa.toString());
            if (fLop != null && !fLop.toString().equals("Tất cả"))
                pst.setString(idx++, fLop.toString());
            if (fGioiTinh != null && !fGioiTinh.toString().equals("Tất cả"))
                pst.setString(idx++, fGioiTinh.toString());

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("maSV"),
                    rs.getString("hoTen"),
                    rs.getDate("ngaySinh") == null ? "" : sdf.format(rs.getDate("ngaySinh")),
                    rs.getString("gioiTinh"),
                    rs.getString("diaChi"),
                    rs.getString("soDienThoai"),
                    rs.getString("email"),
                    rs.getString("maLop")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi load data: " + e.getMessage());
        }
    }

    // ===== RESET FILTER =====
    private void resetFilter() {
        if (view.getCboFilterKhoa() != null) view.getCboFilterKhoa().setSelectedIndex(0);
        if (view.getCboFilterLop() != null) view.getCboFilterLop().setSelectedIndex(0);
        if (view.getCboFilterGioiTinh() != null) view.getCboFilterGioiTinh().setSelectedIndex(0);
        loadDataFromDB();
    }

    // ===== CHỌN DÒNG =====
    private void dongDuocChon() {
        int row = view.getTblSinhVien().getSelectedRow();
        if (row < 0) return;

        int modelRow = view.getTblSinhVien().convertRowIndexToModel(row);

        view.getTxtMaSV().setText(tableModel.getValueAt(modelRow, 0).toString());
        view.getTxtHoTen().setText(tableModel.getValueAt(modelRow, 1).toString());

        // Ngày sinh
        try {
            String ngay = tableModel.getValueAt(modelRow, 2).toString();
            if (!ngay.isEmpty()) {
                view.getDateChooserNgaySinh().setDate(sdf.parse(ngay));
            } else {
                view.getDateChooserNgaySinh().setDate(null);
            }
        } catch (ParseException e) {
            view.getDateChooserNgaySinh().setDate(null);
        }

        // Giới tính - thêm vào combo nếu chưa có
        Object gtObj = tableModel.getValueAt(modelRow, 3);
        if (gtObj != null) {
            String gt = gtObj.toString();
            boolean exists = false;
            for (int i = 0; i < view.getCboGioiTinh().getItemCount(); i++) {
                if (view.getCboGioiTinh().getItemAt(i).equals(gt)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                view.getCboGioiTinh().addItem(gt);
            }
            view.getCboGioiTinh().setSelectedItem(gt);
        }

        view.getTxtDiaChi().setText(tableModel.getValueAt(modelRow, 4).toString());
        view.getTxtSoDienThoai().setText(tableModel.getValueAt(modelRow, 5).toString());
        view.getTxtEmail().setText(tableModel.getValueAt(modelRow, 6).toString());

        // Lớp + Khoa: set Khoa trước → listener tự load Lớp → sau đó set Lớp
        Object maLopObj = tableModel.getValueAt(modelRow, 7);
        if (maLopObj != null) {
            String maLopStr = maLopObj.toString();
            String maKhoa = getMaKhoaCuaLop(maLopStr);
            if (maKhoa != null && view.getCboMaKhoa().getItemCount() > 0) {
                view.getCboMaKhoa().setSelectedItem(maKhoa);
            }
            view.getCboMaLop().setSelectedItem(maLopStr);
        }
    }

    // ===== LẤY MÃ KHOA CỦA LỚP =====
    private String getMaKhoaCuaLop(String maLop) {
        String sql = "SELECT maKhoa FROM lop WHERE maLop = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, maLop);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) return rs.getString("maKhoa");
        } catch (SQLException e) {
        }
        return null;
    }

    // ===== NÚT THÊM =====
    private void btnThemAction(ActionEvent e) {
        if (!validate()) return;

        String sql = "INSERT INTO sinhvien (maSV, hoTen, ngaySinh, gioiTinh, diaChi, soDienThoai, email, maLop) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            setParams(pst);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(view, "Thêm thành công!");
            loadComboBoxFilterGioiTinh();   // Cập nhật bộ lọc giới tính
            loadDataFromDB();
            lamMoiForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    // ===== NÚT SỬA =====
    private void btnSuaAction(ActionEvent e) {
        int row = view.getTblSinhVien().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn SV cần sửa!");
            return;
        }
        if (!validate()) return;

        int modelRow = view.getTblSinhVien().convertRowIndexToModel(row);
        String maCu = tableModel.getValueAt(modelRow, 0).toString();

        String sql = "UPDATE sinhvien SET maSV=?, hoTen=?, ngaySinh=?, gioiTinh=?, "
                   + "diaChi=?, soDienThoai=?, email=?, maLop=? WHERE maSV=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            setParams(pst);
            pst.setString(9, maCu);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(view, "Sửa thành công!");
            loadComboBoxFilterGioiTinh();   // Cập nhật bộ lọc giới tính
            loadDataFromDB();
            lamMoiForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    // ===== NÚT XÓA =====
    private void btnXoaAction(ActionEvent e) {
        int row = view.getTblSinhVien().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn SV cần xóa!");
            return;
        }

        int modelRow = view.getTblSinhVien().convertRowIndexToModel(row);
        String maSV = tableModel.getValueAt(modelRow, 0).toString();

        int cf = JOptionPane.showConfirmDialog(view, "Xóa SV " + maSV + "?",
                "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (cf != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM sinhvien WHERE maSV = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, maSV);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(view, "Xóa thành công!");
            loadComboBoxFilterGioiTinh();   // Cập nhật bộ lọc giới tính
            loadDataFromDB();
            lamMoiForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    // ===== NÚT LÀM MỚI =====
    private void btnLamMoiAction(ActionEvent e) {
        lamMoiForm();
        view.getTblSinhVien().clearSelection();
    }

    private void lamMoiForm() {
        view.getTxtMaSV().setText("");
        view.getTxtHoTen().setText("");
        view.getDateChooserNgaySinh().setDate(null);
        view.getCboGioiTinh().setSelectedItem("Nam");   // Reset về Nam
        view.getTxtDiaChi().setText("");
        view.getTxtSoDienThoai().setText("");
        view.getTxtEmail().setText("");
        if (view.getCboMaKhoa().getItemCount() > 0) {
            view.getCboMaKhoa().setSelectedIndex(0);
        }
        view.getCboMaLop().removeAllItems();
        view.getTxtMaSV().requestFocus();
    }

    // ===== VALIDATE =====
    private boolean validate() {
        if (view.getTxtMaSV().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập Mã SV!");
            return false;
        }
        if (view.getTxtHoTen().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập Họ tên!");
            return false;
        }
        if (view.getCboMaKhoa().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn Khoa!");
            return false;
        }
        if (view.getCboMaLop().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(view, "Khoa này chưa có lớp hoặc bạn chưa chọn Lớp!");
            return false;
        }
        return true;
    }
    
    public void reload() {
        loadComboBoxKhoa();       // Reload khoa
        loadComboBoxFilterKhoa();
        loadComboBoxFilterLop();
        loadComboBoxFilterGioiTinh();
        loadDataFromDB();         // Reload bảng
    }
    
    // ===== SET PARAMS =====
    private void setParams(PreparedStatement pst) throws SQLException {
        pst.setString(1, view.getTxtMaSV().getText().trim());
        pst.setString(2, view.getTxtHoTen().getText().trim());

        java.util.Date utilDate = view.getDateChooserNgaySinh().getDate();
        if (utilDate != null) {
            pst.setDate(3, new java.sql.Date(utilDate.getTime()));
        } else {
            pst.setNull(3, Types.DATE);
        }

        // Giới tính: lấy từ combo (không editable)
        Object gt = view.getCboGioiTinh().getSelectedItem();
        pst.setString(4, gt == null ? "" : gt.toString().trim());

        pst.setString(5, view.getTxtDiaChi().getText().trim());
        pst.setString(6, view.getTxtSoDienThoai().getText().trim());
        pst.setString(7, view.getTxtEmail().getText().trim());

        Object maLop = view.getCboMaLop().getSelectedItem();
        pst.setString(8, maLop == null ? null : maLop.toString());
    }
    
    // =========================================================
    // TÍNH NĂNG XUẤT EXCEL
    // =========================================================
    private void btnExportAction(ActionEvent e) {
        // 1. Mở hộp thoại chọn nơi lưu file
        javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
        fileChooser.setDialogTitle("Lưu file Excel");
        fileChooser.setSelectedFile(new java.io.File("DanhSachSinhVien.xlsx"));

        int userSelection = fileChooser.showSaveDialog(view);
        if (userSelection != javax.swing.JFileChooser.APPROVE_OPTION) {
            return; // Người dùng bấm Hủy
        }

        java.io.File fileToSave = fileChooser.getSelectedFile();
        // Đảm bảo đuôi file là .xlsx
        if (!fileToSave.getName().endsWith(".xlsx")) {
            fileToSave = new java.io.File(fileToSave.getAbsolutePath() + ".xlsx");
        }

        // 2. Lấy dữ liệu hiện tại trên bảng (đã qua lọc)
        int rowCount = tableModel.getRowCount();
        int colCount = tableModel.getColumnCount();

        // 3. Tạo file Excel bằng Apache POI
        try (org.apache.poi.xssf.usermodel.XSSFWorkbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook()) {
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Danh sách sinh viên");

            // --- Tạo Header (Tiêu đề cột) ---
            org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);
            // Tạo Style cho Header (In đậm)
            org.apache.poi.ss.usermodel.CellStyle headerStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            for (int i = 0; i < colCount; i++) {
                org.apache.poi.ss.usermodel.Cell cell = headerRow.createCell(i);
                cell.setCellValue(tableModel.getColumnName(i));
                cell.setCellStyle(headerStyle);
            }

            // --- Đổ dữ liệu vào các dòng ---
            for (int i = 0; i < rowCount; i++) {
                org.apache.poi.ss.usermodel.Row row = sheet.createRow(i + 1);
                for (int j = 0; j < colCount; j++) {
                    Object value = tableModel.getValueAt(i, j);
                    row.createCell(j).setCellValue(value == null ? "" : value.toString());
                }
            }

            // Tự động giãn cột cho vừa với nội dung
            for (int i = 0; i < colCount; i++) {
                sheet.autoSizeColumn(i);
            }

            // 4. Ghi ra file
            try (java.io.FileOutputStream out = new java.io.FileOutputStream(fileToSave)) {
                workbook.write(out);
            }

            JOptionPane.showMessageDialog(view, 
                    "Xuất file Excel thành công tại:\n" + fileToSave.getAbsolutePath(), 
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, 
                    "Lỗi khi xuất file: " + ex.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}