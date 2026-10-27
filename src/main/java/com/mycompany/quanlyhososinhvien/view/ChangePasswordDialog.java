package com.mycompany.quanlyhososinhvien.view;


public class ChangePasswordDialog extends javax.swing.JDialog {

    public ChangePasswordDialog(java.awt.Frame parent, boolean modal, com.mycompany.quanlyhososinhvien.model.User user) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        new com.mycompany.quanlyhososinhvien.controller.ChangePasswordController(this, user);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblOldPass = new javax.swing.JLabel();
        txtOldPass = new javax.swing.JPasswordField();
        txtNewPass = new javax.swing.JPasswordField();
        lblNewPass = new javax.swing.JLabel();
        txtConfirm = new javax.swing.JPasswordField();
        lblConfirm = new javax.swing.JLabel();
        btnDoi = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        lblTitle.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 102, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("ĐỔI MẬT KHẨU");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 218;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(lblTitle, gridBagConstraints);

        lblOldPass.setText("Mật khẩu cũ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 38;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 54, 0, 0);
        jPanel1.add(lblOldPass, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 124;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(29, 18, 0, 0);
        jPanel1.add(txtOldPass, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 124;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 0);
        jPanel1.add(txtNewPass, gridBagConstraints);

        lblNewPass.setText("Mật khẩu mới:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 29;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 54, 0, 0);
        jPanel1.add(lblNewPass, gridBagConstraints);

        txtConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConfirmActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 124;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 0);
        jPanel1.add(txtConfirm, gridBagConstraints);

        lblConfirm.setText("Xác nhận mật khẩu:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.ipady = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 54, 0, 0);
        jPanel1.add(lblConfirm, gridBagConstraints);

        btnDoi.setText("Đổi mật khẩu");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 83, 25, 0);
        jPanel1.add(btnDoi, gridBagConstraints);

        btnHuy.setText("Hủy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 52, 25, 0);
        jPanel1.add(btnHuy, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConfirmActionPerformed

    }//GEN-LAST:event_txtConfirmActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoi;
    private javax.swing.JButton btnHuy;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblConfirm;
    private javax.swing.JLabel lblNewPass;
    private javax.swing.JLabel lblOldPass;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPasswordField txtConfirm;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JPasswordField txtOldPass;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JPasswordField getTxtOldPass() { return txtOldPass; }
    public javax.swing.JPasswordField getTxtNewPass() { return txtNewPass; }
    public javax.swing.JPasswordField getTxtConfirm() { return txtConfirm; }
    public javax.swing.JButton getBtnDoi() { return btnDoi; }
    public javax.swing.JButton getBtnHuy() { return btnHuy; }
}
