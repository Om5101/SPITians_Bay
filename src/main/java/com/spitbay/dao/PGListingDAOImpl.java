package com.spitbay.dao;

import com.spitbay.model.PGListing;
import com.spitbay.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PGListingDAOImpl implements PGListingDAO {
    private Connection connection;

    public PGListingDAOImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean addPGListing(PGListing pgListing) {
        String sql = "INSERT INTO pg_listings (uid, aadhar, address, rent, distance, area, vacancies, beds_per_room, gender, notes) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, pgListing.getUid());
            stmt.setString(2, pgListing.getAadhar());
            stmt.setString(3, pgListing.getAddress());
            stmt.setInt(4, pgListing.getRent());
            stmt.setInt(5, pgListing.getDistance());
            stmt.setInt(6, pgListing.getArea());
            stmt.setInt(7, pgListing.getVacancies());
            stmt.setInt(8, pgListing.getBedsPerRoom());
            stmt.setString(9, pgListing.getGender());
            stmt.setString(10, pgListing.getNotes());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int pgId = rs.getInt(1);
                        pgListing.setId(pgId);
                        addAmenities(pgId, pgListing.getAmenities());
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updatePGListing(PGListing pgListing) {
        String sql = "UPDATE pg_listings SET address = ?, rent = ?, distance = ?, area = ?, " +
                    "vacancies = ?, beds_per_room = ?, gender = ?, notes = ? WHERE id = ? AND uid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pgListing.getAddress());
            stmt.setInt(2, pgListing.getRent());
            stmt.setInt(3, pgListing.getDistance());
            stmt.setInt(4, pgListing.getArea());
            stmt.setInt(5, pgListing.getVacancies());
            stmt.setInt(6, pgListing.getBedsPerRoom());
            stmt.setString(7, pgListing.getGender());
            stmt.setString(8, pgListing.getNotes());
            stmt.setInt(9, pgListing.getId());
            stmt.setString(10, pgListing.getUid());
            
            if (stmt.executeUpdate() > 0) {
                deleteAmenities(pgListing.getId());
                addAmenities(pgListing.getId(), pgListing.getAmenities());
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePGListing(int pgId) {
        String sql = "DELETE FROM pg_listings WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pgId);
            deleteAmenities(pgId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PGListing getPGListing(int pgId) {
        String sql = "SELECT * FROM pg_listings WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pgId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractPGListingFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PGListing> getPGListingByUid(String uid) {
        List<PGListing> listings = new ArrayList<>();
        String sql = "SELECT * FROM pg_listings WHERE uid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, uid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listings.add(extractPGListingFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listings;
    }

    @Override
    public List<PGListing> getAllPGListings() {
        List<PGListing> listings = new ArrayList<>();
        String sql = "SELECT * FROM pg_listings";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                listings.add(extractPGListingFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listings;
    }

    private PGListing extractPGListingFromResultSet(ResultSet rs) throws SQLException {
        PGListing pgListing = new PGListing(
            rs.getString("uid"),
            rs.getString("aadhar"),
            rs.getString("address"),
            rs.getInt("rent"),
            rs.getInt("distance"),
            rs.getInt("area"),
            getAmenities(rs.getInt("id")),
            rs.getInt("vacancies"),
            rs.getInt("beds_per_room"),
            rs.getString("notes"),
            rs.getString("gender")
        );
        pgListing.setId(rs.getInt("id"));
        return pgListing;
    }

    private void addAmenities(int pgId, List<String> amenities) throws SQLException {
        String sql = "INSERT INTO pg_amenities (pg_id, amenity) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (String amenity : amenities) {
                stmt.setInt(1, pgId);
                stmt.setString(2, amenity);
                stmt.executeUpdate();
            }
        }
    }

    private void deleteAmenities(int pgId) throws SQLException {
        String sql = "DELETE FROM pg_amenities WHERE pg_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pgId);
            stmt.executeUpdate();
        }
    }

    private List<String> getAmenities(int pgId) {
        List<String> amenities = new ArrayList<>();
        String sql = "SELECT amenity FROM pg_amenities WHERE pg_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pgId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                amenities.add(rs.getString("amenity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amenities;
    }
}