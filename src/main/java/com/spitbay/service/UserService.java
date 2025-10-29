package com.spitbay.service;

import com.spitbay.dao.SeniorDAO;
import com.spitbay.dao.UIDDAO;
import com.spitbay.model.Senior;
import com.spitbay.util.SecurityUtil;

// Service for user authentication and registration
public class UserService {
    private final SeniorDAO seniorDAO;
    private final UIDDAO uidDAO;
    
    public UserService(SeniorDAO seniorDAO, UIDDAO uidDAO) {
        this.seniorDAO = seniorDAO;
        this.uidDAO = uidDAO;
    }
    
    // Register new senior user with BCrypt hashing and uniqueness check
    public boolean registerUser(Senior senior) {
        if (!senior.isValid() || !SecurityUtil.isValidUid(senior.getUid())) {
            throw new IllegalArgumentException("Invalid user data");
        }
        
        // Check if UID is in the valid UIDs list
        if (!uidDAO.isValidUid(senior.getUid())) {
            throw new IllegalArgumentException("UID not authorized for registration");
        }
        
        // Check if user already exists
        if (userExists(senior.getUid())) {
            throw new IllegalArgumentException("User with this UID already exists");
        }
        
        return seniorDAO.insertSenior(senior);
    }
    
    // Authenticate user login with BCrypt verification
    public Senior authenticateUser(String uid, String password) {
        if (uid == null || uid.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return null;
        }
        
        // Normalize UID to lowercase for consistency
        String normalizedUid = uid.trim().toLowerCase();
        
        if (!SecurityUtil.isValidUid(normalizedUid)) {
            return null;
        }
        
        String storedHashedPassword = seniorDAO.getPasswordHash(normalizedUid);
        if (storedHashedPassword != null && SecurityUtil.verifyPassword(password, storedHashedPassword)) {
            return new Senior(normalizedUid, ""); // Return with empty password for security
        }
        
        return null;
    }
    
    // Check if user exists
    public boolean userExists(String uid) {
        return seniorDAO.exists(uid);
    }
}
