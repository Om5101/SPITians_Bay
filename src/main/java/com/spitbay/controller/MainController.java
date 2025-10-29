package com.spitbay.controller;

import com.spitbay.manager.ServiceManager;
import com.spitbay.model.*;
import com.spitbay.service.*;
import com.spitbay.view.*;
import java.util.*;

// Main controller for application flow
public class MainController {
    private final UserService userService;
    private final PGService pgService;
    private final BlogService blogService;
    private final ScoringService scoringService;
    private final InputHandler inputHandler;
    
    public MainController() {
        ServiceManager serviceManager = ServiceManager.getInstance();
        this.userService = serviceManager.getUserService();
        this.pgService = serviceManager.getPGService();
        this.blogService = serviceManager.getBlogService();
        this.scoringService = serviceManager.getScoringService();
        this.inputHandler = new InputHandler();
    }
    
    // Start the application
    public void start() {
        MenuView.showWelcome();
        
        while (true) {
            MenuView.showMainMenu();
            int choice = inputHandler.getIntInput();
            
            switch (choice) {
                case 1:
                    handleSeniorMenu();
                    break;
                case 2:
                    handleFresherMenu();
                    break;
                case 3:
                    MenuView.showGoodbye();
                    return;
                default:
                    AuthView.showInvalidChoice();
            }
        }
    }
    
    // Handle senior menu flow
    private void handleSeniorMenu() {
        while (true) {
            MenuView.showSeniorMenu();
            int choice = inputHandler.getIntInput();
            
            switch (choice) {
                case 1:
                    handleSeniorLogin();
                    break;
                case 2:
                    handleSeniorRegistration();
                    break;
                case 3:
                    return;
                default:
                    AuthView.showInvalidChoice();
            }
        }
    }
    
    // Handle senior login with secure input
    private void handleSeniorLogin() {
        try {
            String uid = inputHandler.getUidInput();
            String password = inputHandler.getPasswordInput();
            
            Senior senior = userService.authenticateUser(uid, password);
            if (senior != null) {
                AuthView.showLoginSuccess();
                handleSeniorDashboard(senior);
            } else {
                AuthView.showLoginFailure();
            }
        } catch (Exception e) {
            AuthView.showLoginError(e.getMessage());
        }
    }
    
    // Handle senior registration with secure input
    private void handleSeniorRegistration() {
        try {
            String uid = inputHandler.getUidInput();
            String password = inputHandler.getPasswordInput();
            
            if (userService.userExists(uid)) {
                AuthView.showUserExists();
                return;
            }
            
            Senior senior = new Senior(uid, password);
            if (userService.registerUser(senior)) {
                AuthView.showRegistrationSuccess();
                handleSeniorDashboard(senior);
            } else {
                AuthView.showRegistrationFailure();
            }
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("UID not authorized for registration")) {
                AuthView.showUnauthorizedUid();
            } else {
                AuthView.showRegistrationError(e.getMessage());
            }
        } catch (Exception e) {
            AuthView.showRegistrationError(e.getMessage());
        }
    }
    
    // Handle senior dashboard
    private void handleSeniorDashboard(Senior senior) {
        while (true) {
            MenuView.showSeniorDashboard();
            int choice = inputHandler.getIntInput();
            
            switch (choice) {
                case 1:
                    handlePGListings(senior);
                    break;
                case 2:
                    handleBlogs(senior);
                    break;
                case 3:
                    return;
                default:
                    AuthView.showInvalidChoice();
            }
        }
    }
    
    // Handle PG listings management
    private void handlePGListings(Senior senior) {
        List<PGListing> listings = pgService.getPGListingsByOwner(senior.getUid());
        PGView.displayUserListings(listings);
        
        PGView.showPGManagement();
        if (inputHandler.getIntInput() == 1) {
            handleAddPGListing(senior);
        }
    }
    
    // Handle adding new PG listing with secure input
    private void handleAddPGListing(Senior senior) {
        try {
            PGView.showAddPGForm();
            
            String aadhar = inputHandler.getAadharInput();
            String address = inputHandler.getAddressInput();
            int rent = inputHandler.getIntInput("Rent (INR)");
            int distance = inputHandler.getIntInput("Distance from college (meters)");
            int areaCode = inputHandler.getIntInput("Area Code (1-4)", 1, 4);
            
            String[] amenities = scoringService.getAmenityNames();
            PGView.showAvailableAmenities(amenities);
            
            List<String> selectedAmenities = inputHandler.getAmenitySelection(amenities);
            int vacancies = inputHandler.getIntInput("Vacancies");
            int bedsPerRoom = inputHandler.getIntInput("Beds per Room");
            String gender = inputHandler.getGenderInput();
            String notes = inputHandler.getInput("Notes (optional)");
            
            PGListing listing = new PGListing(senior.getUid(), aadhar, address, rent, distance,
                                            areaCode, selectedAmenities, vacancies, bedsPerRoom, gender, notes);
            
            if (pgService.addPGListing(listing)) {
                MenuView.showSuccess("PG Listing added successfully!");
            } else {
                MenuView.showError("Failed to add PG Listing!");
            }
        } catch (Exception e) {
            MenuView.showError("Error adding PG listing: " + e.getMessage());
        }
    }
    
    // Handle blog management
    private void handleBlogs(Senior senior) {
        List<Blog> blogs = blogService.getBlogsByAuthor(senior.getUid());
        BlogView.displayUserBlogs(blogs);
        
        BlogView.showBlogManagement();
        if (inputHandler.getIntInput() == 1) {
            handleAddBlog(senior);
        }
    }
    
    // Handle adding new blog
    private void handleAddBlog(Senior senior) {
        try {
            BlogView.showAddBlogForm();
            
            String content = inputHandler.getInput("Blog content");
            
            List<String> categories = blogService.getAvailableCategories();
            BlogView.showAvailableCategories(categories);
            
            List<String> selectedCategories = inputHandler.getCategorySelection(categories);
            Set<String> hashtags = inputHandler.getHashtagInput();
            
            Blog blog = new Blog(senior.getUid(), content, selectedCategories, hashtags);
            
            if (blogService.addBlog(blog)) {
                MenuView.showSuccess("Blog added successfully!");
            } else {
                MenuView.showError("Failed to add blog!");
            }
        } catch (Exception e) {
            MenuView.showError("Error adding blog: " + e.getMessage());
        }
    }
    
    // Handle fresher menu flow
    private void handleFresherMenu() {
        while (true) {
            MenuView.showFresherMenu();
            int choice = inputHandler.getIntInput();
            
            switch (choice) {
                case 1:
                    handlePGSearch();
                    break;
                case 2:
                    handleBlogViewing();
                    break;
                case 3:
                    return;
                default:
                    AuthView.showInvalidChoice();
            }
        }
    }
    
    // Handle PG search with scoring
    private void handlePGSearch() {
        try {
            PGView.showSearchForm();
            
            SearchPreferences preferences = new SearchPreferences();
            
            preferences.setBudget(inputHandler.getIntInput("Rent budget (INR)"));
            preferences.setMaxDistance(inputHandler.getIntInput("Max distance (meters)"));
            preferences.setGender(inputHandler.getGenderInput());
            preferences.setPreferredBeds(inputHandler.getIntInput("Preferred beds per room"));
            
            // Set weights
            PGView.showWeightInstructions();
            Map<String, Integer> weights = new HashMap<>();
            weights.put("rent", inputHandler.getIntInput("Rent importance (%)"));
            weights.put("distance", inputHandler.getIntInput("Distance importance (%)"));
            weights.put("amenities", inputHandler.getIntInput("Amenities importance (%)"));
            weights.put("area", inputHandler.getIntInput("Area importance (%)"));
            preferences.setWeights(weights);
            
            // Set area priorities
            PGView.showAreaInstructions();
            Map<String, Integer> areaPriorities = new HashMap<>();
            String[] areas = scoringService.getAreaNames();
            for (String area : areas) {
                areaPriorities.put(area, inputHandler.getIntInput("Priority for " + area + " (1-5)", 1, 5));
            }
            preferences.setAreaPriorities(areaPriorities);
            
            // Set amenity priorities
            PGView.showAmenityInstructions();
            Map<String, Integer> amenityPriorities = new HashMap<>();
            String[] amenities = scoringService.getAmenityNames();
            for (String amenity : amenities) {
                amenityPriorities.put(amenity, inputHandler.getIntInput("Priority for " + amenity + " (0-5)", 0, 5));
            }
            preferences.setAmenityPriorities(amenityPriorities);
            
            if (!preferences.isValid()) {
                MenuView.showError("Invalid preferences data!");
                return;
            }
            
            // Search and score PGs
            List<PGListing> listings = pgService.getPGListingsByGender(preferences.getGender());
            
            if (listings.isEmpty()) {
                MenuView.showError("No matching PGs found.");
                return;
            }
            
            // Calculate scores and store in list
            List<Map.Entry<PGListing, Double>> scoredListings = new ArrayList<Map.Entry<PGListing, Double>>();
            for (PGListing listing : listings) {
                double score = scoringService.calculateScore(listing, preferences);
                if (score > 0) {
                    scoredListings.add(new AbstractMap.SimpleEntry<PGListing, Double>(listing, score));
                }
            }
            
            // Sort by score in descending order
            Collections.sort(scoredListings, new Comparator<Map.Entry<PGListing, Double>>() {
                public int compare(Map.Entry<PGListing, Double> a, Map.Entry<PGListing, Double> b) {
                    return Double.compare(b.getValue(), a.getValue());
                }
            });
            
            PGView.displaySearchResults(scoredListings);
            
        } catch (Exception e) {
            MenuView.showError("Error during PG search: " + e.getMessage());
        }
    }
    
    // Handle blog viewing and search
    private void handleBlogViewing() {
        while (true) {
            MenuView.showBlogMenu();
            int choice = inputHandler.getIntInput();
            
            switch (choice) {
                case 1:
                    BlogView.displayBlogs(blogService.getAllBlogs());
                    break;
                case 2:
                    handleCategorySearch();
                    break;
                case 3:
                    handleHashtagSearch();
                    break;
                case 4:
                    return;
                default:
                    AuthView.showInvalidChoice();
            }
        }
    }
    
    // Handle category-based blog search
    private void handleCategorySearch() {
        try {
            List<String> categories = blogService.getAvailableCategories();
            BlogView.showAvailableCategories(categories);
            
            List<String> selectedCategories = inputHandler.getCategorySelection(categories);
            List<Blog> blogs = blogService.searchBlogsByCategories(selectedCategories);
            BlogView.displayBlogs(blogs);
        } catch (Exception e) {
            MenuView.showError("Error searching blogs: " + e.getMessage());
        }
    }
    
    // Handle hashtag-based blog search using HashSet
    private void handleHashtagSearch() {
        try {
            Set<String> searchHashtags = inputHandler.getHashtagInput();
            List<Blog> blogs = blogService.searchBlogsByHashtags(searchHashtags);
            BlogView.displayBlogs(blogs);
        } catch (Exception e) {
            MenuView.showError("Error searching blogs: " + e.getMessage());
        }
    }
}
