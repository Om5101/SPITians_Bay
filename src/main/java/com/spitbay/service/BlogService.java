package com.spitbay.service;

import com.spitbay.model.Blog;
import com.spitbay.dao.BlogDAO;
import com.spitbay.dao.BlogDAOImpl;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.sql.SQLException;

public class BlogService {
    private BlogDAO blogDAO;
    private static final List<String> FIXED_CATEGORIES = List.of(
        "Campus Life",
        "Academic Experience",
        "Housing Tips",
        "Local Guide",
        "Student Resources"
    );

    public BlogService() throws SQLException {
        this.blogDAO = new BlogDAOImpl();
    }

    public boolean addBlog(Blog blog) {
        // Validate categories
        for (String category : blog.getCategories()) {
            if (!FIXED_CATEGORIES.contains(category)) {
                return false;
            }
        }
        return blogDAO.addBlog(blog);
    }

    public boolean updateBlog(Blog blog) {
        // Validate categories
        for (String category : blog.getCategories()) {
            if (!FIXED_CATEGORIES.contains(category)) {
                return false;
            }
        }
        return blogDAO.updateBlog(blog);
    }

    public boolean deleteBlog(int blogId) {
        return blogDAO.deleteBlog(blogId);
    }

    public Blog getBlog(int blogId) {
        return blogDAO.getBlog(blogId);
    }

    public List<Blog> getBlogByUid(String uid) {
        return blogDAO.getBlogByUid(uid);
    }

    public List<Blog> getAllBlogs() {
        return blogDAO.getAllBlogs();
    }

    public List<Blog> getBlogsByCategories(List<String> categories) {
        return blogDAO.getBlogsByCategories(categories);
    }

    public List<Blog> getBlogsByHashtags(Set<String> hashtags) {
        return blogDAO.getBlogsByHashtags(hashtags);
    }

    public List<Blog> getBlogsByCategoriesAndHashtags(List<String> categories, List<String> hashtags) {
        // First get blogs by categories
        List<Blog> blogsByCategories = getBlogsByCategories(categories);
        
        // Then filter by hashtags
        Set<String> hashtagSet = new HashSet<>(hashtags);
        List<Blog> filteredBlogs = new ArrayList<>();
        
        for (Blog blog : blogsByCategories) {
            // Check if blog contains any of the selected hashtags
            boolean hasMatchingHashtag = false;
            for (String hashtag : hashtagSet) {
                if (blog.getHashtags().contains(hashtag)) {
                    hasMatchingHashtag = true;
                    break;
                }
            }
            if (hasMatchingHashtag) {
                filteredBlogs.add(blog);
            }
        }
        
        return filteredBlogs;
    }

    public List<String> getFixedCategories() {
        return new ArrayList<>(FIXED_CATEGORIES);
    }

    public Set<String> getAllHashtags() {
        return blogDAO.getAllHashtags();
    }
} 