package com.mycompany.quanlyhososinhvien.controller;

import com.mycompany.quanlyhososinhvien.database.DBConnection;
import com.mycompany.quanlyhososinhvien.model.User;
import com.mycompany.quanlyhososinhvien.view.ChangePasswordDialog;

import javax.swing.*;
import java.sql.*;

public class ChangePasswordController {

    private final ChangePasswordDialog view;
    private final User currentUser;

    public ChangePasswordController(ChangePasswordDialog view, User user) {
        this.view = view;
        this.currentUser = user;
        initController();
    }

    private void initController() {
        view.getBtnDoi().addActionListener(e -> doiMatKhau());
        view.getBtnHuy().addActionListener(e -> view.dispose());
    }

    private void doiMatKhau() {
        String oldPass = new String(view.getTxtOldPass().getPassword());
        String newPass = new String(view.getTxtNewPass().getPassword());
        String confirm = new String(view.getTxtConfirm().getPassword());

        if (oldPass.isEmpty() || newPass.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ!");
            return;
        }
        if (!newPass.equals(confirm)) {
            JOptionPane.showMessageDialog(view, "Mật khẩu xác nhận không khớp!");
            return;
        }
        if (newPass.length() < 6) {
            JOptionPane.showMessageDialog(view, "Mật khẩu mới phải từ 6 ký tự!");
            return;
        }

        // 1. Kiểm tra mật khẩu cũ
        String sqlCheck = "SELECT COUNT(*) FROM users WHERE id = ? AND password = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sqlCheck)) {
            pst.setInt(1, currentUser.getId());
            pst.setString(2, oldPass);
            ResultSet rs = pst.executeQuery();
            if (!rs.next() || rs.getInt(1) == 0) {
                JOptionPane.showMessageDialog(view, "Mật khẩu cũ không đúng!");
                return;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage());
            return;
        }

        // 2. Cập nhật mật khẩu mới
        String sqlUpdate = "UPDATE users SET password = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sqlUpdate)) {
            pst.setString(1, newPass);
            pst.setInt(2, currentUser.getId());
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(view, "Đổi mật khẩu thành công!\nVui lòng đăng nhập lại.");
            view.dispose();
            
            // Đăng xuất
            System.exit(0); // Hoặc quay về LoginFrame tùy logic của bạn
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage());
        }
    }
}