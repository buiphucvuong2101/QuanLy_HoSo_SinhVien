package com.mycompany.quanlyhososinhvien.controller;

import com.mycompany.quanlyhososinhvien.database.DBConnection;
import com.mycompany.quanlyhososinhvien.view.ForgotDialog;

import javax.swing.*;
import java.sql.*;

public class ForgotController {

    private final ForgotDialog view;

    public ForgotController(ForgotDialog view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // Gắn sự kiện cho 2 nút
        view.getBtnDoiMatKhau().addActionListener(e -> xuLyDoiMatKhau());
        view.getBtnHuy().addActionListener(e -> view.dispose());
    }

    private void xuLyDoiMatKhau() {
        String username = view.getTxtUsername().getText().trim();
        String hoTen = view.getTxtHoTen().getText().trim();
        String newPass = new String(view.getTxtNewPass().getPassword());

        // 1. Validate
        if (username.isEmpty() || hoTen.isEmpty() || newPass.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ!");
            return;
        }
        if (newPass.length() < 6) {
            JOptionPane.showMessageDialog(view, "Mật khẩu phải có ít nhất 6 ký tự!");
            return;
        }

        // 2. Kiểm tra username + hoTen có khớp
        String sqlCheck = "SELECT COUNT(*) FROM users WHERE username = ? AND hoTen = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sqlCheck)) {
            pst.setString(1, username);
            pst.setString(2, hoTen);
            ResultSet rs = pst.executeQuery();
            if (!rs.next() || rs.getInt(1) == 0) {
                JOptionPane.showMessageDialog(view, 
                    "Tên đăng nhập hoặc họ tên không đúng!");
                return;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage());
            return;
        }

        // 3. Cập nhật mật khẩu
        String sqlUpdate = "UPDATE users SET password = ? WHERE username = ? AND hoTen = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sqlUpdate)) {
            pst.setString(1, newPass);
            pst.setString(2, username);
            pst.setString(3, hoTen);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(view, 
                "Đổi mật khẩu thành công! Vui lòng đăng nhập lại.");
            view.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage());
        }
    }
}