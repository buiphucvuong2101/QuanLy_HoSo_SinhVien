package com.mycompany.quanlyhososinhvien.view;

import com.mycompany.quanlyhososinhvien.model.User;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;

public class MainFrame extends JFrame {

    private SinhVienPanel sinhVienPanel;
    private KhoaPanel khoaPanel;
    private LopPanel lopPanel;
    private SearchPanel searchPanel;
    private StatisticsPanel statsPanel;
    private UserPanel userPanel;

    // Biến static để nhớ trạng thái theme
    private static boolean isDarkMode = false;

    public MainFrame(User user) {
        setTitle("Quản lý hồ sơ sinh viên - " + user.getHoTen() 
                + " (" + user.getRole() + ")");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== 1. KHỞI TẠO CÁC PANEL =====
        sinhVienPanel = new SinhVienPanel(user);
        khoaPanel = new KhoaPanel(user);
        lopPanel = new LopPanel(user);
        searchPanel = new SearchPanel();
        statsPanel = new StatisticsPanel();
        userPanel = new UserPanel(user);

        // ===== 2. TẠO TABBEDPANE =====
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Sinh viên", sinhVienPanel);
        tabs.addTab("Khoa", khoaPanel);
        tabs.addTab("Lớp", lopPanel);
        tabs.addTab("Tìm kiếm", searchPanel);
        tabs.addTab("Thống kê", statsPanel);

        // ⭐ CHỈ HIỂN THỊ TAB TÀI KHOẢN NẾU LÀ ADMIN
        if ("admin".equals(user.getRole())) {
            tabs.addTab("Tài khoản", userPanel);
        }

        // ⭐ Tự động reload khi chuyển tab
        tabs.addChangeListener((ChangeEvent e) -> {
            int idx = tabs.getSelectedIndex();
            switch (idx) {
                case 0 -> { if (sinhVienPanel != null) sinhVienPanel.reloadData(); }
                case 1 -> { if (khoaPanel != null) khoaPanel.reloadData(); }
                case 2 -> { if (lopPanel != null) lopPanel.reloadData(); }
                case 3 -> { if (searchPanel != null) searchPanel.reloadData(); }
                case 4 -> { if (statsPanel != null) statsPanel.reloadData(); }
                case 5 -> { if (userPanel != null) userPanel.reloadData(); }
            }
        });

        add(tabs, BorderLayout.CENTER);

        // ===== 3. TẠO MENU HỆ THỐNG =====
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Hệ thống");


        // 3.2. Thông tin tài khoản
        JMenuItem mniThongTin = new JMenuItem("Thông tin tài khoản");
        mniThongTin.addActionListener(e -> {
            String info = "Tên đăng nhập: " + user.getUsername() + "\n"
                        + "Họ và tên: " + user.getHoTen() + "\n"
                        + "Quyền hạn: " + ("admin".equals(user.getRole()) ? "Quản trị viên" : "Người dùng");
            JOptionPane.showMessageDialog(this, info, "Thông tin tài khoản", JOptionPane.INFORMATION_MESSAGE);
        });

        // 3.3. Đổi mật khẩu
        JMenuItem mniDoiMatKhau = new JMenuItem("Đổi mật khẩu");
        mniDoiMatKhau.addActionListener(e -> {
            ChangePasswordDialog dlg = new ChangePasswordDialog(this, true, user);
            dlg.setLocationRelativeTo(this);
            dlg.setVisible(true);
        });

        // 3.4. Chế độ tối/sáng
        JMenuItem mniTheme = new JMenuItem("Giao diện Tối/Sáng");
        mniTheme.addActionListener(e -> toggleTheme());

        // 3.5. Đăng xuất
        JMenuItem mniLogout = new JMenuItem("Đăng xuất");
        mniLogout.addActionListener(e -> {
            int cf = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (cf == JOptionPane.YES_OPTION) {
                dispose();
                new LoginFrame().setVisible(true);
            }
        });

        // 3.6. Thoát
        JMenuItem mniExit = new JMenuItem("Thoát");
        mniExit.addActionListener(e -> {
            int cf = JOptionPane.showConfirmDialog(this,
                "Bạn có muốn thoát chương trình?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (cf == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Thêm các mục vào menu (Đúng thứ tự)
        fileMenu.add(mniThongTin);
        fileMenu.add(mniDoiMatKhau);
        fileMenu.addSeparator(); 
        fileMenu.add(mniTheme);
        fileMenu.addSeparator();
        fileMenu.add(mniLogout);
        fileMenu.add(mniExit);

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    // ===== PHƯƠNG THỨC ĐỔI GIAO DIỆN =====
    private void toggleTheme() {
        try {
            if (!isDarkMode) {
                UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
                isDarkMode = true;
            } else {
                UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
                isDarkMode = false;
            }
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(this, 
                "Không thể đổi giao diện. Vui lòng kiểm tra thư viện FlatLaf.\n" + ex.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}