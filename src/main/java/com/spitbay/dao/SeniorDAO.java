package com.spitbay.dao;

import com.spitbay.database.DatabaseConnection;
import com.spitbay.model.Senior;
import com.spitbay.util.SecurityUtil;
import java.sql.*;

// Optimized DAO for Senior user operations
public class SeniorDAO {
    private final DatabaseConnection dbConnection;
    
    public SeniorDAO() {
        this.dbConnection = DatabaseConnection.getInstance(); // Use singleton instance
    }
    
    // Insert new senior user
    public boolean insertSenior(Senior senior) {
        String sql = "INSERT INTO seniors (uid, password) VALUES (?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String hashedPassword = SecurityUtil.hashPassword(senior.getPassword());
            stmt.setString(1, senior.getUid());
            stmt.setString(2, hashedPassword);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Database error inserting senior", e);
        }
    }
    
    // Find senior by UID
    public Senior findSeniorByUid(String uid) {
        String sql = "SELECT uid, password FROM seniors WHERE uid = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, uid.trim());
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Senior(rs.getString("uid"), ""); // Empty password for security
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error finding senior", e);
        }
        
        return null;
    }
    
    // Check if user exists
    public boolean exists(String uid) {
        String sql = "SELECT COUNT(*) FROM seniors WHERE uid = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, uid);
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error checking user existence", e);
        }
    }
    
    // Get stored password hash for verification
    public String getPasswordHash(String uid) {
        String sql = "SELECT password FROM seniors WHERE uid = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, uid.trim());
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error getting password hash", e);
        }
        
        return null;
    }
}
