package com.spitbay.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class PGPreferences {
    private int rentBudget;
    private int rentWeightage;
    private int distance;
    private int distanceWeightage;
    private Map<Integer, Integer> areaPriorities; // Area (1-4) -> Priority (1-4)
    private int areaWeightage;
    private Map<String, Integer> amenitiesPriorities; // Amenity name -> Priority (1-5)
    private int amenitiesWeightage;
    private int bedsPerRoom;
    private int bedsWeightage;
    private String gender; // M/F

    public PGPreferences() {
        this.areaPriorities = new HashMap<>();
        this.amenitiesPriorities = new HashMap<>();
    }

    // Getters and Setters
    public int getRentBudget() { return rentBudget; }
    public void setRentBudget(int rentBudget) { this.rentBudget = rentBudget; }
    
    public int getRentWeightage() { return rentWeightage; }
    public void setRentWeightage(int rentWeightage) { this.rentWeightage = rentWeightage; }
    
    public int getDistance() { return distance; }
    public void setDistance(int distance) { this.distance = distance; }
    
    public int getDistanceWeightage() { return distanceWeightage; }
    public void setDistanceWeightage(int distanceWeightage) { this.distanceWeightage = distanceWeightage; }
    
    public Map<Integer, Integer> getAreaPriorities() { return areaPriorities; }
    public void setAreaPriorities(Map<Integer, Integer> areaPriorities) { this.areaPriorities = areaPriorities; }
    
    public int getAreaWeightage() { return areaWeightage; }
    public void setAreaWeightage(int areaWeightage) { this.areaWeightage = areaWeightage; }
    
    public Map<String, Integer> getAmenitiesPriorities() { return amenitiesPriorities; }
    public void setAmenitiesPriorities(Map<String, Integer> amenitiesPriorities) { this.amenitiesPriorities = amenitiesPriorities; }
    
    public int getAmenitiesWeightage() { return amenitiesWeightage; }
    public void setAmenitiesWeightage(int amenitiesWeightage) { this.amenitiesWeightage = amenitiesWeightage; }
    
    public int getBedsPerRoom() { return bedsPerRoom; }
    public void setBedsPerRoom(int bedsPerRoom) { this.bedsPerRoom = bedsPerRoom; }
    
    public int getBedsWeightage() { return bedsWeightage; }
    public void setBedsWeightage(int bedsWeightage) { this.bedsWeightage = bedsWeightage; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public boolean validateWeightages() {
        int totalWeight = rentWeightage + distanceWeightage + areaWeightage + 
                         amenitiesWeightage + bedsWeightage;
        return totalWeight == 100;
    }

    @Override
    public String toString() {
        return String.format("PG Preferences:\n" +
                           "Rent Budget: %d (Weight: %d%%)\n" +
                           "Distance: %d meters (Weight: %d%%)\n" +
                           "Area Priorities: %s (Weight: %d%%)\n" +
                           "Amenities Priorities: %s (Weight: %d%%)\n" +
                           "Beds per room: %d (Weight: %d%%)\n" +
                           "Gender: %s",
                           rentBudget, rentWeightage,
                           distance, distanceWeightage,
                           areaPriorities, areaWeightage,
                           amenitiesPriorities, amenitiesWeightage,
                           bedsPerRoom, bedsWeightage,
                           gender);
    }
} 