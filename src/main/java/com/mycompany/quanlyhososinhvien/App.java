package com.mycompany.quanlyhososinhvien;

import com.mycompany.quanlyhososinhvien.view.LoginFrame;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class App {
    public static void main(String[] args) {
        try {
            // 1. Sử dụng FlatLaf (Giao diện phẳng hiện đại)
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");

            // 2. Bo góc mềm mại
            UIManager.put("Component.arc", 15);
            UIManager.put("Button.arc", 20);
            UIManager.put("TextComponent.arc", 10);

            // 3. Font chữ mềm mại (Segoe UI)
            UIManager.put("defaultFont", new Font("Segoe UI", Font.PLAIN, 14));

            // 4. Màu xanh Neon nhạt cho TẤT CẢ các nút bấm
            Color neonGreen = new Color(185, 246, 202); // Mã màu xanh neon pastel
            UIManager.put("Button.background", neonGreen);
            UIManager.put("Button.foreground", new Color(30, 30, 30)); // Chữ màu xám đậm để dễ đọc
            UIManager.put("Button.hoverBackground", new Color(150, 230, 170)); // Đậm hơn 1 xíu khi di chuột
            UIManager.put("Button.pressedBackground", new Color(120, 200, 140));

            // 5. Cấu hình bảng (JTable) rộng rãi, xen kẽ màu
            UIManager.put("Table.rowHeight", 35); // Dòng cao thoáng
            UIManager.put("Table.showHorizontalLines", true);
            UIManager.put("Table.showVerticalLines", false); // Ẩn viền dọc cho hiện đại
            UIManager.put("Table.gridColor", new Color(230, 230, 230)); // Đường kẻ ngang xám nhạt
            UIManager.put("Table.alternateRowColor", new Color(248, 249, 250)); // Màu xen kẽ trắng/xám nhạt
            UIManager.put("Table.selectionBackground", new Color(204, 229, 255)); // Xanh khi chọn
            
            // 6. Cấu hình Header bảng (Tiêu đề cột)
            UIManager.put("TableHeader.background", new Color(50, 50, 50)); // Xám than
            UIManager.put("TableHeader.foreground", Color.WHITE);
            UIManager.put("TableHeader.font", new Font("Segoe UI", Font.BOLD, 14));
            
            // 7. Thanh trượt mềm mại (bo tròn, thu nhỏ)
            UIManager.put("ScrollBar.width", 12); // Thu nhỏ thanh trượt
            UIManager.put("ScrollBar.thumbArc", 999); // Bo tròn 100% thanh kéo
            UIManager.put("ScrollBar.thumbInsets", new java.awt.Insets(2, 2, 2, 2));
            UIManager.put("ScrollBar.track", new Color(245, 245, 245)); // Màu nền thanh trượt

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        }

        // Mở ứng dụng
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}