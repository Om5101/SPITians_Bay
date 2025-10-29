package com.spitbay.view;

import com.spitbay.model.Blog;
import java.util.List;

// Simple view class for blog displays
public class BlogView {
    
    // Display all blogs
    public static void displayBlogs(List<Blog> blogs) {
        if (blogs.isEmpty()) {
            System.out.println("No blogs found.");
            return;
        }
        
        System.out.println("\n=== Blogs ===");
        for (Blog blog : blogs) {
            displaySingleBlog(blog);
            System.out.println("----------------------------------------");
        }
    }
    
    // Display single blog
    public static void displaySingleBlog(Blog blog) {
        System.out.println(blog.toString());
    }
    
    // Display blog categories selection
    public static void showAvailableCategories(List<String> categories) {
        System.out.println("Available Categories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }
    }
    
    // Display blog creation form
    public static void showAddBlogForm() {
        System.out.println("\n=== Add New Blog ===");
    }
    
    // Display user's blogs
    public static void displayUserBlogs(List<Blog> blogs) {
        if (!blogs.isEmpty()) {
            System.out.println("\n=== Your Blogs ===");
            for (Blog blog : blogs) {
                System.out.println(blog.toString());
            }
        }
    }
    
    // Display blog management menu
    public static void showBlogManagement() {
        System.out.println("\n1. Add New Blog");
        System.out.println("2. Back to Dashboard");
        System.out.print("Enter choice: ");
    }
}
