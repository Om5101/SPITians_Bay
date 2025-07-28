package com.spitbay.service;

import com.spitbay.model.PGListing;
import com.spitbay.model.PGPreferences;
import com.spitbay.util.AreaMapper;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class PGMatchmakingService {
    private PGListingService pgListingService;

    public PGMatchmakingService(PGListingService pgListingService) {
        this.pgListingService = pgListingService;
    }

    public List<Map.Entry<PGListing, Double>> findMatchingPGs(PGPreferences preferences) {
        List<PGListing> allPGs = pgListingService.getAllPGListings();
        List<Map.Entry<PGListing, Double>> matchedPGs = new ArrayList<>();

        for (PGListing pg : allPGs) {
            // Skip if gender doesn't match (strict filter)
            if (!pg.getGender().equals(preferences.getGender())) {
                continue;
            }

            double score = calculateMatchScore(pg, preferences);
            if (score > 0) {
                matchedPGs.add(Map.entry(pg, score));
            }
        }

        // Sort by score in descending order
        matchedPGs.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));
        return matchedPGs;
    }

    private double calculateMatchScore(PGListing pg, PGPreferences preferences) {
        double totalScore = 0;

        // Rent score (lower difference is better)
        double rentDiff = Math.abs(pg.getRent() - preferences.getRentBudget());
        double rentScore = Math.max(0, 100 - (rentDiff / preferences.getRentBudget() * 100));
        totalScore += rentScore * (preferences.getRentWeightage() / 100.0);

        // Distance score (lower distance is better)
        double distanceDiff = Math.abs(pg.getDistance() - preferences.getDistance());
        double distanceScore = Math.max(0, 100 - (distanceDiff / preferences.getDistance() * 100));
        totalScore += distanceScore * (preferences.getDistanceWeightage() / 100.0);

        // Area score (based on priorities 1-4)
        int areaPriority = preferences.getAreaPriorities().getOrDefault(pg.getArea(), 0);
        double areaScore = 0;
        if (areaPriority > 0) {
            areaScore = (5 - areaPriority) * 25; // Convert priority 1-4 to score 100-25
        }
        totalScore += areaScore * (preferences.getAreaWeightage() / 100.0);

        // Amenities score (based on priorities 1-5)
        double amenitiesScore = 0;
        int totalPriority = 0;
        int amenityCount = 0;
        for (String amenity : pg.getAmenities()) {
            Integer priority = preferences.getAmenitiesPriorities().get(amenity);
            if (priority != null) {
                totalPriority += priority;
                amenityCount++;
            }
        }
        if (amenityCount > 0) {
            amenitiesScore = (6 - (totalPriority / amenityCount)) * 20; // Convert average priority to score
        }
        totalScore += amenitiesScore * (preferences.getAmenitiesWeightage() / 100.0);

        // Beds per room score
        double bedsScore;
        if (pg.getBedsPerRoom() == preferences.getBedsPerRoom()) {
            bedsScore = 100; // Exact match gets full score
        } else {
            double diff = Math.abs(preferences.getBedsPerRoom() - pg.getBedsPerRoom());
            bedsScore = Math.max(0, 100 - (diff * 50)); // Penalty for each bed difference
        }
        totalScore += bedsScore * (preferences.getBedsWeightage() / 100.0);

        return totalScore;
    }

    // Helper method to get individual scores for display
    public Map<String, Double> getIndividualScores(PGListing pg, PGPreferences preferences) {
        Map<String, Double> scores = new HashMap<>();
        
        // Rent score (max 40)
        double rentDiff = Math.abs(pg.getRent() - preferences.getRentBudget());
        double rentScore = Math.max(0, 40 - (rentDiff / preferences.getRentBudget() * 40));
        scores.put("Rent", rentScore);

        // Distance score (max 20)
        double distanceDiff = Math.abs(pg.getDistance() - preferences.getDistance());
        double distanceScore = Math.max(0, 20 - (distanceDiff / preferences.getDistance() * 20));
        scores.put("Distance", distanceScore);

        // Area score (max 20)
        int areaPriority = preferences.getAreaPriorities().getOrDefault(pg.getArea(), 0);
        double areaScore = 0;
        if (areaPriority > 0) {
            areaScore = (5 - areaPriority) * 5; // Convert priority 1-4 to score 20-5
        }
        scores.put("Area", areaScore);

        // Beds per room score (max 20)
        double bedsScore;
        if (pg.getBedsPerRoom() == preferences.getBedsPerRoom()) {
            bedsScore = 20; // Exact match gets full score
        } else {
            double diff = Math.abs(preferences.getBedsPerRoom() - pg.getBedsPerRoom());
            bedsScore = Math.max(0, 20 - (diff * 10)); // Penalty for each bed difference
        }
        scores.put("BedsPerRoom", bedsScore);

        // Amenities score (max 20)
        double amenitiesScore = 0;
        int totalPriority = 0;
        int amenityCount = 0;
        for (String amenity : pg.getAmenities()) {
            Integer priority = preferences.getAmenitiesPriorities().get(amenity);
            if (priority != null) {
                totalPriority += priority;
                amenityCount++;
            }
        }
        if (amenityCount > 0) {
            amenitiesScore = (6 - (totalPriority / amenityCount)) * 4; // Convert average priority to score
        }
        scores.put("Amenities", amenitiesScore);

        return scores;
    }
} 