package com.mycompany.quanlyhososinhvien.controller;

import com.mycompany.quanlyhososinhvien.database.DBConnection;
import com.mycompany.quanlyhososinhvien.model.User;
import com.mycompany.quanlyhososinhvien.view.KhoaPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.*;

public class KhoaController {

    private final KhoaPanel view;
    private final User currentUser;
    private DefaultTableModel tableModel;

    // Constructor cũ (không user)
    public KhoaController(KhoaPanel view) {
        this(view, null);
    }

    // ⭐ Constructor chính - nhận user
    public KhoaController(KhoaPanel view, User user) {
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

    private void initController() {
        tableModel = new DefaultTableModel(new Object[]{"Mã khoa", "Tên khoa"}, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        view.getTblKhoa().setModel(tableModel);

        // ⭐ 4 dòng bắt buộc
        view.getBtnThem().addActionListener(this::btnThemAction);
        view.getBtnSua().addActionListener(this::btnSuaAction);
        view.getBtnXoa().addActionListener(this::btnXoaAction);
        view.getBtnLamMoi().addActionListener(this::btnLamMoiAction);

        view.getTblKhoa().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) dongDuocChon();
        });

        loadDataFromDB();
    }

    private void loadDataFromDB() {
        tableModel.setRowCount(0);
        String sql = "SELECT maKhoa, tenKhoa FROM khoa ORDER BY maKhoa";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("maKhoa"),
                    rs.getString("tenKhoa")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Lỗi load: " + e.getMessage());
        }
    }

    private void dongDuocChon() {
        int row = view.getTblKhoa().getSelectedRow();
        if (row < 0) return;
        view.getTxtMaKhoa().setText(tableModel.getValueAt(row, 0).toString());
        view.getTxtTenKhoa().setText(tableModel.getValueAt(row, 1).toString());
    }

    private void btnThemAction(ActionEvent e) {
        if (!validate()) return;   // ⭐ Kiểm tra dữ liệu nhập

        String sql = "INSERT INTO khoa (maKhoa, tenKhoa) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, view.getTxtMaKhoa().getText().trim());
            pst.setString(2, view.getTxtTenKhoa().getText().trim());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(view, "Thêm thành công!");
            loadDataFromDB();
            lamMoi();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    private void btnSuaAction(ActionEvent e) {
        int row = view.getTblKhoa().getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(view, "Chọn khoa!"); return; }
        if (!validate()) return;

        String maCu = tableModel.getValueAt(row, 0).toString();
        String sql = "UPDATE khoa SET maKhoa=?, tenKhoa=? WHERE maKhoa=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, view.getTxtMaKhoa().getText().trim());
            pst.setString(2, view.getTxtTenKhoa().getText().trim());
            pst.setString(3, maCu);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(view, "Sửa thành công!");
            loadDataFromDB();
            lamMoi();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    private void btnXoaAction(ActionEvent e) {
        int row = view.getTblKhoa().getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(view, "Chọn khoa!"); return; }
        String maKhoa = tableModel.getValueAt(row, 0).toString();
        int cf = JOptionPane.showConfirmDialog(view, "Xóa khoa " + maKhoa + "?",
                "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (cf != JOptionPane.YES_OPTION) return;

        String sql = "DELETE FROM khoa WHERE maKhoa = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, maKhoa);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(view, "Xóa thành công!");
            loadDataFromDB();
            lamMoi();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    private void btnLamMoiAction(ActionEvent e) {
        lamMoi();
        view.getTblKhoa().clearSelection();
    }

    private void lamMoi() {
        view.getTxtMaKhoa().setText("");
        view.getTxtTenKhoa().setText("");
        view.getTxtMaKhoa().requestFocus();
    }

    private boolean validate() {
        if (view.getTxtMaKhoa().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nhập mã khoa!");
            return false;
        }
        if (view.getTxtTenKhoa().getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nhập tên khoa!");
            return false;
        }
        return true;
    }
    
    public void reload() {
        loadDataFromDB();
    }
}