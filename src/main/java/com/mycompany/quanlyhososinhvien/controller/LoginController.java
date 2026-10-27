package com.mycompany.quanlyhososinhvien.controller;

import com.mycompany.quanlyhososinhvien.database.DBConnection;
import com.mycompany.quanlyhososinhvien.model.User;
import com.mycompany.quanlyhososinhvien.view.LoginFrame;
import com.mycompany.quanlyhososinhvien.view.MainFrame;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.*;
import java.util.Base64;
import java.util.Properties;

public class LoginController {

    private final LoginFrame view;
    private static final String CONFIG_FILE = "login_config.properties";

    public LoginController(LoginFrame view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // Các sự kiện cơ bản
        view.getBtnLogin().addActionListener(e -> xuLyDangNhap());
        view.getTxtPassword().addActionListener(e -> xuLyDangNhap());
        
        view.getBtnRegister().addActionListener(e -> moDangKy());
        
        if (view.getLblForgot() != null) {
            view.getLblForgot().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Đổi con trỏ bàn tay
            
            view.getLblForgot().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    moQuenMatKhau();
                }
                @Override
                public void mouseEntered(MouseEvent evt) {
                    view.getLblForgot().setForeground(new Color(0, 85, 212)); // Xanh đậm khi hover
                }
                @Override
                public void mouseExited(MouseEvent evt) {
                    view.getLblForgot().setForeground(new Color(0, 123, 255)); // Trả về xanh nhạt
                }
            });
        }

        // Hiện/Ẩn mật khẩu
        view.getChkShowPass().addActionListener(e -> {
            if (view.getChkShowPass().isSelected()) {
                view.getTxtPassword().setEchoChar((char) 0); // Hiện
            } else {
                view.getTxtPassword().setEchoChar('•');      // Ẩn
            }
        });

        // Load thông tin đăng nhập đã lưu
        loadSavedLogin();
        
        if (view.getLblExit() != null) {
            view.getLblExit().setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 

            view.getLblExit().addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    // Hỏi xác nhận trước khi thoát
                    int cf = JOptionPane.showConfirmDialog(view, 
                            "Bạn có chắc muốn thoát chương trình?", 
                            "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (cf == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    view.getLblExit().setForeground(new java.awt.Color(180, 0, 0)); // Đổi sang đỏ đậm khi hover
                }
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    // Trả về màu ban đầu (giả sử ban đầu là màu xám)
                    view.getLblExit().setForeground(new java.awt.Color(108, 117, 125)); 
                }
            });
        }
    }

    private void xuLyDangNhap() {
        String username = view.getTxtUsername().getText().trim();
        String password = new String(view.getTxtPassword().getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ!");
            return;
        }

        String sql = "SELECT id, username, password, hoTen, role, maSV FROM users WHERE username = ? AND password = ?";
               sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                User user = new User(
                    rs.getInt("id"), rs.getString("username"),
                    rs.getString("password"), rs.getString("hoTen"),
                    rs.getString("role"), rs.getString("maSV") 
                );
                JOptionPane.showMessageDialog(view, "Đăng nhập thành công!");
                
                // ⭐ Lưu thông tin nếu tick "Ghi nhớ đăng nhập"
                if (view.getChkRemember() != null && view.getChkRemember().isSelected()) {
                    saveLogin(username, password);
                } else {
                    deleteSavedLogin();
                }
                
                view.dispose();
                new MainFrame(user).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(view, "Sai tên đăng nhập hoặc mật khẩu!");
                view.getTxtPassword().setText("");
                view.getTxtPassword().requestFocus();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage());
        }
    }
    
    // ===== LƯU THÔNG TIN ĐĂNG NHẬP =====
    private void saveLogin(String username, String password) {
        try (FileOutputStream out = new FileOutputStream(CONFIG_FILE)) {
            Properties props = new Properties();
            props.setProperty("username", username);
            // Mã hóa Base64 (đơn giản)
            props.setProperty("password", Base64.getEncoder().encodeToString(password.getBytes()));
            props.store(out, "Login credentials");
        } catch (Exception e) {
        }
    }

    // ===== ĐỌC THÔNG TIN ĐĂNG NHẬP =====
    private void loadSavedLogin() {
        File f = new File(CONFIG_FILE);
        if (!f.exists()) return;
        
        try (FileInputStream in = new FileInputStream(f)) {
            Properties props = new Properties();
            props.load(in);
            String savedUser = props.getProperty("username");
            String encodedPass = props.getProperty("password");
            
            if (savedUser != null) {
                view.getTxtUsername().setText(savedUser);
                if (encodedPass != null) {
                    String decodedPass = new String(Base64.getDecoder().decode(encodedPass));
                    view.getTxtPassword().setText(decodedPass);
                }
                if (view.getChkRemember() != null) {
                    view.getChkRemember().setSelected(true);
                }
            }
        } catch (Exception e) {
        }
    }

    // ===== XÓA THÔNG TIN ĐĂNG NHẬP =====
    private void deleteSavedLogin() {
        File f = new File(CONFIG_FILE);
        if (f.exists()) f.delete();
    }

    // ===== MỞ DIALOG ĐĂNG KÝ =====
    private void moDangKy() {
        com.mycompany.quanlyhososinhvien.view.RegisterDialog dlg =
            new com.mycompany.quanlyhososinhvien.view.RegisterDialog(view, true);
        dlg.setLocationRelativeTo(view);
        dlg.setVisible(true);
    }

    // ===== MỞ DIALOG QUÊN MẬT KHẨU =====
    private void moQuenMatKhau() {
        com.mycompany.quanlyhososinhvien.view.ForgotDialog dlg =
            new com.mycompany.quanlyhososinhvien.view.ForgotDialog(view, true);
        dlg.setLocationRelativeTo(view);
        dlg.setVisible(true);
    }
}