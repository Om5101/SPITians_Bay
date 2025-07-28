package service;

import dao.PGListingDAO;
import dao.PGListingDAOImpl;
import model.PGListing;

import java.sql.SQLException;
import java.util.List;

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
    
    public PGListing getPGListingByUid(String uid) {
        return pgListingDAO.getPGListingByUid(uid);
    }
    
    public List<PGListing> getAllPGListings() {
        return pgListingDAO.getAllPGListings();
    }
    
    public double calculateMatchScore(PGListing pg, double rentWeight, double distanceWeight, 
                                    double areaWeight, double amenitiesWeight, double bedsWeight,
                                    int preferredRent, int preferredDistance, String preferredArea,
                                    int preferredBeds) {
        double score = 0.0;
        
        // Rent score (lower is better)
        double rentScore = 1.0 - Math.abs(pg.getRent() - preferredRent) / (double) preferredRent;
        score += rentScore * rentWeight;
        
        // Distance score (lower is better)
        double distanceScore = 1.0 - Math.abs(pg.getDistance() - preferredDistance) / (double) preferredDistance;
        score += distanceScore * distanceWeight;
        
        // Area score (exact match)
        if (pg.getArea().equals(preferredArea)) {
            score += areaWeight;
        }
        
        // Beds score (exact match)
        if (pg.getBeds() == preferredBeds) {
            score += bedsWeight;
        }
        
        return score;
    }
} 