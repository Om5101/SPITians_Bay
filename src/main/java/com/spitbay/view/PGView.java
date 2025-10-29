package com.spitbay.view;

import com.spitbay.model.PGListing;
import java.util.List;

// Simple view class for PG listing displays
public class PGView {
    
    // Display user's PG listings
    public static void displayUserListings(List<PGListing> listings) {
        if (!listings.isEmpty()) {
            System.out.println("\n=== Your PG Listings ===");
            for (PGListing listing : listings) {
                System.out.println(listing.toString());
            }
        }
    }
    
    // Display PG search results with scores
    public static void displaySearchResults(List<java.util.Map.Entry<PGListing, Double>> scoredListings) {
        if (scoredListings.isEmpty()) {
            System.out.println("No matching PGs found.");
            return;
        }
        
        System.out.println("\n=== Matching PGs (Sorted by Score) ===");
        for (java.util.Map.Entry<PGListing, Double> entry : scoredListings) {
            System.out.printf("Score: %.2f | %s\n", entry.getValue(), entry.getKey());
            System.out.println("----------------------------------------");
        }
    }
    
    // Display all PG listings
    public static void displayAllListings(List<PGListing> listings) {
        if (listings.isEmpty()) {
            System.out.println("No PG listings found.");
            return;
        }
        
        System.out.println("\n=== Available PG Listings ===");
        for (PGListing listing : listings) {
            System.out.println(listing.toString());
            System.out.println("----------------------------------------");
        }
    }
    
    // Display PG listing form
    public static void showAddPGForm() {
        System.out.println("\n=== Add New PG Listing ===");
    }
    
    // Display available amenities
    public static void showAvailableAmenities(String[] amenities) {
        System.out.println("Available Amenities:");
        for (int i = 0; i < amenities.length; i++) {
            System.out.println((i + 1) + ". " + amenities[i]);
        }
    }
    
    // Display PG management menu
    public static void showPGManagement() {
        System.out.println("\n1. Add New PG Listing");
        System.out.println("2. Back to Dashboard");
        System.out.print("Enter choice: ");
    }
    
    // Display search form
    public static void showSearchForm() {
        System.out.println("\n=== Customized PG Search ===");
    }
    
    // Display weight setting instructions
    public static void showWeightInstructions() {
        System.out.println("\nSet importance weights (total must be 100%):");
    }
    
    // Display area preference instructions
    public static void showAreaInstructions() {
        System.out.println("\nSet area preferences (1=highest, 5=lowest):");
    }
    
    // Display amenity preference instructions
    public static void showAmenityInstructions() {
        System.out.println("\nSet amenity priorities (1=highest, 5=lowest, 0=not interested):");
    }
}
