package com.spitbay.model;

import java.util.Objects;

// Senior student who can post PG listings and blogs
public class Senior extends User {
    
    public Senior(String uid, String password) {
        super(uid, password);
    }
    
    @Override
    public String toString() {
        return "Senior [UID: " + getUid() + "]";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Senior senior = (Senior) obj;
        return Objects.equals(getUid(), senior.getUid());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getUid());
    }
}
