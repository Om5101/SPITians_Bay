package dao;

import model.PGListing;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PGListingDAOImpl implements PGListingDAO {
    private Connection connection;
    
    public PGListingDAOImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    
    @Override
    public boolean addPGListing(PGListing pgListing) {
        String query = "INSERT INTO pg_listing (uid, aadhar, address, rent, distance, area, vacancies, beds, notes) " +
                      "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, pgListing.getUid());
            stmt.setString(2, pgListing.getAadhar());
            stmt.setString(3, pgListing.getAddress());
            stmt.setInt(4, pgListing.getRent());
            stmt.setInt(5, pgListing.getDistance());
            stmt.setString(6, pgListing.getArea());
            stmt.setInt(7, pgListing.getVacancies());
            stmt.setInt(8, pgListing.getBeds());
            stmt.setString(9, pgListing.getNotes());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean updatePGListing(PGListing pgListing) {
        String query = "UPDATE pg_listing SET aadhar=?, address=?, rent=?, distance=?, area=?, " +
                      "vacancies=?, beds=?, notes=? WHERE pg_id=? AND uid=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, pgListing.getAadhar());
            stmt.setString(2, pgListing.getAddress());
            stmt.setInt(3, pgListing.getRent());
            stmt.setInt(4, pgListing.getDistance());
            stmt.setString(5, pgListing.getArea());
            stmt.setInt(6, pgListing.getVacancies());
            stmt.setInt(7, pgListing.getBeds());
            stmt.setString(8, pgListing.getNotes());
            stmt.setInt(9, pgListing.getPgId());
            stmt.setString(10, pgListing.getUid());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean deletePGListing(int pgId) {
        String query = "DELETE FROM pg_listing WHERE pg_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, pgId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public PGListing getPGListing(int pgId) {
        String query = "SELECT * FROM pg_listing WHERE pg_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
    public PGListing getPGListingByUid(String uid) {
        String query = "SELECT * FROM pg_listing WHERE uid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, uid);
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
    public List<PGListing> getAllPGListings() {
        List<PGListing> listings = new ArrayList<>();
        String query = "SELECT * FROM pg_listing";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listings.add(extractPGListingFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listings;
    }
    
    private PGListing extractPGListingFromResultSet(ResultSet rs) throws SQLException {
        PGListing pgListing = new PGListing();
        pgListing.setPgId(rs.getInt("pg_id"));
        pgListing.setUid(rs.getString("uid"));
        pgListing.setAadhar(rs.getString("aadhar"));
        pgListing.setAddress(rs.getString("address"));
        pgListing.setRent(rs.getInt("rent"));
        pgListing.setDistance(rs.getInt("distance"));
        pgListing.setArea(rs.getString("area"));
        pgListing.setVacancies(rs.getInt("vacancies"));
        pgListing.setBeds(rs.getInt("beds"));
        pgListing.setNotes(rs.getString("notes"));
        return pgListing;
    }
} 