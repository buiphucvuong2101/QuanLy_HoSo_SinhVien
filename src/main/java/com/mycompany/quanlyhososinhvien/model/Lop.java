package com.mycompany.quanlyhososinhvien.model;

public class Lop {
    private String maLop, tenLop, maKhoa, khoaHoc;

    public Lop() {}

    public Lop(String maLop, String tenLop, String maKhoa, String khoaHoc) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.maKhoa = maKhoa;
        this.khoaHoc = khoaHoc;
    }

    public String getMaLop() { return maLop; }
    public void setMaLop(String maLop) { this.maLop = maLop; }
    public String getTenLop() { return tenLop; }
    public void setTenLop(String tenLop) { this.tenLop = tenLop; }
    public String getMaKhoa() { return maKhoa; }
    public void setMaKhoa(String maKhoa) { this.maKhoa = maKhoa; }
    public String getKhoaHoc() { return khoaHoc; }
    public void setKhoaHoc(String khoaHoc) { this.khoaHoc = khoaHoc; }

    @Override
    public String toString() { return maLop + " - " + tenLop; }
}
