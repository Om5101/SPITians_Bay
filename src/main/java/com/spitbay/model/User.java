package com.spitbay.model;

import com.spitbay.util.SecurityUtil;

// Base user model with common properties
public abstract class User {
    private String uid;
    private String password;
    
    public User(String uid, String password) {
        // Normalize UID to lowercase for consistency
        this.uid = uid != null ? uid.trim().toLowerCase() : null;
        this.password = password;
    }
    
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        // Normalize UID to lowercase for consistency
        this.uid = uid != null ? uid.trim().toLowerCase() : null;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    // Validate user credentials with strict rules
    public boolean isValid() {
        return SecurityUtil.isValidUid(uid) && 
               password != null && !password.trim().isEmpty() && password.length() >= 6;
    }
}
