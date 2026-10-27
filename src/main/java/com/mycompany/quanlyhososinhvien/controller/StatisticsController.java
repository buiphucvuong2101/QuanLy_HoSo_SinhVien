package com.mycompany.quanlyhososinhvien.controller;

import com.mycompany.quanlyhososinhvien.database.DBConnection;
import com.mycompany.quanlyhososinhvien.view.StatisticsPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class StatisticsController {

    private final StatisticsPanel view;
    private DefaultTableModel tableModel;

    public StatisticsController(StatisticsPanel view) {
        this.view = view;
        initController();
    }

    private void initController() {
        tableModel = new DefaultTableModel(
            new Object[]{"Mã khoa", "Tên khoa", "Số sinh viên"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        view.getTblThongKe().setModel(tableModel);

        loadThongKe();
    }

    private void loadThongKe() {
        try (Connection con = DBConnection.getConnection()) {
            // Tổng sinh viên
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM sinhvien")) {
                if (rs.next()) view.getLblTongSV().setText("Tổng SV: " + rs.getInt(1));
            }
            // Tổng lớp
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM lop")) {
                if (rs.next()) view.getLblTongLop().setText("Tổng lớp: " + rs.getInt(1));
            }
            // Tổng khoa
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM khoa")) {
                if (rs.next()) view.getLblTongKhoa().setText("Tổng khoa: " + rs.getInt(1));
            }
            // Bảng thống kê theo khoa
            tableModel.setRowCount(0);
            String sql = "SELECT k.maKhoa, k.tenKhoa, COUNT(sv.maSV) AS soSV "
                       + "FROM khoa k LEFT JOIN lop l ON k.maKhoa = l.maKhoa "
                       + "LEFT JOIN sinhvien sv ON l.maLop = sv.maLop "
                       + "GROUP BY k.maKhoa, k.tenKhoa ORDER BY k.maKhoa";
            try (PreparedStatement pst = con.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                        rs.getString("maKhoa"),
                        rs.getString("tenKhoa"),
                        rs.getInt("soSV")
                    });
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage());
        }
    }
    
    public void reload() {
        loadThongKe();
    }
}