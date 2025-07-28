package com.spitbay.dao;

import com.spitbay.model.Senior;

public interface SeniorDAO {
    boolean register(Senior senior);
    Senior login(String uid, String password);
    boolean updatePassword(String uid, String newPassword);
    boolean deleteAccount(String uid);
} 