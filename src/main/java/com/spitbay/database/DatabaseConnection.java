package com.spitbay.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Simple optimized database connection manager
public class DatabaseConnection {
    private static final String URL = System.getenv("DB_URL") != null ? 
        System.getenv("DB_URL") : "jdbc:mysql://localhost:3306/spit_bay";
    private static final String USER = System.getenv("DB_USER") != null ? 
        System.getenv("DB_USER") : "spitbay";
    private static final String PASS = System.getenv("DB_PASS") != null ? 
        System.getenv("DB_PASS") : "spitbay";
    
    // Singleton instance
    private static DatabaseConnection instance;
    
    // Static block to load driver once
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL driver not found", e);
        }
    }
    
    // Private constructor for singleton
    private DatabaseConnection() {}
    
    // Get singleton instance
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    // Get database connection (simple and optimized)
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}