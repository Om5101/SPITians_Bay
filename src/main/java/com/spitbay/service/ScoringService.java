package com.spitbay.service;

import com.spitbay.model.PGListing;
import com.spitbay.model.SearchPreferences;
import java.util.List;
import java.util.Map;

// Service for calculating PG listing scores based on preferences
public class ScoringService {
    private static final String[] AREA_NAMES = {"JP Road", "Malad", "Versova", "DN Nagar"};
    private static final String[] AMENITY_NAMES = {"WiFi", "Laundry", "AC", "Food", "Parking", "Security"};
    
    // Calculate total score for PG listing
    public double calculateScore(PGListing listing, SearchPreferences preferences) {
        double rentScore = calculateRentScore(listing.getRent(), preferences);
        double distanceScore = calculateDistanceScore(listing.getDistance(), preferences);
        double amenityScore = calculateAmenityScore(listing.getAmenities(), preferences);
        double areaScore = calculateAreaScore(listing.getAreaCode(), preferences);
        
        return rentScore + distanceScore + amenityScore + areaScore;
    }
    
    // Calculate rent-based score
    private double calculateRentScore(int listingRent, SearchPreferences preferences) {
        int budget = preferences.getBudget();
        if (budget <= 0 || listingRent <= 0) return 0;
        
        double weight = preferences.getWeights().getOrDefault("rent", 25) / 100.0;
        
        if (listingRent <= budget) {
            double ratio = (double) listingRent / budget;
            return (1.0 - ratio * 0.5) * weight * 100;
        } else {
            double excess = (double) (listingRent - budget) / budget;
            return Math.max(0, (1.0 - excess) * weight * 100);
        }
    }
    
    // Calculate distance-based score
    private double calculateDistanceScore(int listingDistance, SearchPreferences preferences) {
        int maxDistance = preferences.getMaxDistance();
        if (maxDistance <= 0 || listingDistance < 0) return 0;
        
        double weight = preferences.getWeights().getOrDefault("distance", 25) / 100.0;
        
        if (listingDistance <= maxDistance) {
            double ratio = (double) listingDistance / maxDistance;
            return (1.0 - ratio * 0.3) * weight * 100;
        } else {
            return 0;
        }
    }
    
    // Calculate amenity-based score
    private double calculateAmenityScore(List<String> listingAmenities, SearchPreferences preferences) {
        Map<String, Integer> amenityPriorities = preferences.getAmenityPriorities();
        if (amenityPriorities.isEmpty() || listingAmenities == null) return 0;
        
        double weight = preferences.getWeights().getOrDefault("amenities", 25) / 100.0;
        
        // Count matching amenities
        int matchingAmenities = 0;
        for (int i = 0; i < listingAmenities.size(); i++) {
            String amenity = listingAmenities.get(i);
            if (amenityPriorities.containsKey(amenity)) {
                Integer priority = amenityPriorities.get(amenity);
                if (priority != null && priority > 0) {
                    matchingAmenities++;
                }
            }
        }
        
        // Count total preferred amenities
        int totalPreferred = 0;
        for (Map.Entry<String, Integer> entry : amenityPriorities.entrySet()) {
            if (entry.getValue() > 0) {
                totalPreferred++;
            }
        }
        
        if (totalPreferred == 0) return 0;
        return ((double) matchingAmenities / totalPreferred) * weight * 100;
    }
    
    // Calculate area-based score
    private double calculateAreaScore(int areaCode, SearchPreferences preferences) {
        Map<String, Integer> areaPriorities = preferences.getAreaPriorities();
        if (areaPriorities.isEmpty()) return 0;
        
        double weight = preferences.getWeights().getOrDefault("area", 25) / 100.0;
        String areaName = getAreaName(areaCode);
        Integer priority = areaPriorities.get(areaName);
        
        if (priority == null || priority <= 0) return 0;
        
        // Priority 1 = highest, 5 = lowest
        // Formula ensures higher priority = higher score
        return ((6 - priority) / 5.0) * weight * 100;
    }
    
    private String getAreaName(int areaCode) {
        if (areaCode >= 1 && areaCode <= AREA_NAMES.length) {
            return AREA_NAMES[areaCode - 1];
        }
        return "Unknown";
    }
    
    // Get area names for UI
    public String[] getAreaNames() {
        return AREA_NAMES.clone();
    }
    
    // Get amenity names for UI
    public String[] getAmenityNames() {
        return AMENITY_NAMES.clone();
    }
}
