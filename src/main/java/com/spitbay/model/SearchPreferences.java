package com.spitbay.model;

import java.util.HashMap;
import java.util.Map;

// Student search preferences for PG matching
public class SearchPreferences {
    private int budget;
    private int maxDistance;
    private String gender;
    private int preferredBeds;
    private Map<String, Integer> weights;
    private Map<String, Integer> areaPriorities;
    private Map<String, Integer> amenityPriorities;
    
    public SearchPreferences() {
        this.weights = new HashMap<>();
        this.areaPriorities = new HashMap<>();
        this.amenityPriorities = new HashMap<>();
    }
    
    // Getters and setters for encapsulation
    public int getBudget() { return budget; }
    public void setBudget(int budget) { this.budget = budget; }
    
    public int getMaxDistance() { return maxDistance; }
    public void setMaxDistance(int maxDistance) { this.maxDistance = maxDistance; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public int getPreferredBeds() { return preferredBeds; }
    public void setPreferredBeds(int preferredBeds) { this.preferredBeds = preferredBeds; }
    
    public Map<String, Integer> getWeights() { return weights; }
    public void setWeights(Map<String, Integer> weights) { this.weights = weights; }
    
    public Map<String, Integer> getAreaPriorities() { return areaPriorities; }
    public void setAreaPriorities(Map<String, Integer> areaPriorities) { this.areaPriorities = areaPriorities; }
    
    public Map<String, Integer> getAmenityPriorities() { return amenityPriorities; }
    public void setAmenityPriorities(Map<String, Integer> amenityPriorities) { this.amenityPriorities = amenityPriorities; }
    
    // Validate if weights sum to 100 (with small flexibility)
    public boolean isValidWeights() {
        int sum = 0;
        for (Integer weight : weights.values()) {
            sum += weight.intValue();
        }
        return Math.abs(sum - 100) <= 2;
    }
    
    // Validate preferences
    public boolean isValid() {
        return budget > 0 && maxDistance >= 0 && 
               gender != null && (gender.equals("M") || gender.equals("F")) &&
               preferredBeds > 0 && isValidWeights();
    }
    
    @Override
    public String toString() {
        return String.format("Budget: ₹%d, Distance: %dm, Gender: %s, Beds: %d", 
                           budget, maxDistance, gender, preferredBeds);
    }
}
