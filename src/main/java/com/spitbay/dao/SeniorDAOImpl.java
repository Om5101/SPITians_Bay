package com.spitbay.dao;

import com.spitbay.model.Senior;
import com.spitbay.util.DatabaseConnection;
import java.sql.*;

public class SeniorDAOImpl implements SeniorDAO {
    private Connection connection;

    public SeniorDAOImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean register(Senior senior) {
        String sql = "INSERT INTO seniors (uid, password) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, senior.getUid());
            stmt.setString(2, senior.getPassword());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Senior login(String uid, String password) {
        String sql = "SELECT * FROM seniors WHERE uid = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, uid);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Senior(rs.getString("uid"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updatePassword(String uid, String newPassword) {
        String sql = "UPDATE seniors SET password = ? WHERE uid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, uid);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAccount(String uid) {
        String sql = "DELETE FROM seniors WHERE uid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, uid);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
} 