package com.spitbay.controller;

import com.spitbay.util.SecurityUtil;
import java.util.*;

// Utility class for handling user input safely
public class InputHandler {
    private final Scanner scanner;
    
    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }
    
    // Get string input
    public String getStringInput() {
        try {
            return scanner.nextLine().trim();
        } catch (Exception e) {
            return "";
        }
    }
    
    // Get input with prompt
    public String getInput(String prompt) {
        System.out.print("Enter " + prompt + ": ");
        return getStringInput();
    }
    
    // Get integer input
    public int getIntInput() {
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
    
    // Get integer input with prompt
    public int getIntInput(String prompt) {
        System.out.print("Enter " + prompt + ": ");
        return getIntInput();
    }
    
    // Get integer input within range
    public int getIntInput(String prompt, int min, int max) {
        while (true) {
            System.out.print("Enter " + prompt + ": ");
            try {
                String line = scanner.nextLine().trim();
                int input = Integer.parseInt(line);
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.printf("Please enter a number between %d and %d: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
    
    // Get gender input (M/F)
    public String getGenderInput() {
        while (true) {
            System.out.print("Enter gender (M/F): ");
            String input = getStringInput().toUpperCase();
            if ("M".equals(input) || "F".equals(input)) {
                return input;
            }
            System.out.print("Please enter M or F: ");
        }
    }
    
    // Get amenity selection from comma-separated input
    public List<String> getAmenitySelection(String[] availableAmenities) {
        System.out.print("Enter amenity numbers (comma-separated): ");
        String input = getStringInput();
        
        if (input.isEmpty()) {
            return new ArrayList<String>();
        }
        
        List<String> selectedAmenities = new ArrayList<String>();
        String[] parts = input.split(",");
        
        for (int i = 0; i < parts.length; i++) {
            String trimmedPart = parts[i].trim();
            try {
                int index = Integer.parseInt(trimmedPart) - 1;
                if (index >= 0 && index < availableAmenities.length) {
                    selectedAmenities.add(availableAmenities[index]);
                }
            } catch (NumberFormatException e) {
                // Skip invalid numbers
            }
        }
        
        return selectedAmenities;
    }
    
    // Get category selection from comma-separated input
    public List<String> getCategorySelection(List<String> availableCategories) {
        System.out.print("Enter category numbers (comma-separated): ");
        String input = getStringInput();
        
        if (input.isEmpty()) {
            return new ArrayList<String>();
        }
        
        List<String> selectedCategories = new ArrayList<String>();
        String[] parts = input.split(",");
        
        for (int i = 0; i < parts.length; i++) {
            String trimmedPart = parts[i].trim();
            try {
                int index = Integer.parseInt(trimmedPart) - 1;
                if (index >= 0 && index < availableCategories.size()) {
                    selectedCategories.add(availableCategories.get(index));
                }
            } catch (NumberFormatException e) {
                // Skip invalid numbers
            }
        }
        
        return selectedCategories;
    }
    
    // Get hashtag input and return as HashSet for fast lookup
    public Set<String> getHashtagInput() {
        System.out.print("Enter hashtags (space-separated): ");
        String input = getStringInput();
        
        if (input.isEmpty()) {
            return new HashSet<String>();
        }
        
        Set<String> hashtags = new HashSet<String>();
        String[] parts = input.split("\\s+");
        
        for (int i = 0; i < parts.length; i++) {
            String hashtag = parts[i].trim();
            if (!hashtag.startsWith("#")) {
                hashtag = "#" + hashtag;
            }
            if (hashtag.length() > 1) {
                hashtags.add(hashtag);
            }
        }
        
        return hashtags;
    }
    
    // Get UID input with strict validation (Email ending with @spit.ac.in)
    public String getUidInput() {
        while (true) {
            System.out.print("Enter UID (Email ending with @spit.ac.in): ");
            String uid = getStringInput();
            if (SecurityUtil.isValidUid(uid)) {
                return uid.toLowerCase().trim(); // Normalize to lowercase
            }
            System.out.println("Invalid UID! Must be a valid email ending with '@spit.ac.in'.");
        }
    }
    
    // Get password input securely
    public String getPasswordInput() {
        while (true) {
            System.out.print("Enter password: ");
            String password = getStringInput();
            if (password != null && !password.trim().isEmpty() && password.length() >= 6) {
                return password;
            }
            System.out.println("Password must be at least 6 characters long.");
        }
    }
    
    // Get Aadhar input with strict validation (12 digits only)
    public String getAadharInput() {
        while (true) {
            System.out.print("Enter Aadhar Number (12 digits only): ");
            String aadhar = getStringInput();
            if (SecurityUtil.isValidAadhar(aadhar)) {
                return aadhar;
            }
            System.out.println("Invalid Aadhar! Must be exactly 12 digits (numbers only).");
        }
    }
    
    // Get address input with validation
    public String getAddressInput() {
        while (true) {
            System.out.print("Enter Address: ");
            String address = getStringInput();
            if (SecurityUtil.isValidAddress(address)) {
                return address;
            }
            System.out.println("Address cannot be empty!");
        }
    }
}
