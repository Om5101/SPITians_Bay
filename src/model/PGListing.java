package model;

public class PGListing {
    private int pgId;
    private String uid;
    private String aadhar;
    private String address;
    private int rent;
    private int distance;
    private String area;
    private int vacancies;
    private int beds;
    private String notes;
    
    public PGListing() {}
    
    public PGListing(String uid, String aadhar, String address, int rent, int distance, 
                    String area, int vacancies, int beds, String notes) {
        this.uid = uid;
        this.aadhar = aadhar;
        this.address = address;
        this.rent = rent;
        this.distance = distance;
        this.area = area;
        this.vacancies = vacancies;
        this.beds = beds;
        this.notes = notes;
    }
    
    // Getters and Setters
    public int getPgId() {
        return pgId;
    }
    
    public void setPgId(int pgId) {
        this.pgId = pgId;
    }
    
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public String getAadhar() {
        return aadhar;
    }
    
    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public int getRent() {
        return rent;
    }
    
    public void setRent(int rent) {
        this.rent = rent;
    }
    
    public int getDistance() {
        return distance;
    }
    
    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    public String getArea() {
        return area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    
    public int getVacancies() {
        return vacancies;
    }
    
    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }
    
    public int getBeds() {
        return beds;
    }
    
    public void setBeds(int beds) {
        this.beds = beds;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
} 