package com.mycompany.quanlyhososinhvien.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL =
        "jdbc:sqlserver://localhost:1433;"
      + "databaseName=qlhssv;"
      + "encrypt=true;trustServerCertificate=true;";
    
    // ✅ TÀI KHOẢN SQL SERVER (không phải tài khoản app)
    private static final String USER = "nemchua";
    private static final String PASS = "thanhhoa";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
        }
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            System.out.println("✅ Kết nối thành công: " + con.getCatalog());
        } catch (SQLException e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
    }
}
