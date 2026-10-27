/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.quanlyhososinhvien.view;

import com.mycompany.quanlyhososinhvien.model.User;

/**
 *
 * @author Buiph
 */
public class LopPanel extends javax.swing.JPanel {

    private User currentUser;

    public LopPanel() {
        this(null);
    }

    public LopPanel(User user) {
        this.currentUser = user;
        initComponents();
        new com.mycompany.quanlyhososinhvien.controller.LopController(this, user);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblMaKhoa1 = new javax.swing.JLabel();
        txtMaKhoa1 = new javax.swing.JTextField();
        txtTenKhoa1 = new javax.swing.JTextField();
        lblTenKhoa1 = new javax.swing.JLabel();
        pnTable = new javax.swing.JPanel();
        scrollTable = new javax.swing.JScrollPane();
        tblLop = new javax.swing.JTable();
        pnFilter = new javax.swing.JPanel();
        lblFilterKhoa = new javax.swing.JLabel();
        cboFilterKhoa = new javax.swing.JComboBox<>();
        pnForm = new javax.swing.JPanel();
        lblMaLop = new javax.swing.JLabel();
        txtMaLop = new javax.swing.JTextField();
        txtTenLop = new javax.swing.JTextField();
        lblTenKhoa = new javax.swing.JLabel();
        lblMaKhoa = new javax.swing.JLabel();
        lblKhoaHoc = new javax.swing.JLabel();
        txtKhoaHoc = new javax.swing.JTextField();
        cboMaKhoa = new javax.swing.JComboBox<>();
        pnButtons = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();

        lblMaKhoa1.setText("Mã lớp:");

        lblTenKhoa1.setText("Tên lớp:");

        scrollTable.setViewportView(tblLop);

        lblFilterKhoa.setText("Lọc theo Khoa:");

        javax.swing.GroupLayout pnFilterLayout = new javax.swing.GroupLayout(pnFilter);
        pnFilter.setLayout(pnFilterLayout);
        pnFilterLayout.setHorizontalGroup(
            pnFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnFilterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFilterKhoa)
                .addGap(18, 18, 18)
                .addComponent(cboFilterKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnFilterLayout.setVerticalGroup(
            pnFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnFilterLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(pnFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFilterKhoa)
                    .addComponent(cboFilterKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnTableLayout = new javax.swing.GroupLayout(pnTable);
        pnTable.setLayout(pnTableLayout);
        pnTableLayout.setHorizontalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollTable, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(pnFilter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnTableLayout.setVerticalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTableLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnForm.setLayout(new java.awt.GridBagLayout());

        lblMaLop.setText("Mã lớp:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 76;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 179, 0, 0);
        pnForm.add(lblMaLop, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 193;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 6, 0, 184);
        pnForm.add(txtMaLop, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 193;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 6, 0, 184);
        pnForm.add(txtTenLop, gridBagConstraints);

        lblTenKhoa.setText("Tên lớp:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 74;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 179, 0, 0);
        pnForm.add(lblTenKhoa, gridBagConstraints);

        lblMaKhoa.setText("Khoa:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 86;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 179, 0, 0);
        pnForm.add(lblMaKhoa, gridBagConstraints);

        lblKhoaHoc.setText("Khóa học:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 64;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 179, 26, 0);
        pnForm.add(lblKhoaHoc, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 193;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 6, 26, 184);
        pnForm.add(txtKhoaHoc, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 185;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 6, 0, 184);
        pnForm.add(cboMaKhoa, gridBagConstraints);

        pnButtons.setLayout(new java.awt.GridLayout(1, 0));

        btnThem.setText("Thêm");
        pnButtons.add(btnThem);

        btnSua.setText("Sửa");
        pnButtons.add(btnSua);

        btnXoa.setText("Xóa");
        pnButtons.add(btnXoa);

        btnLamMoi.setText("Làm mới");
        pnButtons.add(btnLamMoi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnForm, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // ========== GETTER CHO CONTROLLER ==========
    public javax.swing.JTable getTblLop() { return tblLop; }
    public javax.swing.JTextField getTxtMaLop() { return txtMaLop; }
    public javax.swing.JTextField getTxtTenLop() { return txtTenLop; }
    public javax.swing.JTextField getTxtKhoaHoc() { return txtKhoaHoc; }
    public javax.swing.JComboBox<String> getCboMaKhoa() { return cboMaKhoa; }
    public javax.swing.JButton getBtnThem() { return btnThem; }
    public javax.swing.JButton getBtnSua() { return btnSua; }
    public javax.swing.JButton getBtnXoa() { return btnXoa; }
    public javax.swing.JButton getBtnLamMoi() { return btnLamMoi; }
    public javax.swing.JComboBox<String> getCboFilterKhoa() { return cboFilterKhoa; }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboFilterKhoa;
    private javax.swing.JComboBox<String> cboMaKhoa;
    private javax.swing.JLabel lblFilterKhoa;
    private javax.swing.JLabel lblKhoaHoc;
    private javax.swing.JLabel lblMaKhoa;
    private javax.swing.JLabel lblMaKhoa1;
    private javax.swing.JLabel lblMaLop;
    private javax.swing.JLabel lblTenKhoa;
    private javax.swing.JLabel lblTenKhoa1;
    private javax.swing.JPanel pnButtons;
    private javax.swing.JPanel pnFilter;
    private javax.swing.JPanel pnForm;
    private javax.swing.JPanel pnTable;
    private javax.swing.JScrollPane scrollTable;
    private javax.swing.JTable tblLop;
    private javax.swing.JTextField txtKhoaHoc;
    private javax.swing.JTextField txtMaKhoa1;
    private javax.swing.JTextField txtMaLop;
    private javax.swing.JTextField txtTenKhoa1;
    private javax.swing.JTextField txtTenLop;
    // End of variables declaration//GEN-END:variables

    void reloadData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
