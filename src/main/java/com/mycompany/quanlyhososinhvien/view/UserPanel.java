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
public class UserPanel extends javax.swing.JPanel {

    private final User currentUser;
    private final com.mycompany.quanlyhososinhvien.controller.UserController controller;

    /**
     * Creates new form UserPanel
     * @param user
     */
    public UserPanel(User user) {
        this.currentUser = user;
        initComponents();
        controller = new com.mycompany.quanlyhososinhvien.controller.UserController(this, user);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        txtUsername1 = new javax.swing.JTextField();
        lblUsername1 = new javax.swing.JLabel();
        pnTable = new javax.swing.JPanel();
        TableUser = new javax.swing.JScrollPane();
        tblUser = new javax.swing.JTable();
        pnForm = new javax.swing.JPanel();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        lblHoTen = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        cboRole = new javax.swing.JComboBox<>();
        pnButtons = new javax.swing.JPanel();
        btnCapQuyen = new javax.swing.JButton();
        btnHaQuyen = new javax.swing.JButton();
        btnXoaUser = new javax.swing.JButton();

        lblUsername1.setText("jLabel1");

        tblUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TableUser.setViewportView(tblUser);

        javax.swing.GroupLayout pnTableLayout = new javax.swing.GroupLayout(pnTable);
        pnTable.setLayout(pnTableLayout);
        pnTableLayout.setHorizontalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TableUser)
        );
        pnTableLayout.setVerticalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TableUser, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
        );

        pnForm.setLayout(new java.awt.GridBagLayout());

        lblUsername.setText("Username:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 69;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 139, 0, 0);
        pnForm.add(lblUsername, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 267;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 6, 0, 130);
        pnForm.add(txtUsername, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 267;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 6, 0, 130);
        pnForm.add(txtHoTen, gridBagConstraints);

        lblHoTen.setText("Họ và tên:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 139, 0, 0);
        pnForm.add(lblHoTen, gridBagConstraints);

        lblRole.setText("Quyền:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 88;
        gridBagConstraints.ipady = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 139, 15, 0);
        pnForm.add(lblRole, gridBagConstraints);

        cboRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "user", "admin", " " }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 259;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 6, 15, 130);
        pnForm.add(cboRole, gridBagConstraints);

        pnButtons.setLayout(new java.awt.GridBagLayout());

        btnCapQuyen.setText("Cấp quyền Admin");
        btnCapQuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapQuyenActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 82, 6, 0);
        pnButtons.add(btnCapQuyen, gridBagConstraints);

        btnHaQuyen.setText("Hạ quyền User");
        btnHaQuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHaQuyenActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 14;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 109, 6, 0);
        pnButtons.add(btnHaQuyen, gridBagConstraints);

        btnXoaUser.setText("Xóa tài khoản");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 92, 6, 81);
        pnButtons.add(btnXoaUser, gridBagConstraints);

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
                .addComponent(pnForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapQuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapQuyenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapQuyenActionPerformed

    private void btnHaQuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHaQuyenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHaQuyenActionPerformed

    public void reloadData() {
        if (controller != null) {
            controller.reload();
        }
    }
    
    public javax.swing.JTable getTblUser() { return tblUser; }
    public javax.swing.JTextField getTxtUsername() { return txtUsername; }
    public javax.swing.JTextField getTxtHoTen() { return txtHoTen; }
    public javax.swing.JComboBox<String> getCboRole() { return cboRole; }
    public javax.swing.JButton getBtnCapQuyen() { return btnCapQuyen; }
    public javax.swing.JButton getBtnHaQuyen() { return btnHaQuyen; }
    public javax.swing.JButton getBtnXoaUser() { return btnXoaUser; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane TableUser;
    private javax.swing.JButton btnCapQuyen;
    private javax.swing.JButton btnHaQuyen;
    private javax.swing.JButton btnXoaUser;
    private javax.swing.JComboBox<String> cboRole;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JPanel pnButtons;
    private javax.swing.JPanel pnForm;
    private javax.swing.JPanel pnTable;
    private javax.swing.JTable tblUser;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTextField txtUsername1;
    // End of variables declaration//GEN-END:variables
}
