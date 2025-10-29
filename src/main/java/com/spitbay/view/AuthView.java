package com.spitbay.view;

// Simple view class for authentication displays
public class AuthView {
    
    // Display login success
    public static void showLoginSuccess() {
        System.out.println("Login successful!");
    }
    
    // Display login failure
    public static void showLoginFailure() {
        System.out.println("Invalid credentials!");
    }
    
    // Display registration success
    public static void showRegistrationSuccess() {
        System.out.println("Registration successful!");
    }
    
    // Display registration failure
    public static void showRegistrationFailure() {
        System.out.println("Registration failed!");
    }
    
    // Display user exists message
    public static void showUserExists() {
        System.out.println("User with this UID already exists!");
    }
    
    // Display invalid UID format message
    public static void showInvalidUidFormat() {
        System.out.println("Invalid UID format! Must be a valid email ending with '@spit.ac.in'.");
    }
    
    // Display unauthorized UID message
    public static void showUnauthorizedUid() {
        System.out.println("UID not authorized for registration! Please contact administrator.");
    }
    
    // Display login error
    public static void showLoginError(String message) {
        System.out.println("Login error: " + message);
    }
    
    // Display registration error
    public static void showRegistrationError(String message) {
        System.out.println("Registration error: " + message);
    }
    
    // Display invalid choice message
    public static void showInvalidChoice() {
        System.out.println("Invalid choice. Please try again.");
    }
}
