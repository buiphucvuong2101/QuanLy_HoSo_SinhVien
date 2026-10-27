
package com.mycompany.quanlyhososinhvien.view;

import com.mycompany.quanlyhososinhvien.model.User;

public class SinhVienPanel extends javax.swing.JPanel {

    
    // ⭐ Lưu User để phân quyền
    private User currentUser;

    // ⭐ Constructor rỗng - NetBeans Designer gọi
    public SinhVienPanel() {
        this(null);
    }

    // ⭐ Constructor chính - nhận User
    public SinhVienPanel(User user) {
        this.currentUser = user;    
        initComponents();
        if (user != null) {
            new com.mycompany.quanlyhososinhvien.controller.SinhVienController(this, user);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnForm = new javax.swing.JPanel();
        lblMaSV = new javax.swing.JLabel();
        txtMaSV = new javax.swing.JTextField();
        lblHoTen = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        lblNgaySinh = new javax.swing.JLabel();
        dateChooserNgaySinh = new com.toedter.calendar.JDateChooser();
        lblGioiTinh = new javax.swing.JLabel();
        cboGioiTinh = new javax.swing.JComboBox<>();
        lblDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        lblSoDienThoai = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        lblMaLop = new javax.swing.JLabel();
        lblMaKhoa = new javax.swing.JLabel();
        cboMaLop = new javax.swing.JComboBox<>();
        cboMaKhoa = new javax.swing.JComboBox<>();
        pnTable = new javax.swing.JPanel();
        pnFilter = new javax.swing.JPanel();
        lblFilterKhoa = new javax.swing.JLabel();
        cboFilterKhoa = new javax.swing.JComboBox<>();
        lblFilterLop = new javax.swing.JLabel();
        cboFilterLop = new javax.swing.JComboBox<>();
        cboFilterGioiTinh = new javax.swing.JComboBox<>();
        lblFilterGioiTinh = new javax.swing.JLabel();
        btnXoaLoc = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        ScrollTable = new javax.swing.JScrollPane();
        tblSinhVien = new javax.swing.JTable();
        pnButtons = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();

        lblMaSV.setText("Mã sinh viên:");

        txtMaSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSVActionPerformed(evt);
            }
        });

        lblHoTen.setText("Họ và tên:");

        txtHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenActionPerformed(evt);
            }
        });

        lblNgaySinh.setText("Ngày sinh:");

        dateChooserNgaySinh.setDateFormatString("dd/MM/yyyy");

        lblGioiTinh.setText("Giới tính");

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "Khác:..." }));
        cboGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGioiTinhActionPerformed(evt);
            }
        });

        lblDiaChi.setText("Địa chỉ:");

        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        lblSoDienThoai.setText("Số điện thoại");

        txtSoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDienThoaiActionPerformed(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        lblEmail.setText("Email:");

        lblMaLop.setText("Lớp");

        lblMaKhoa.setText("Khoa");

        cboMaLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaLopActionPerformed(evt);
            }
        });

        cboMaKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaKhoaActionPerformed(evt);
            }
        });

        lblFilterKhoa.setText("Khoa:");

        lblFilterLop.setText("Lớp:");

        cboFilterGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterGioiTinhActionPerformed(evt);
            }
        });

        lblFilterGioiTinh.setText("Giới tính:");

        btnXoaLoc.setText("Xóa lọc");

        btnExport.setBackground(new java.awt.Color(102, 102, 0));
        btnExport.setText("Xuất Excel");

        javax.swing.GroupLayout pnFilterLayout = new javax.swing.GroupLayout(pnFilter);
        pnFilter.setLayout(pnFilterLayout);
        pnFilterLayout.setHorizontalGroup(
            pnFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnFilterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(lblFilterKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboFilterKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFilterLop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboFilterLop, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblFilterGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboFilterGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaLoc)
                .addGap(22, 22, 22))
        );
        pnFilterLayout.setVerticalGroup(
            pnFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFilterLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnFilterLayout.createSequentialGroup()
                        .addComponent(btnExport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnFilterLayout.createSequentialGroup()
                        .addGroup(pnFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblFilterGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboFilterGioiTinh)
                                .addComponent(btnXoaLoc))
                            .addGroup(pnFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboFilterLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblFilterLop, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboFilterKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblFilterKhoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(23, 23, 23))))
        );

        ScrollTable.setBackground(new java.awt.Color(255, 0, 204));

        ScrollTable.setViewportView(tblSinhVien);

        javax.swing.GroupLayout pnTableLayout = new javax.swing.GroupLayout(pnTable);
        pnTable.setLayout(pnTableLayout);
        pnTableLayout.setHorizontalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTableLayout.createSequentialGroup()
                .addComponent(ScrollTable)
                .addContainerGap())
        );
        pnTableLayout.setVerticalGroup(
            pnTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTableLayout.createSequentialGroup()
                .addComponent(pnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ScrollTable, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnFormLayout = new javax.swing.GroupLayout(pnForm);
        pnForm.setLayout(pnFormLayout);
        pnFormLayout.setHorizontalGroup(
            pnFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnFormLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(pnFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnFormLayout.createSequentialGroup()
                        .addComponent(lblMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(lblHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnFormLayout.createSequentialGroup()
                        .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateChooserNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnFormLayout.createSequentialGroup()
                        .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiaChi))
                    .addGroup(pnFormLayout.createSequentialGroup()
                        .addGroup(pnFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(pnFormLayout.createSequentialGroup()
                                .addComponent(lblSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSoDienThoai))
                            .addGroup(pnFormLayout.createSequentialGroup()
                                .addComponent(lblMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(cboMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(54, 54, 54)
                        .addGroup(pnFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnFormLayout.createSequentialGroup()
                                .addComponent(lblMaKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboMaKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnFormLayout.createSequentialGroup()
                                .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        pnFormLayout.setVerticalGroup(
            pnFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFormLayout.createSequentialGroup()
                .addComponent(pnTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(pnFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnFormLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(pnFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(pnFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnFormLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnFormLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnFormLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(dateChooserNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnFormLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnFormLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnFormLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(pnFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMaKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        pnButtons.setLayout(new java.awt.GridLayout(1, 0));

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnButtons.add(btnThem);

        btnSua.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnButtons.add(btnSua);

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnButtons.add(btnXoa);

        btnLamMoi.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        pnButtons.add(btnLamMoi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnButtons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnForm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtMaSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSVActionPerformed

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed

    private void cboGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboGioiTinhActionPerformed

    private void txtSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDienThoaiActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void cboMaLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaLopActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMaLopActionPerformed

    private void cboMaKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaKhoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboMaKhoaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void cboFilterGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboFilterGioiTinhActionPerformed

    // ========== GETTER CHO CONTROLLER ==========
    public javax.swing.JTable getTblSinhVien() { return tblSinhVien; }
    public javax.swing.JTextField getTxtMaSV() { return txtMaSV; }
    public javax.swing.JTextField getTxtHoTen() { return txtHoTen; }
    public com.toedter.calendar.JDateChooser getDateChooserNgaySinh() { return dateChooserNgaySinh; }
    public javax.swing.JComboBox<String> getCboGioiTinh() { return cboGioiTinh; }
    public javax.swing.JTextField getTxtDiaChi() { return txtDiaChi; }
    public javax.swing.JTextField getTxtSoDienThoai() { return txtSoDienThoai; }
    public javax.swing.JTextField getTxtEmail() { return txtEmail; }
    public javax.swing.JComboBox<String> getCboMaKhoa() { return cboMaKhoa; }
    public javax.swing.JComboBox<String> getCboMaLop() { return cboMaLop; }
    public javax.swing.JButton getBtnThem() { return btnThem; }
    public javax.swing.JButton getBtnSua() { return btnSua; }
    public javax.swing.JButton getBtnXoa() { return btnXoa; }
    public javax.swing.JButton getBtnLamMoi() { return btnLamMoi; }
    public javax.swing.JButton getBtnXoaLoc() { return btnXoaLoc; }
    public javax.swing.JButton getBtnExport() { return btnExport; }
    // ⭐ Getter cho bộ lọc
    public javax.swing.JComboBox<String> getCboFilterKhoa() { return cboFilterKhoa; }
    public javax.swing.JComboBox<String> getCboFilterLop() { return cboFilterLop; }
    public javax.swing.JComboBox<String> getCboFilterGioiTinh() { return cboFilterGioiTinh; }
    // ⭐ Getter cho User
    public User getCurrentUser() { return currentUser; }
    // ========== HẾT GETTER ==========
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollTable;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaLoc;
    private javax.swing.JComboBox<String> cboFilterGioiTinh;
    private javax.swing.JComboBox<String> cboFilterKhoa;
    private javax.swing.JComboBox<String> cboFilterLop;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<String> cboMaKhoa;
    private javax.swing.JComboBox<String> cboMaLop;
    private com.toedter.calendar.JDateChooser dateChooserNgaySinh;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblFilterGioiTinh;
    private javax.swing.JLabel lblFilterKhoa;
    private javax.swing.JLabel lblFilterLop;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMaKhoa;
    private javax.swing.JLabel lblMaLop;
    private javax.swing.JLabel lblMaSV;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JPanel pnButtons;
    private javax.swing.JPanel pnFilter;
    private javax.swing.JPanel pnForm;
    private javax.swing.JPanel pnTable;
    private javax.swing.JTable tblSinhVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables

    void reloadData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
