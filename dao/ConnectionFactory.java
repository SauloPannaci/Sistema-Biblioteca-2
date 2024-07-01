package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mariadb://localhost:3306/Biblioteca";
    private static final String USER = "root";
    private static final String PASSWORD = "250409";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            
        }
        return null;
    }
}
