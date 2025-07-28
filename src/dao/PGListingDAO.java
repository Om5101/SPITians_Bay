package dao;

import model.PGListing;
import java.util.List;

public interface PGListingDAO {
    boolean addPGListing(PGListing pgListing);
    boolean updatePGListing(PGListing pgListing);
    boolean deletePGListing(int pgId);
    PGListing getPGListing(int pgId);
    PGListing getPGListingByUid(String uid);
    List<PGListing> getAllPGListings();
} 