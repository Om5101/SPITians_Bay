package com.spitbay;

import com.spitbay.controller.MainController;

// Main entry point for SPITian's Bay application
public class Main {
    public static void main(String[] args) {
        try {
            MainController controller = new MainController();
            controller.start();
        } catch (Exception e) {
            System.err.println("Application error: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 