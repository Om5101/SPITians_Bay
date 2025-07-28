package com.spitbay.model;

import java.util.List;
import java.util.ArrayList;
import com.spitbay.util.AreaMapper;

public class PGListing {
    private int id;
    private String uid;
    private String aadhar;
    private String address;
    private int rent;
    private int distance; // in meters
    private int area; // 1-8 options
    private List<String> amenities;
    private int vacancies;
    private int bedsPerRoom;
    private String notes;
    private String gender; // M/F

    public PGListing(String uid, String aadhar, String address, int rent, int distance, 
                    int area, List<String> amenities, int vacancies, int bedsPerRoom, 
                    String notes, String gender) {
        this.uid = uid;
        this.aadhar = aadhar;
        this.address = address;
        this.rent = rent;
        this.distance = distance;
        this.area = area;
        this.amenities = amenities;
        this.vacancies = vacancies;
        this.bedsPerRoom = bedsPerRoom;
        this.notes = notes;
        this.gender = gender;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }

    public String getAadhar() { return aadhar; }
    public void setAadhar(String aadhar) { this.aadhar = aadhar; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public int getRent() { return rent; }
    public void setRent(int rent) { this.rent = rent; }

    public int getDistance() { return distance; }
    public void setDistance(int distance) { this.distance = distance; }

    public int getArea() { return area; }
    public void setArea(int area) { this.area = area; }
    
    public String getAreaName() { return AreaMapper.getAreaName(area); }
    
    public List<String> getAmenities() { return amenities; }
    public void setAmenities(List<String> amenities) { this.amenities = amenities; }

    public int getVacancies() { return vacancies; }
    public void setVacancies(int vacancies) { this.vacancies = vacancies; }

    public int getBedsPerRoom() { return bedsPerRoom; }
    public void setBedsPerRoom(int bedsPerRoom) { this.bedsPerRoom = bedsPerRoom; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    @Override
    public String toString() {
        return String.format("PG Listing [ID: %d]\n" +
                           "Address: %s\n" +
                           "Rent: %d\n" +
                           "Distance: %d meters\n" +
                           "Area: %s\n" +
                           "Amenities: %s\n" +
                           "Vacancies: %d\n" +
                           "Beds per room: %d\n" +
                           "Gender: %s\n" +
                           "Notes: %s",
                           id, address, rent, distance, getAreaName(), 
                           String.join(", ", amenities), 
                           vacancies, bedsPerRoom, gender, notes);
    }
} 