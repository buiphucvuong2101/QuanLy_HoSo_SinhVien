package com.mycompany.quanlyhososinhvien.controller;

import com.mycompany.quanlyhososinhvien.database.DBConnection;
import com.mycompany.quanlyhososinhvien.model.User;
import com.mycompany.quanlyhososinhvien.view.LopPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.*;

public class LopController {


    private final LopPanel view;
    private final User currentUser;
    private DefaultTableModel tableModel;

    public LopController(LopPanel view) {
        this(view, null);
    }

    public LopController(LopPanel view, User user) {
        this.view = view;
        this.currentUser = user;
        initController();
        applyPermissions();
    }
     
    private void applyPermissions() {
        if (currentUser == null) return;
        if (!"admin".equals(currentUser.getRole())) {
            view.getBtnThem().setEnabled(false);
            view.getBtnSua().setEnabled(false);
            view.getBtnXoa().setEnabled(false);
        }
    }

    // ===== KHỞI TẠO =====
    private void initController() {
        // 1. Model cho JTable
        tableModel = new DefaultTableModel(
            new Object[]{"Mã lớp", "Tên lớp", "Khoa", "Khóa học"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        view.getTblLop().setModel(tableModel);
        view.getTblLop().setAutoCreateRowSorter(true);

        // 2. Sự kiện nút
        view.getBtnThem().addActionListener(this::btnThemAction);
        view.getBtnSua().addActionListener(this::btnSuaAction);
        view.getBtnXoa().addActionListener(this::btnXoaAction);
        view.getBtnLamMoi().addActionListener(this::btnLamMoiAction);

        // 3. Sự kiện chọn dòng trong bảng
        view.getTblLop().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                dongDuocChon();
            }
        });

        // 4. Sự kiện combo lọc Khoa (bảng) → lọc dữ liệu
        view.getCboFilterKhoa().addActionListener(e -> loadDataFromDB());

        // 5. Load dữ liệu ban đầu
        loadComboBoxKhoa();         // Combo khoa trong form
        loadComboBoxFilterKhoa();   // Combo khoa trong bộ lọc
        loadDataFromDB();           // JTable
    }

    // ===== LOAD COMBO KHOA (trong form nhập) =====
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
    }

    // ===== LOAD COMBO KHOA (trong bộ lọc) =====
    private void loadComboBoxFilterKhoa() {
        view.getCboFilterKhoa().removeAllItems();
        view.getCboFilterKhoa().addItem("-- Tất cả --");
        String sql = "SELECT maKhoa FROM khoa ORDER BY maKhoa";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                view.getCboFilterKhoa().addItem(rs.getString("maKhoa"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi load filter: " + e.getMessage());
        }
    }

    // ===== LOAD DỮ LIỆU LỚP LÊN BẢNG (CÓ LỌC) =====
    private void loadDataFromDB() {
        tableModel.setRowCount(0);

        // Lấy khoa đang lọc
        Object filterObj = view.getCboFilterKhoa().getSelectedItem();
        String filterKhoa = null;
        if (filterObj != null && !filterObj.toString().startsWith("--")) {
            filterKhoa = filterObj.toString();
        }

        String sql;
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement pst;
            if (filterKhoa == null) {
                sql = "SELECT maLop, tenLop, maKhoa, khoaHoc FROM lop ORDER BY maKhoa, maLop";
                pst = con.prepareStatement(sql);
            } else {
                sql = "SELECT maLop, tenLop, maKhoa, khoaHoc FROM lop "
                    + "WHERE maKhoa = ? ORDER BY maLop";
                pst = con.prepareStatement(sql);
                pst.setString(1, filterKhoa);
            }

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                        rs.getString("maLop"),
                        rs.getString("tenLop"),
                        rs.getString("maKhoa"),
                        rs.getString("khoaHoc")
                    });
                }
            }
            pst.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi load: " + e.getMessage());
        }
    }

    // ===== CHỌN DÒNG → ĐIỀN FORM =====
    private void dongDuocChon() {
        int row = view.getTblLop().getSelectedRow();
        if (row < 0) return;

        // Nếu bảng đang sắp xếp → convert row index
        int modelRow = view.getTblLop().convertRowIndexToModel(row);

        view.getTxtMaLop().setText(tableModel.getValueAt(modelRow, 0).toString());
        view.getTxtTenLop().setText(tableModel.getValueAt(modelRow, 1).toString());
        view.getCboMaKhoa().setSelectedItem(tableModel.getValueAt(modelRow, 2).toString());

        Object khoaHoc = tableModel.getValueAt(modelRow, 3);
        view.getTxtKhoaHoc().setText(khoaHoc == null ? "" : khoaHoc.toString());
    }

    // ===== NÚT THÊM =====
    private void btnThemAction(ActionEvent e) {
        if (!validate()) return;

        String sql = "INSERT INTO lop (maLop, tenLop, maKhoa, khoaHoc) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            setParams(pst);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(view, "Thêm lớp thành công!");
            loadDataFromDB();
            lamMoi();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    // ===== NÚT SỬA =====
    private void btnSuaAction(ActionEvent e) {
        int row = view.getTblLop().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn lớp cần sửa!");
            return;
        }
        if (!validate()) return;

        int modelRow = view.getTblLop().convertRowIndexToModel(row);
        String maCu = tableModel.getValueAt(modelRow, 0).toString();

        String sql = "UPDATE lop SET maLop=?, tenLop=?, maKhoa=?, khoaHoc=? WHERE maLop=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            setParams(pst);
            pst.setString(5, maCu);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(view, "Sửa lớp thành công!");
            loadDataFromDB();
            lamMoi();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    // ===== NÚT XÓA =====
    private void btnXoaAction(ActionEvent e) {
        int row = view.getTblLop().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn lớp cần xóa!");
            return;
        }

        int modelRow = view.getTblLop().convertRowIndexToModel(row);
        String maLop = tableModel.getValueAt(modelRow, 0).toString();

        int cf = JOptionPane.showConfirmDialog(view, "Xóa lớp " + maLop + "?",
                "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (cf != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM lop WHERE maLop = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, maLop);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(view, "Xóa lớp thành công!");
            loadDataFromDB();
            lamMoi();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    // ===== NÚT LÀM MỚI =====
    private void btnLamMoiAction(ActionEvent e) {
        lamMoi();
        view.getTblLop().clearSelection();
        // Reset combo lọc về "Tất cả"
        view.getCboFilterKhoa().setSelectedIndex(0);
    }

    private void lamMoi() {
        view.getTxtMaLop().setText("");
        view.getTxtTenLop().setText("");
        view.getTxtKhoaHoc().setText("");
        if (view.getCboMaKhoa().getItemCount() > 0) {
            view.getCboMaKhoa().setSelectedIndex(0);
        }
        view.getTxtMaLop().requestFocus();
    }

    // ===== VALIDATE =====
    private boolean validate() {
        if (view.getTxtMaLop().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập Mã lớp!");
            return false;
        }
        if (view.getTxtTenLop().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập Tên lớp!");
            return false;
        }
        if (view.getCboMaKhoa().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn Khoa!");
            return false;
        }
        return true;
    }

    // ===== SET PARAMS =====
    private void setParams(PreparedStatement pst) throws SQLException {
        pst.setString(1, view.getTxtMaLop().getText().trim());
        pst.setString(2, view.getTxtTenLop().getText().trim());
        pst.setString(3, view.getCboMaKhoa().getSelectedItem().toString());
        pst.setString(4, view.getTxtKhoaHoc().getText().trim());
    }
    
    public void reload() {
        loadComboBoxKhoa();
        loadComboBoxFilterKhoa();
        loadDataFromDB();
    }
}