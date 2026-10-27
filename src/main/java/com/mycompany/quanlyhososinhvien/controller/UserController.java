package com.mycompany.quanlyhososinhvien.controller;

import com.mycompany.quanlyhososinhvien.database.DBConnection;
import com.mycompany.quanlyhososinhvien.model.User;
import com.mycompany.quanlyhososinhvien.view.UserPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class UserController {

    private final UserPanel view;
    private final User currentUser; // ⭐ Thêm biến lưu user đang đăng nhập
    private DefaultTableModel tableModel;

    public UserController(UserPanel view, User user) {
        this.view = view;
        this.currentUser = user;
        initController();
    }

    private void initController() {
        // 1. Khởi tạo bảng
        tableModel = new DefaultTableModel(new Object[]{"ID", "Username", "Họ tên", "Quyền"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho sửa trực tiếp trên bảng
            }
        };
        view.getTblUser().setModel(tableModel);

        // 2. Load dữ liệu ban đầu
        loadDataFromDB();

        // 3. Sự kiện chọn dòng
        view.getTblUser().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                dongDuocChon();
            }
        });

        // 4. Sự kiện các nút
        view.getBtnCapQuyen().addActionListener(e -> thayDoiQuyen("admin"));
        view.getBtnHaQuyen().addActionListener(e -> thayDoiQuyen("user"));
        view.getBtnXoaUser().addActionListener(e -> xoaUser());
    }

    // Load danh sách user từ DB
    private void loadDataFromDB() {
        tableModel.setRowCount(0);
        String sql = "SELECT id, username, hoTen, role FROM users ORDER BY id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("hoTen"),
                    rs.getString("role")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi load dữ liệu: " + e.getMessage());
        }
    }

    // Hiển thị thông tin khi chọn dòng
    private void dongDuocChon() {
        int row = view.getTblUser().getSelectedRow();
        if (row < 0) return;

        int modelRow = view.getTblUser().convertRowIndexToModel(row);

        view.getTxtUsername().setText(tableModel.getValueAt(modelRow, 1).toString());
        view.getTxtHoTen().setText(tableModel.getValueAt(modelRow, 2).toString());
        view.getCboRole().setSelectedItem(tableModel.getValueAt(modelRow, 3).toString());
    }

    // Cấp quyền / Hạ quyền
    private void thayDoiQuyen(String roleMoi) {
        int row = view.getTblUser().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn tài khoản!");
            return;
        }

        int modelRow = view.getTblUser().convertRowIndexToModel(row);
        int id = (int) tableModel.getValueAt(modelRow, 0);
        String username = tableModel.getValueAt(modelRow, 1).toString();
        String roleHienTai = tableModel.getValueAt(modelRow, 3).toString();

        // 🛑 CHẶN 1: Không cho đổi quyền của Super Admin (nhom13)
        if (username.equals("nhom13")) {
            JOptionPane.showMessageDialog(view, 
                "Đây là tài khoản Super Admin (nhom13), không thể thay đổi quyền!", 
                "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 🛑 CHẶN 2: Không cho tự hạ quyền chính mình
        if (username.equals(currentUser.getUsername())) {
            JOptionPane.showMessageDialog(view, "Bạn không thể tự hạ quyền của chính mình!");
            return;
        }

        if (roleHienTai.equals(roleMoi)) {
            JOptionPane.showMessageDialog(view, "Tài khoản này đã có quyền " + roleMoi.toUpperCase() + " rồi!");
            return;
        }

        int cf = JOptionPane.showConfirmDialog(view,
                "Đổi quyền của '" + username + "' thành " + roleMoi.toUpperCase() + "?",
                "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (cf != JOptionPane.YES_OPTION) return;

        String sql = "UPDATE users SET role = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, roleMoi);
            pst.setInt(2, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(view, "Cập nhật quyền thành công!");
            loadDataFromDB();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage());
        }
    }

    // Xóa tài khoản
    private void xoaUser() {
        int row = view.getTblUser().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn tài khoản cần xóa!");
            return;
        }

        int modelRow = view.getTblUser().convertRowIndexToModel(row);
        int id = (int) tableModel.getValueAt(modelRow, 0);
        String username = tableModel.getValueAt(modelRow, 1).toString();

        // 🛑 CHẶN 3: Không cho xóa Super Admin
        if (username.equals("nhom13")) {
            JOptionPane.showMessageDialog(view, 
                "Đây là tài khoản Super Admin (nhom13), không thể xóa!", 
                "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 🛑 CHẶN 4: Không cho tự xóa chính mình
        if (username.equals(currentUser.getUsername())) {
            JOptionPane.showMessageDialog(view, "Bạn không thể tự xóa tài khoản của chính mình!");
            return;
        }

        int cf = JOptionPane.showConfirmDialog(view,
                "Bạn có chắc muốn xóa vĩnh viễn tài khoản '" + username + "'?",
                "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (cf != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(view, "Xóa thành công!");
            loadDataFromDB();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage());
        }
    }

    // Hàm reload (dành cho MainFrame gọi khi chuyển tab)
    public void reload() {
        loadDataFromDB();
    }
}