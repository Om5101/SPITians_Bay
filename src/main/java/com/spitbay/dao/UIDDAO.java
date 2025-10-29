package com.spitbay.dao;

import com.spitbay.database.DatabaseConnection;
import java.sql.*;

// DAO for UID validation operations
public class UIDDAO {
    private final DatabaseConnection dbConnection;
    
    public UIDDAO() {
        this.dbConnection = DatabaseConnection.getInstance(); // Use singleton instance
    }
    
    // Check if UID exists in valid_uids table
    public boolean isValidUid(String uid) {
        String sql = "SELECT COUNT(*) FROM valid_uids WHERE uid = ?";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, uid.trim().toLowerCase());
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error checking UID validity", e);
        }
    }
    
    // Get all valid UIDs (for admin purposes)
    public java.util.List<String> getAllValidUids() {
        String sql = "SELECT uid FROM valid_uids ORDER BY uid";
        java.util.List<String> validUids = new java.util.ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                validUids.add(rs.getString("uid"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error getting valid UIDs", e);
        }
        
        return validUids;
    }
}
