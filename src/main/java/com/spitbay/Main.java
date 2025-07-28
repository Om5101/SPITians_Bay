package com.spitbay;

import com.spitbay.model.Senior;
import com.spitbay.model.PGListing;
import com.spitbay.model.Blog;
import com.spitbay.model.PGPreferences;
import com.spitbay.service.SeniorService;
import com.spitbay.service.PGListingService;
import com.spitbay.service.BlogService;
import com.spitbay.service.PGMatchmakingService;
import com.spitbay.util.RabinKarpSearch;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.sql.SQLException;

public class Main {
    private static SeniorService seniorService;
    private static PGListingService pgListingService;
    private static BlogService blogService;
    private static PGMatchmakingService pgMatchmakingService;
    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            seniorService = new SeniorService();
            pgListingService = new PGListingService();
            blogService = new BlogService();
            pgMatchmakingService = new PGMatchmakingService(pgListingService);
            scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nWelcome, to SPIT");
                System.out.println("1. Senior  : Add PG / Write a Blog");
                System.out.println("2. Fresher : Looking for PG / Blogs");
                System.out.print("Enter Choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        seniorWorkflow();
                        break;
                    case 2:
                        fresherWorkflow();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static void seniorWorkflow() {
        System.out.println("\n=== Senior Workflow ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Back to Main Menu");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                seniorLogin();
                break;
            case 2:
                seniorRegistration();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void seniorLogin() {
        System.out.print("Enter UID: ");
        String uid = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            Senior senior = seniorService.login(uid, password);
            if (senior != null) {
                System.out.println("Login successful!");
                seniorMenu(senior);
            } else {
                System.out.println("Invalid credentials!");
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }

    private static void seniorRegistration() {
        System.out.print("Enter UID: ");
        String uid = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            Senior senior = new Senior(uid, password);
            if (seniorService.register(senior)) {
                System.out.println("Registration successful!");
                seniorMenu(senior);
            } else {
                System.out.println("Registration failed!");
            }
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
        }
    }

    private static void seniorMenu(Senior senior) {
        while (true) {
            System.out.println("\n=== Senior Menu ===");
            System.out.println("1. Go to My PG Listing");
            System.out.println("2. Go to My Blog Post");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    managePGListing(senior);
                    break;
                case 2:
                    manageBlog(senior);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void managePGListing(Senior senior) {
        try {
            List<PGListing> seniorPGs = pgListingService.getPGListingByUid(senior.getUid());
            if (seniorPGs != null && !seniorPGs.isEmpty()) {
                PGListing existingPG = seniorPGs.get(0);
                System.out.println("\n=== PG Listing Options ===");
                System.out.println("You have already posted a PG listing:");
                System.out.println(existingPG);
                System.out.println("1. View");
                System.out.println("2. Update");
                System.out.println("3. Delete");
                System.out.println("4. Back to Senior Menu");
                System.out.print("Enter choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("\n--- Your PG Listing ---");
                        System.out.println(existingPG);
                        break;
                    case 2:
                        updatePGListing(existingPG);
                        break;
                    case 3:
                        deletePGListing(existingPG.getId());
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("\n=== PG Listing Options ===");
                System.out.println("You have not any posted a PG listing yet.");
                System.out.println("1. Add New PG Listing");
                System.out.println("2. Back to Senior Menu");
                System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addPGListing(senior);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            }
        } catch (Exception e) {
            System.out.println("Error managing PG listings: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void addPGListing(Senior senior) {
        try {
            System.out.print("Enter PG Owner Aadhar (Unique): ");
            String aadhar = scanner.nextLine();
            System.out.print("Enter PG Owner Address: ");
            String address = scanner.nextLine();
            System.out.print("Enter Rent (int): ");
            int rent = scanner.nextInt();
            System.out.print("Enter Distance from college (in meters): ");
            int distance = scanner.nextInt();
            
            System.out.println("Available Areas:");
            Map<Integer, String> areas = com.spitbay.util.AreaMapper.getAllAreas();
            for (Map.Entry<Integer, String> entry : areas.entrySet()) {
                System.out.printf("%d. %s\n", entry.getKey(), entry.getValue());
            }
            System.out.print("Enter Area (1-4): ");
            int area = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.println("Available Amenities: AC, WiFi, Geyser, Laundry, Parking lot");
            System.out.print("Enter Amenities (comma-separated: AC, WiFi, etc.): ");
            List<String> amenities = Arrays.asList(scanner.nextLine().split(","));
            amenities.replaceAll(String::trim);

            System.out.print("Enter Current vacancies (int): ");
            int vacancies = scanner.nextInt();
            System.out.print("Enter Beds per room (int): ");
            int bedsPerRoom = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Notes / Special instructions: ");
            String notes = scanner.nextLine();
            System.out.print("Enter Gender (M/F): ");
            String gender = scanner.nextLine();

            PGListing pgListing = new PGListing(senior.getUid(), aadhar, address, rent, distance, area, amenities, vacancies, bedsPerRoom, notes, gender);
            if (pgListingService.addPGListing(pgListing)) {
                System.out.println("PG Listing added successfully!");
            } else {
                System.out.println("Failed to add PG Listing!");
            }
        } catch (Exception e) {
            System.out.println("Error adding PG Listing: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void updatePGListing(PGListing existingPG) {
        try {
            System.out.println("\n--- Update PG Listing ---");
            System.out.print("Enter new address (current: " + existingPG.getAddress() + "): ");
            String address = scanner.nextLine();
            if (!address.isEmpty()) {
                existingPG.setAddress(address);
            }

            System.out.print("Enter new rent (current: " + existingPG.getRent() + "): ");
            String rentStr = scanner.nextLine();
            if (!rentStr.isEmpty()) {
                existingPG.setRent(Integer.parseInt(rentStr));
            }
            
            System.out.print("Enter new distance (current: " + existingPG.getDistance() + "): ");
            String distanceStr = scanner.nextLine();
            if (!distanceStr.isEmpty()) {
                existingPG.setDistance(Integer.parseInt(distanceStr));
            }

            System.out.println("Available Areas:");
            Map<Integer, String> areas = com.spitbay.util.AreaMapper.getAllAreas();
            for (Map.Entry<Integer, String> entry : areas.entrySet()) {
                System.out.printf("%d. %s\n", entry.getKey(), entry.getValue());
            }
            System.out.print("Enter new area (1-4, current: " + existingPG.getAreaName() + "): ");
            String areaStr = scanner.nextLine();
            if (!areaStr.isEmpty()) {
                existingPG.setArea(Integer.parseInt(areaStr));
            }
            
            System.out.println("Available Amenities: AC, WiFi, Geyser, Laundry, Parking lot");
            System.out.print("Enter new amenities (comma-separated, current: " + String.join(", ", existingPG.getAmenities()) + "): ");
            String amenitiesStr = scanner.nextLine();
            if (!amenitiesStr.isEmpty()) {
                List<String> newAmenities = Arrays.asList(amenitiesStr.split(","));
                newAmenities.replaceAll(String::trim);
                existingPG.setAmenities(newAmenities);
            }

            System.out.print("Enter new vacancies (current: " + existingPG.getVacancies() + "): ");
            String vacanciesStr = scanner.nextLine();
            if (!vacanciesStr.isEmpty()) {
                existingPG.setVacancies(Integer.parseInt(vacanciesStr));
            }
            
            System.out.print("Enter new beds per room (current: " + existingPG.getBedsPerRoom() + "): ");
            String bedsPerRoomStr = scanner.nextLine();
            if (!bedsPerRoomStr.isEmpty()) {
                existingPG.setBedsPerRoom(Integer.parseInt(bedsPerRoomStr));
            }
            
            System.out.print("Enter new notes (current: " + existingPG.getNotes() + "): ");
            String notes = scanner.nextLine();
            if (!notes.isEmpty()) {
                existingPG.setNotes(notes);
            }
            
            System.out.print("Enter new gender (M/F, current: " + existingPG.getGender() + "): ");
            String gender = scanner.nextLine();
            if (!gender.isEmpty()) {
                existingPG.setGender(gender);
            }

            if (pgListingService.updatePGListing(existingPG)) {
                System.out.println("PG Listing updated successfully!");
            } else {
                System.out.println("Failed to update PG Listing!");
            }
        } catch (Exception e) {
            System.out.println("Error updating PG Listing: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void deletePGListing(int pgId) {
        try {
            if (pgListingService.deletePGListing(pgId)) {
                System.out.println("PG Listing deleted successfully!");
            } else {
                System.out.println("Failed to delete PG Listing!");
            }
        } catch (Exception e) {
            System.out.println("Error deleting PG Listing: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void viewMyPGListings(Senior senior) {
        try {
            List<PGListing> listings = pgListingService.getPGListingByUid(senior.getUid());
            if (listings == null || listings.isEmpty()) {
                System.out.println("You have no PG listings.");
            } else {
                System.out.println("\n--- Your PG Listings ---");
            for (PGListing listing : listings) {
                    System.out.println(listing);
                System.out.println("------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Error viewing your PG listings: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void manageBlog(Senior senior) {
        try {
            List<Blog> seniorBlogs = blogService.getBlogByUid(senior.getUid());
            if (seniorBlogs != null && !seniorBlogs.isEmpty()) {
                Blog existingBlog = seniorBlogs.get(0);
                System.out.println("\n=== Blog Post Options ===");
                System.out.println("You have already posted a blog:");
                System.out.println(existingBlog);
                System.out.println("1. View");
                System.out.println("2. Edit");
                System.out.println("3. Delete");
                System.out.println("4. Back to Senior Menu");
                System.out.print("Enter choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("\n--- Your Blog Post ---");
                        System.out.println(existingBlog);
                        break;
                    case 2:
                        updateBlog(existingBlog);
                        break;
                    case 3:
                        deleteBlog(existingBlog.getId());
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("\n=== Blog Post Options ===");
                System.out.println("You have not posted a blog yet.");
                System.out.println("1. Add New Blog Post");
                System.out.println("2. Back to Senior Menu");
                System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBlog(senior);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            }
        } catch (Exception e) {
            System.out.println("Error managing blogs: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void addBlog(Senior senior) {
        try {
            System.out.print("Enter blog content: ");
            String content = scanner.nextLine();

            System.out.println("\nChoose one or more categories (comma-separated):");
            List<String> fixedCategories = blogService.getFixedCategories();
            for (int i = 0; i < fixedCategories.size(); i++) {
                System.out.println((i + 1) + ". " + fixedCategories.get(i));
            }
            System.out.print("Enter category numbers (e.g., 1,3): ");
            String categoryInput = scanner.nextLine();
            List<String> selectedCategories = new ArrayList<>();
            for (String numStr : categoryInput.split(",")) {
                try {
                    int index = Integer.parseInt(numStr.trim()) - 1;
                    if (index >= 0 && index < fixedCategories.size()) {
                        selectedCategories.add(fixedCategories.get(index));
                    }
                } catch (NumberFormatException e) {
                    // Ignore invalid input
                }
            }

            System.out.print("Add any number of hashtags (space-separated): ");
            String hashtagInput = scanner.nextLine();
            Set<String> hashtags = new HashSet<>(Arrays.asList(hashtagInput.split(" ")));
            hashtags.removeIf(String::isEmpty);

            Blog blog = new Blog(senior.getUid(), content, selectedCategories, hashtags);
            if (blogService.addBlog(blog)) {
                System.out.println("Blog post added successfully!");
            } else {
                System.out.println("Failed to add blog post. Ensure categories are valid.");
            }
        } catch (Exception e) {
            System.out.println("Error adding blog post: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void updateBlog(Blog existingBlog) {
        try {
            System.out.println("\n--- Edit Blog Post ---");
            System.out.print("Enter new blog content (current: " + existingBlog.getContent() + "): ");
            String content = scanner.nextLine();
            if (!content.isEmpty()) {
                existingBlog.setContent(content);
            }

            System.out.println("\nChoose new categories (comma-separated, current: " + String.join(", ", existingBlog.getCategories()) + "): ");
            List<String> fixedCategories = blogService.getFixedCategories();
            for (int i = 0; i < fixedCategories.size(); i++) {
                System.out.println((i + 1) + ". " + fixedCategories.get(i));
            }
            System.out.print("Enter category numbers (e.g., 1,3) or press Enter to keep current: ");
            String categoryInput = scanner.nextLine();
            if (!categoryInput.isEmpty()) {
                List<String> newCategories = new ArrayList<>();
                for (String numStr : categoryInput.split(",")) {
                    try {
                        int index = Integer.parseInt(numStr.trim()) - 1;
                        if (index >= 0 && index < fixedCategories.size()) {
                            newCategories.add(fixedCategories.get(index));
                        }
                    } catch (NumberFormatException e) {
                        // Ignore invalid input
                    }
                }
                if (!newCategories.isEmpty()) {
                    existingBlog.setCategories(newCategories);
                }
            }

            System.out.print("Add new hashtags (space-separated, current: " + String.join(" ", existingBlog.getHashtags()) + ") or press Enter to keep current: ");
            String hashtagInput = scanner.nextLine();
            if (!hashtagInput.isEmpty()) {
                Set<String> newHashtags = new HashSet<>(Arrays.asList(hashtagInput.split(" ")));
                newHashtags.removeIf(String::isEmpty);
                existingBlog.setHashtags(newHashtags);
            }

            if (blogService.updateBlog(existingBlog)) {
                System.out.println("Blog post updated successfully!");
            } else {
                System.out.println("Failed to update blog post. Ensure categories are valid.");
            }
        } catch (Exception e) {
            System.out.println("Error updating blog post: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void deleteBlog(int blogId) {
        try {
            if (blogService.deleteBlog(blogId)) {
                System.out.println("Blog post deleted successfully!");
            } else {
                System.out.println("Failed to delete blog post!");
            }
        } catch (Exception e) {
            System.out.println("Error deleting blog post: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void viewMyBlogs(Senior senior) {
        try {
            List<Blog> blogs = blogService.getBlogByUid(senior.getUid());
            if (blogs == null || blogs.isEmpty()) {
                System.out.println("You have no blog posts.");
            } else {
                System.out.println("\n--- Your Blog Post ---");
                System.out.println(blogs.get(0)); // Only one blog per senior
            }
        } catch (Exception e) {
            System.out.println("Error viewing your blog posts: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void fresherWorkflow() {
        while (true) {
            System.out.println("\n=== Fresher Workflow ===");
            System.out.println("1. Set Preferences and View PG Match Score");
            System.out.println("2. View Blogs");
            System.out.println("3. Back to Main Menu");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    setPreferencesAndViewMatches();
                    break;
                case 2:
                    viewBlogs();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void setPreferencesAndViewMatches() {
        PGPreferences preferences = new PGPreferences();
        
        System.out.println("\n===================================================================================================");
        System.out.println("Enter Weightage for 5 criteria (total Weightage must be 100%)");
        System.out.println("1. Rent | 2. Distance | 3. Area | 4. Amenities | 5. Beds per Room |");
        System.out.println("===================================================================================================");
        
        System.out.print("1. Rent (%): ");
        preferences.setRentWeightage(scanner.nextInt());
        System.out.print("2. Distance (%): ");
        preferences.setDistanceWeightage(scanner.nextInt());
        System.out.print("3. Area (%): ");
        preferences.setAreaWeightage(scanner.nextInt());
        System.out.print("4. Amenities (%): ");
        preferences.setAmenitiesWeightage(scanner.nextInt());
        System.out.print("5. Beds per Room (%): ");
        preferences.setBedsWeightage(scanner.nextInt());
        scanner.nextLine(); // Consume newline

        if (!preferences.validateWeightages()) {
            System.out.println("Error: Total weightage must be 100%. Please try again.");
            return;
        }

        System.out.println("\n===================================================================================================");
        System.out.print("Enter Preferred Rent amount : ");
        preferences.setRentBudget(scanner.nextInt());
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Preferred Distance From College (Kms): ");
        int distanceKm = scanner.nextInt();
        preferences.setDistance(distanceKm * 1000); // Convert to meters
        scanner.nextLine(); // Consume newline

        System.out.println("\n===================================================================================================");
        System.out.println("Enter Area Preferences (1-4, where 1 is Highest Priority and 4 is Lowest Priority):");
        Map<Integer, String> areas = com.spitbay.util.AreaMapper.getAllAreas();
        for (Map.Entry<Integer, String> entry : areas.entrySet()) {
            System.out.printf("Priority for %s : ", entry.getValue());
            int priority = scanner.nextInt();
            preferences.getAreaPriorities().put(entry.getKey(), priority);
        }
        scanner.nextLine(); // Consume newline

        System.out.print("\nEnter Preferred Beds per room: ");
        preferences.setBedsPerRoom(scanner.nextInt());
        scanner.nextLine(); // Consume newline

        System.out.println("\n===================================================================================================");
        System.out.println("Enter Amenities Preferences (1-5, where 1 is Highest Priority and 5 is Lowest Priority):");
        String[] amenities = {"AC", "WiFi", "Geyser", "Laundry", "Parking lot"};
        for (String amenity : amenities) {
            System.out.printf("Priority for %s : ", amenity);
            int priority = scanner.nextInt();
            preferences.getAmenitiesPriorities().put(amenity, priority);
        }
        scanner.nextLine(); // Consume newline

        System.out.print("Enter your Gender (M/F): ");
        preferences.setGender(scanner.nextLine());

        System.out.println("\nCalculating PG Match Scores...");
        List<Map.Entry<PGListing, Double>> matchedPGs = pgMatchmakingService.findMatchingPGs(preferences);

        if (matchedPGs.isEmpty()) {
            System.out.println("No matching PG listings found based on your preferences.");
        } else {
            System.out.println("\n=== PG Match Scores (Sorted by Score) ===");
            int rank = 1;
            for (Map.Entry<PGListing, Double> entry : matchedPGs) {
                PGListing pg = entry.getKey();
                double totalScore = entry.getValue();
                Map<String, Double> individualScores = pgMatchmakingService.getIndividualScores(pg, preferences);

                // Format exactly as shown in the image with proper alignment
                System.out.printf("PG ID: %d | Rent: %d | Distance : %d meters | Area : %s | Beds per Room : %d | Amenities : %s |\n",
                    pg.getId(), pg.getRent(), pg.getDistance(), pg.getAreaName(), pg.getBedsPerRoom(), 
                    String.join(", ", pg.getAmenities()));
                
                System.out.printf("| Score : %.0f/40 | Score : %.2f/20 | Score : %.0f/20 | Score : %.0f/20 | Score : %.0f/20 |\n",
                    individualScores.get("Rent"), individualScores.get("Distance"), 
                    individualScores.get("Area"), individualScores.get("BedsPerRoom"), 
                    individualScores.get("Amenities"));
                
                System.out.printf("Rank : \n");
                System.out.printf("Total Match Score : %.2f\n", totalScore);
                System.out.printf("Addess : %s\n", pg.getAddress());
                System.out.printf("Gender : %s\n", pg.getGender());
                System.out.printf("Note from PG Student : %s\n", pg.getNotes());
                System.out.println("--------------------------------------------------------------------------------");
                rank++;
            }
        }
    }

    private static void viewBlogs() {
        while (true) {
            System.out.println("\n=== Blog Viewing ===");
            System.out.println("1. View All Blogs");
            System.out.println("2. Filter blogs on hashtags and categories");
            System.out.println("3. Back to Fresher Menu");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllBlogs();
                    break;
                case 2:
                    filterBlogsByCategoriesAndHashtags();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewAllBlogs() {
        try {
            List<Blog> allBlogs = blogService.getAllBlogs();
            if (allBlogs.isEmpty()) {
                System.out.println("No blog posts available.");
            } else {
                System.out.println("\n--- All Blog Posts ---");
                for (Blog blog : allBlogs) {
                    System.out.println(blog);
                    System.out.println("------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Error viewing all blogs: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void filterBlogsByCategoriesAndHashtags() {
        try {
            // Show available categories
            List<String> categories = blogService.getFixedCategories();
            System.out.println("\nAvailable Categories:");
            for (int i = 0; i < categories.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, categories.get(i));
            }

            // Get category selections
            List<String> selectedCategories = new ArrayList<>();
            System.out.print("\nEnter category numbers (comma-separated, or press Enter to skip): ");
            String categoryInput = scanner.nextLine().trim();
            if (!categoryInput.isEmpty()) {
                String[] categoryIndices = categoryInput.split(",");
                for (String index : categoryIndices) {
                    try {
                        int idx = Integer.parseInt(index.trim()) - 1;
                        if (idx >= 0 && idx < categories.size()) {
                            selectedCategories.add(categories.get(idx));
                        }
                    } catch (NumberFormatException e) {
                        // Skip invalid numbers
                    }
                }
            }

            // Show available hashtags
            Set<String> hashtags = blogService.getAllHashtags();
            if (!hashtags.isEmpty()) {
                System.out.println("\nAvailable Hashtags:");
                List<String> hashtagList = new ArrayList<>(hashtags);
                for (int i = 0; i < hashtagList.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, hashtagList.get(i));
                }

                // Get hashtag selections
                List<String> selectedHashtags = new ArrayList<>();
                System.out.print("\nEnter hashtag numbers (comma-separated, or press Enter to skip): ");
                String hashtagInput = scanner.nextLine().trim();
                if (!hashtagInput.isEmpty()) {
                    String[] hashtagIndices = hashtagInput.split(",");
                    for (String index : hashtagIndices) {
                        try {
                            int idx = Integer.parseInt(index.trim()) - 1;
                            if (idx >= 0 && idx < hashtagList.size()) {
                                selectedHashtags.add(hashtagList.get(idx));
                            }
                        } catch (NumberFormatException e) {
                            // Skip invalid numbers
                        }
                    }
                }

                // Get filtered blogs based on the cases
                List<Blog> filteredBlogs;
                
                if (!selectedCategories.isEmpty() && !selectedHashtags.isEmpty()) {
                    // Case 4: Both categories and hashtags selected
                    // Get blogs matching both criteria (no duplicates)
                    filteredBlogs = blogService.getBlogsByCategoriesAndHashtags(selectedCategories, selectedHashtags);
                } else if (!selectedCategories.isEmpty()) {
                    // Case 1: Only categories selected
                    filteredBlogs = blogService.getBlogsByCategories(selectedCategories);
                } else if (!selectedHashtags.isEmpty()) {
                    // Case 2: Only hashtags selected
                    filteredBlogs = blogService.getBlogsByHashtags(new HashSet<>(selectedHashtags));
                } else {
                    // Case 3: No filters selected
                    System.out.println("\nNo filters selected. Please select at least one category or hashtag.");
                    return;
                }

                // Display filtered blogs
                if (filteredBlogs.isEmpty()) {
                    System.out.println("\nNo blog posts found matching the selected criteria.");
                } else {
                    System.out.println("\n--- Filtered Blog Posts ---");
                    for (Blog blog : filteredBlogs) {
                        System.out.println(blog);
                        System.out.println("------------------------");
                    }
                }
            } else {
                System.out.println("\nNo hashtags available.");
                // If no hashtags available but categories are selected, show category results
                if (!selectedCategories.isEmpty()) {
                    List<Blog> filteredBlogs = blogService.getBlogsByCategories(selectedCategories);
                    if (filteredBlogs.isEmpty()) {
                        System.out.println("\nNo blog posts found for the selected categories.");
                    } else {
                        System.out.println("\n--- Filtered Blog Posts ---");
                        for (Blog blog : filteredBlogs) {
                            System.out.println(blog);
                            System.out.println("------------------------");
                        }
                    }
                } else {
                    System.out.println("\nNo filters selected. Please select at least one category.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error filtering blogs: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 