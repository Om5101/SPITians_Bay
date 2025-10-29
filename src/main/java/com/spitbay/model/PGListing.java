package com.spitbay.model;

import com.spitbay.util.SecurityUtil;
import java.util.List;

// PG listing model for accommodation search
public class PGListing {
    private int id;
    private String ownerUid;
    private String aadhar;
    private String address;
    private int rent;
    private int distance; // in meters
    private int areaCode;
    private List<String> amenities;
    private int vacancies;
    private int bedsPerRoom;
    private String gender;
    private String notes;
    
    public PGListing(String ownerUid, String aadhar, String address, int rent, 
                    int distance, int areaCode, List<String> amenities, 
                    int vacancies, int bedsPerRoom, String gender, String notes) {
        this.ownerUid = ownerUid;
        this.aadhar = aadhar;
        this.address = address;
        this.rent = rent;
        this.distance = distance;
        this.areaCode = areaCode;
        this.amenities = amenities;
        this.vacancies = vacancies;
        this.bedsPerRoom = bedsPerRoom;
        this.gender = gender;
        this.notes = notes;
    }
    
    // Getters and setters for encapsulation
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getOwnerUid() { return ownerUid; }
    public void setOwnerUid(String ownerUid) { this.ownerUid = ownerUid; }
    
    public String getAadhar() { return aadhar; }
    public void setAadhar(String aadhar) { this.aadhar = aadhar; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public int getRent() { return rent; }
    public void setRent(int rent) { this.rent = rent; }
    
    public int getDistance() { return distance; }
    public void setDistance(int distance) { this.distance = distance; }
    
    public int getAreaCode() { return areaCode; }
    public void setAreaCode(int areaCode) { this.areaCode = areaCode; }
    
    public List<String> getAmenities() { return amenities; }
    public void setAmenities(List<String> amenities) { this.amenities = amenities; }
    
    public int getVacancies() { return vacancies; }
    public void setVacancies(int vacancies) { this.vacancies = vacancies; }
    
    public int getBedsPerRoom() { return bedsPerRoom; }
    public void setBedsPerRoom(int bedsPerRoom) { this.bedsPerRoom = bedsPerRoom; }
    
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    // Validate PG listing data with strict rules
    public boolean isValid() {
        return SecurityUtil.isValidUid(ownerUid) &&
               SecurityUtil.isValidAadhar(aadhar) &&
               SecurityUtil.isValidAddress(address) &&
               rent > 0 && distance >= 0 && areaCode >= 1 && areaCode <= 4 &&
               vacancies >= 0 && bedsPerRoom > 0 &&
               gender != null && (gender.equals("M") || gender.equals("F"));
    }
    
    @Override
    public String toString() {
        return String.format("PG ID: %d | Rent: ₹%d | Distance: %dm | Area: %s | Gender: %s", 
                           id, rent, distance, getAreaName(), gender);
    }
    
    private String getAreaName() {
        switch(areaCode) {
            case 1: return "JP Road";
            case 2: return "Malad";
            case 3: return "Versova";
            case 4: return "DN Nagar";
            default: return "Area " + areaCode;
        }
    }
}
