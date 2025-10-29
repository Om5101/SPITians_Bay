package com.spitbay.service;

import com.spitbay.dao.BlogDAO;
import com.spitbay.model.Blog;
import java.util.*;

// Service for blog operations with hashtag-based search
public class BlogService {
    private final BlogDAO blogDAO;
    private static final List<String> CATEGORIES = Arrays.asList(
        "Campus Life", "Academic Experience", "Housing Tips", "Local Guide", "Student Resources"
    );
    
    public BlogService(BlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }
    
    // Add new blog post
    public boolean addBlog(Blog blog) {
        if (!blog.isValid()) {
            throw new IllegalArgumentException("Invalid blog data");
        }
        
        return blogDAO.insertBlog(blog);
    }
    
    // Get blogs by author
    public List<Blog> getBlogsByAuthor(String uid) {
        return blogDAO.findBlogsByAuthor(uid);
    }
    
    // Get all blogs
    public List<Blog> getAllBlogs() {
        return blogDAO.findAllBlogs();
    }
    
    // Search blogs by hashtags using HashSet for fast lookup (optimized)
    public List<Blog> searchBlogsByHashtags(Set<String> searchHashtags) {
        List<Blog> allBlogs = getAllBlogs(); // Single database call
        
        if (searchHashtags == null || searchHashtags.isEmpty()) {
            return allBlogs;
        }
        
        List<Blog> matchingBlogs = new ArrayList<Blog>();
        
        for (int i = 0; i < allBlogs.size(); i++) {
            Blog blog = allBlogs.get(i);
            Set<String> blogHashtags = blog.getHashtags();
            
            if (blogHashtags != null && blogHashtags.size() > 0) {
                boolean hasMatch = false;
                
                // Check if any blog hashtag matches any search hashtag
                for (String blogHashtag : blogHashtags) {
                    if (hasMatch) break;
                    
                    for (String searchHashtag : searchHashtags) {
                        String blogHashtagLower = blogHashtag.toLowerCase();
                        String searchHashtagLower = searchHashtag.toLowerCase();
                        
                        if (blogHashtagLower.contains(searchHashtagLower)) {
                            hasMatch = true;
                            break;
                        }
                    }
                }
                
                if (hasMatch) {
                    matchingBlogs.add(blog);
                }
            }
        }
        
        return matchingBlogs;
    }
    
    // Search blogs by categories
    public List<Blog> searchBlogsByCategories(List<String> categories) {
        return blogDAO.findBlogsByCategories(categories);
    }
    
    // Get available categories
    public List<String> getAvailableCategories() {
        return new ArrayList<>(CATEGORIES);
    }
}