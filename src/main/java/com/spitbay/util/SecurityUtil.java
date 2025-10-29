package com.spitbay.util;

import org.mindrot.jbcrypt.BCrypt;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

// Security utility for encryption and validation
public class SecurityUtil {
    
    // Validate UID - must be email ending with "@spit.ac.in"
    public static boolean isValidUid(String uid) {
        if (uid == null || uid.trim().isEmpty()) {
            return false;
        }
        String trimmedUid = uid.trim().toLowerCase();
        // Must be a valid email format ending with "@spit.ac.in"
        return trimmedUid.matches("^[a-zA-Z0-9._%+-]+@spit\\.ac\\.in$");
    }
    
    // Validate Aadhar - exactly 12 digits only
    public static boolean isValidAadhar(String aadhar) {
        if (aadhar == null || aadhar.trim().isEmpty()) {
            return false;
        }
        return aadhar.matches("^\\d{12}$"); // Only 12 digits
    }
    
    // Validate address - any length but not empty
    public static boolean isValidAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }
    
    // Hash password using BCrypt
    public static String hashPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    
    // Verify password using BCrypt
    public static boolean verifyPassword(String password, String hashedPassword) {
        if (password == null || hashedPassword == null) {
            return false;
        }
        try {
            return BCrypt.checkpw(password, hashedPassword);
        } catch (Exception e) {
            return false;
        }
    }
    
    // Encrypt sensitive data using AES (reversible encryption for non-password data)
    public static String encryptData(String data) {
        if (data == null || data.isEmpty()) {
            return data;
        }
        try {
            SecretKeySpec key = new SecretKeySpec("MySecretKey12345".getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }
    
    // Decrypt sensitive data using AES
    public static String decryptData(String encryptedData) {
        if (encryptedData == null || encryptedData.isEmpty()) {
            return encryptedData;
        }
        try {
            SecretKeySpec key = new SecretKeySpec("MySecretKey12345".getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
}








