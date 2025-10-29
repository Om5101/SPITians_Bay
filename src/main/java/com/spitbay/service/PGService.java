package com.spitbay.service;

import com.spitbay.dao.PGListingDAO;
import com.spitbay.model.PGListing;
import com.spitbay.util.SecurityUtil;
import java.util.List;

// Service for PG listing operations
public class PGService {
    private final PGListingDAO pgListingDAO;
    
    public PGService(PGListingDAO pgListingDAO) {
        this.pgListingDAO = pgListingDAO;
    }
    
    // Add new PG listing with encrypted sensitive data
    public boolean addPGListing(PGListing listing) {
        if (!listing.isValid() || !SecurityUtil.isValidAadhar(listing.getAadhar())) {
            throw new IllegalArgumentException("Invalid PG listing data");
        }
        
        return pgListingDAO.insertPGListing(listing);
    }
    
    // Get PG listings by owner
    public List<PGListing> getPGListingsByOwner(String uid) {
        return pgListingDAO.findPGListingsByOwner(uid);
    }
    
    // Get PG listings by gender preference
    public List<PGListing> getPGListingsByGender(String gender) {
        return pgListingDAO.findPGListingsByGender(gender);
    }
}
