package com.mycompany.quanlyhososinhvien.controller;

import com.mycompany.quanlyhososinhvien.database.DBConnection;
import com.mycompany.quanlyhososinhvien.view.RegisterDialog;

import javax.swing.*;
import java.sql.*;

public class RegisterController {

    private final RegisterDialog view;

    public RegisterController(RegisterDialog view) {
        this.view = view;
        initController();
        view.getBtnDangKy().addActionListener(e -> xuLyDangKy());
        view.getBtnHuy().addActionListener(e -> view.dispose());
    }

    private void initController() {
        view.getBtnDangKy().addActionListener(e -> xuLyDangKy());
        view.getBtnHuy().addActionListener(e -> view.dispose());
    }

    private void xuLyDangKy() {
        String username = view.getTxtUsername().getText().trim();
        String password = new String(view.getTxtPassword().getPassword());
        String confirm = new String(view.getTxtConfirm().getPassword());
        String hoTen = view.getTxtHoTen().getText().trim();

        // Validate
        if (username.isEmpty() || password.isEmpty() || hoTen.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ!");
            return;
        }
        if (username.length() < 3) {
            JOptionPane.showMessageDialog(view, "Tên đăng nhập phải có ít nhất 3 ký tự!");
            return;
        }
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(view, "Mật khẩu phải có ít nhất 6 ký tự!");
            return;
        }
        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(view, "Mật khẩu xác nhận không khớp!");
            return;
        }

        // Kiểm tra username đã tồn tại chưa
        String sqlCheck = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sqlCheck)) {
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                JOptionPane.showMessageDialog(view, "Tên đăng nhập đã tồn tại!");
                return;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage());
            return;
        }

        // INSERT
        String sql = "INSERT INTO users (username, password, hoTen, role) VALUES (?, ?, ?, 'user')";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, hoTen);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(view, "Đăng ký thành công! Vui lòng đăng nhập.");
            view.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage());
        }
    }
}