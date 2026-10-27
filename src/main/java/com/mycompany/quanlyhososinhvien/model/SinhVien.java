package com.mycompany.quanlyhososinhvien.model;

import java.util.Date;

public class SinhVien {
    private String maSV, hoTen, gioiTinh, diaChi, soDienThoai, email, maLop;
    private Date ngaySinh;

    public SinhVien() {}

    public SinhVien(String maSV, String hoTen, Date ngaySinh, String gioiTinh,
                    String diaChi, String soDienThoai, String email, String maLop) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.maLop = maLop;
    }

    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }
    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMaLop() { return maLop; }
    public void setMaLop(String maLop) { this.maLop = maLop; }

    @Override
    public String toString() { return maSV + " - " + hoTen; }
}