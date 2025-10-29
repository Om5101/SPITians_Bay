package com.spitbay.dao;

import com.spitbay.database.DatabaseConnection;
import com.spitbay.model.PGListing;
import com.spitbay.util.SecurityUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Optimized DAO for PG Listing operations
public class PGListingDAO {
    private final DatabaseConnection dbConnection;
    
    public PGListingDAO() {
        this.dbConnection = DatabaseConnection.getInstance(); // Use singleton instance
    }
    
    // Insert new PG listing
    public boolean insertPGListing(PGListing listing) {
        String sql = "INSERT INTO pg_listings (uid, aadhar, address, rent, distance, area, " +
                    "vacancies, beds_per_room, gender, notes) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, listing.getOwnerUid());
            stmt.setString(2, SecurityUtil.encryptData(listing.getAadhar()));
            stmt.setString(3, listing.getAddress());
            stmt.setInt(4, listing.getRent());
            stmt.setInt(5, listing.getDistance());
            stmt.setInt(6, listing.getAreaCode());
            stmt.setInt(7, listing.getVacancies());
            stmt.setInt(8, listing.getBedsPerRoom());
            stmt.setString(9, listing.getGender());
            stmt.setString(10, listing.getNotes());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        listing.setId(generatedKeys.getInt(1));
                        insertAmenities(listing.getId(), listing.getAmenities());
                    }
                }
            }
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Database error inserting PG listing", e);
        }
    }
    
    // Find PG listings by owner
    public List<PGListing> findPGListingsByOwner(String uid) {
        String sql = "SELECT * FROM pg_listings WHERE uid = ?";
        List<PGListing> listings = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, uid);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PGListing listing = createPGListingFromResultSet(rs);
                    if (listing != null) {
                        listings.add(listing);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error finding PG listings by owner", e);
        }
        
        return listings;
    }
    
    // Find PG listings by gender
    public List<PGListing> findPGListingsByGender(String gender) {
        String sql = "SELECT * FROM pg_listings WHERE gender = ?";
        List<PGListing> listings = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, gender);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PGListing listing = createPGListingFromResultSet(rs);
                    if (listing != null) {
                        listings.add(listing);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error finding PG listings by gender", e);
        }
        
        return listings;
    }
    
    // Insert amenities for PG listing
    private void insertAmenities(int pgId, List<String> amenities) {
        String sql = "INSERT INTO pg_amenities (pg_id, amenity) VALUES (?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            for (String amenity : amenities) {
                if (amenity != null && !amenity.trim().isEmpty()) {
                    stmt.setInt(1, pgId);
                    stmt.setString(2, amenity.trim());
                    stmt.addBatch();
                }
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("Database error inserting amenities", e);
        }
    }
    
    // Get amenities for PG listing
    private List<String> getAmenitiesForPG(int pgId) {
        String sql = "SELECT amenity FROM pg_amenities WHERE pg_id = ?";
        List<String> amenities = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, pgId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    amenities.add(rs.getString("amenity"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error fetching amenities", e);
        }
        
        return amenities;
    }
    
    // Create PGListing object from ResultSet
    private PGListing createPGListingFromResultSet(ResultSet rs) throws SQLException {
        String ownerUid = rs.getString("uid");
        // Note: encryptedAadhar is retrieved but we use placeholder for display security
        String address = rs.getString("address");
        int rent = rs.getInt("rent");
        int distance = rs.getInt("distance");
        int area = rs.getInt("area");
        int vacancies = rs.getInt("vacancies");
        int bedsPerRoom = rs.getInt("beds_per_room");
        String gender = rs.getString("gender");
        String notes = rs.getString("notes");
        
        // Use placeholder for Aadhar display since it's encrypted in DB
        String displayAadhar = "ENCRYPTED_AADHAR";
        
        PGListing listing = new PGListing(ownerUid, displayAadhar, address, rent, distance, 
                                        area, new ArrayList<>(), vacancies, bedsPerRoom, gender, notes);
        listing.setId(rs.getInt("id"));
        listing.setAmenities(getAmenitiesForPG(listing.getId()));
        
        return listing;
    }
}
