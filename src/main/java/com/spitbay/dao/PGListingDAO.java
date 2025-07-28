package com.spitbay.dao;

import com.spitbay.model.PGListing;
import java.util.List;

public interface PGListingDAO {
    boolean addPGListing(PGListing pgListing);
    boolean updatePGListing(PGListing pgListing);
    boolean deletePGListing(int pgId);
    PGListing getPGListing(int pgId);
    List<PGListing> getPGListingByUid(String uid);
    List<PGListing> getAllPGListings();
} 