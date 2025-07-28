package service;

import dao.SeniorDAO;
import dao.SeniorDAOImpl;
import model.Senior;

import java.sql.SQLException;

public class SeniorService {
    private SeniorDAO seniorDAO;
    
    public SeniorService() throws SQLException {
        this.seniorDAO = new SeniorDAOImpl();
    }
    
    public boolean register(Senior senior) {
        return seniorDAO.register(senior);
    }
    
    public Senior login(String uid, String password) {
        return seniorDAO.login(uid, password);
    }
    
    public boolean updatePassword(String uid, String newPassword) {
        return seniorDAO.updatePassword(uid, newPassword);
    }
    
    public boolean deleteAccount(String uid) {
        return seniorDAO.deleteAccount(uid);
    }
} 