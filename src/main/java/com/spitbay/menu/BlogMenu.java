package com.spitbay.menu;

import com.spitbay.model.Blog;
import com.spitbay.service.BlogService;
import java.util.*;
import java.sql.SQLException;

public class BlogMenu {
    private BlogService blogService;
    private Scanner scanner;

    public BlogMenu() throws SQLException {
        this.blogService = new BlogService();
        this.scanner = new Scanner(System.in);
    }

    public void showBlogMenu() {
        while (true) {
            System.out.println("\n=== Blog Menu ===");
            System.out.println("1. View and Filter Blogs");
            System.out.println("2. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAndFilterBlogs();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewAndFilterBlogs() {
        System.out.println("\n=== View and Filter Blogs ===");
        System.out.println("Available Categories:");
        List<String> categories = blogService.getFixedCategories();
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }

        List<String> selectedCategories = new ArrayList<>();
        System.out.print("Enter category numbers (comma-separated) or press Enter to skip: ");
        String categoryInput = scanner.nextLine().trim();
        if (!categoryInput.isEmpty()) {
            String[] categoryNumbers = categoryInput.split(",");
            for (String num : categoryNumbers) {
                try {
                    int index = Integer.parseInt(num.trim()) - 1;
                    if (index >= 0 && index < categories.size()) {
                        selectedCategories.add(categories.get(index));
                    }
                } catch (NumberFormatException e) {
                    // Skip invalid numbers
                }
            }
        }

        System.out.print("Enter hashtags (comma-separated) or press Enter to skip: ");
        String hashtagInput = scanner.nextLine().trim();
        Set<String> selectedHashtags = new HashSet<>();
        if (!hashtagInput.isEmpty()) {
            String[] hashtags = hashtagInput.split(",");
            for (String hashtag : hashtags) {
                selectedHashtags.add(hashtag.trim());
            }
        }

        List<Blog> filteredBlogs;
        if (selectedCategories.isEmpty() && selectedHashtags.isEmpty()) {
            filteredBlogs = blogService.getAllBlogs();
        } else if (selectedCategories.isEmpty()) {
            filteredBlogs = blogService.getBlogsByHashtags(selectedHashtags);
        } else if (selectedHashtags.isEmpty()) {
            filteredBlogs = blogService.getBlogsByCategories(selectedCategories);
        } else {
            // Combine results from both filters
            List<Blog> categoryBlogs = blogService.getBlogsByCategories(selectedCategories);
            List<Blog> hashtagBlogs = blogService.getBlogsByHashtags(selectedHashtags);
            filteredBlogs = new ArrayList<>(categoryBlogs);
            filteredBlogs.retainAll(hashtagBlogs);
        }

        System.out.println("\n=== Blog Results ===");
        if (filteredBlogs.isEmpty()) {
            System.out.println("No blogs found matching the criteria.");
        } else {
            displayBlogs(filteredBlogs);
        }
    }

    private void displayBlogs(List<Blog> blogs) {
        for (Blog blog : blogs) {
            System.out.println("\nBlog ID: " + blog.getId());
            System.out.println("Content: " + blog.getContent());
            System.out.println("Categories: " + String.join(", ", blog.getCategories()));
            System.out.println("Hashtags: " + String.join(", ", blog.getHashtags()));
            System.out.println("----------------------------------------");
        }
    }
} 