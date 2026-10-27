package com.mycompany.quanlyhososinhvien.controller;

import com.mycompany.quanlyhososinhvien.database.DBConnection;
import com.mycompany.quanlyhososinhvien.view.SearchPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.SimpleDateFormat;

public class SearchController {

    private final SearchPanel view;
    private DefaultTableModel tableModel;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public SearchController(SearchPanel view) {
        this.view = view;
        initController();
    }

    private void initController() {
        tableModel = new DefaultTableModel(
            new Object[]{"Mã SV", "Họ tên", "Ngày sinh", "Giới tính",
                         "Địa chỉ", "SĐT", "Email", "Lớp"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        view.getTblKetQua().setModel(tableModel);

        view.getBtnTim().addActionListener(this::btnTimAction);
        view.getBtnReset().addActionListener(this::btnResetAction);

        loadComboBoxKhoa();
        loadComboBoxLop();
        loadAll();
    }

    private void loadComboBoxKhoa() {
        view.getCboKhoaFilter().removeAllItems();
        view.getCboKhoaFilter().addItem("-- Tất cả --");
        String sql = "SELECT maKhoa FROM khoa ORDER BY maKhoa";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                view.getCboKhoaFilter().addItem(rs.getString("maKhoa"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private void loadComboBoxLop() {
        view.getCboLopFilter().removeAllItems();
        view.getCboLopFilter().addItem("-- Tất cả --");
        String sql = "SELECT maLop FROM lop ORDER BY maLop";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                view.getCboLopFilter().addItem(rs.getString("maLop"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    private void loadAll() {
        tableModel.setRowCount(0);
        String sql = "SELECT * FROM sinhvien ORDER BY maSV";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) addRow(rs);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage());
        }
    }

    private void btnTimAction(ActionEvent e) {
        tableModel.setRowCount(0);
        String keyword = view.getTxtKeyword().getText().trim();
        String maKhoa = (String) view.getCboKhoaFilter().getSelectedItem();
        String maLop = (String) view.getCboLopFilter().getSelectedItem();

        StringBuilder sql = new StringBuilder(
            "SELECT sv.* FROM sinhvien sv JOIN lop l ON sv.maLop = l.maLop WHERE 1=1");
        if (!keyword.isEmpty()) sql.append(" AND (sv.maSV LIKE ? OR sv.hoTen LIKE ?)");
        if (maKhoa != null && !maKhoa.equals("-- Tất cả --")) sql.append(" AND l.maKhoa = ?");
        if (maLop != null && !maLop.equals("-- Tất cả --")) sql.append(" AND sv.maLop = ?");

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql.toString())) {
            int idx = 1;
            if (!keyword.isEmpty()) {
                pst.setString(idx++, "%" + keyword + "%");
                pst.setString(idx++, "%" + keyword + "%");
            }
            if (maKhoa != null && !maKhoa.equals("-- Tất cả --")) pst.setString(idx++, maKhoa);
            if (maLop != null && !maLop.equals("-- Tất cả --")) pst.setString(idx++, maLop);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) addRow(rs);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    private void btnResetAction(ActionEvent e) {
        view.getTxtKeyword().setText("");
        view.getCboKhoaFilter().setSelectedIndex(0);
        view.getCboLopFilter().setSelectedIndex(0);
        loadAll();
    }

    private void addRow(ResultSet rs) throws SQLException {
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
    
    public void reload() {
        loadComboBoxKhoa();
        loadComboBoxLop();
        loadAll();  // hoặc loadDataFromDB()
    }
}