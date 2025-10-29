package com.spitbay.view;

// Simple view class for menu displays
public class MenuView {
    
    // Display main menu
    public static void showMainMenu() {
        System.out.println("\n=== SPITian's Bay ===");
        System.out.println("1. Senior (Post PG listings and blogs)");
        System.out.println("2. Fresher (Search for PGs and read blogs)");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }
    
    // Display welcome message
    public static void showWelcome() {
        System.out.println("=== Welcome to SPITian's Bay - PG Search Platform ===");
        System.out.println("Solving the broker problem for engineering students in Mumbai");
    }
    
    // Display goodbye message
    public static void showGoodbye() {
        System.out.println("Thank you for using SPITian's Bay!");
    }
    
    // Display senior menu
    public static void showSeniorMenu() {
        System.out.println("\n=== Senior Menu ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Back to Main Menu");
        System.out.print("Enter choice: ");
    }
    
    // Display fresher menu
    public static void showFresherMenu() {
        System.out.println("\n=== Fresher Menu ===");
        System.out.println("1. Search for PGs");
        System.out.println("2. View Blogs");
        System.out.println("3. Back to Main Menu");
        System.out.print("Enter choice: ");
    }
    
    // Display senior dashboard
    public static void showSeniorDashboard() {
        System.out.println("\n=== Senior Dashboard ===");
        System.out.println("1. Manage PG Listings");
        System.out.println("2. Manage Blogs");
        System.out.println("3. Logout");
        System.out.print("Enter choice: ");
    }
    
    // Display blog viewing menu
    public static void showBlogMenu() {
        System.out.println("\n=== View Blogs ===");
        System.out.println("1. View All Blogs");
        System.out.println("2. Search by Categories");
        System.out.println("3. Search by Hashtags");
        System.out.println("4. Back to Fresher Menu");
        System.out.print("Enter choice: ");
    }
    
    // Display error message
    public static void showError(String message) {
        System.out.println("Error: " + message);
    }
    
    // Display success message
    public static void showSuccess(String message) {
        System.out.println("Success: " + message);
    }
}
