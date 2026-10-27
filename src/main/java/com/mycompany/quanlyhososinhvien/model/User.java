package com.mycompany.quanlyhososinhvien.model;

public class User {
    private int id;
    private String username, password, hoTen, role;
    private String maSV;

    public User() {}
    
    public User(int id, String username, String password, String hoTen, String role, String maSV) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.hoTen = hoTen;
        this.role = role;
        this.maSV = maSV;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }
}