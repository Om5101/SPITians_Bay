package com.spitbay.service;

import com.spitbay.model.PGListing;
import com.spitbay.dao.PGListingDAO;
import com.spitbay.dao.PGListingDAOImpl;
import java.util.List;
import java.sql.SQLException;

public class PGListingService {
    private PGListingDAO pgListingDAO;

    public PGListingService() throws SQLException {
        this.pgListingDAO = new PGListingDAOImpl();
    }

    public boolean addPGListing(PGListing pgListing) {
        return pgListingDAO.addPGListing(pgListing);
    }

    public boolean updatePGListing(PGListing pgListing) {
        return pgListingDAO.updatePGListing(pgListing);
    }

    public boolean deletePGListing(int pgId) {
        return pgListingDAO.deletePGListing(pgId);
    }

    public PGListing getPGListing(int pgId) {
        return pgListingDAO.getPGListing(pgId);
    }

    public List<PGListing> getPGListingByUid(String uid) {
        return pgListingDAO.getPGListingByUid(uid);
    }

    public List<PGListing> getAllPGListings() {
        return pgListingDAO.getAllPGListings();
    }
} 