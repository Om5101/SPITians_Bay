package com.spitbay.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Blog {
    private int id;
    private String uid;
    private String content;
    private List<String> categories; // From 5 fixed categories
    private Set<String> hashtags; // Dynamic hashtags

    public Blog(String uid, String content, List<String> categories, Set<String> hashtags) {
        this.uid = uid;
        this.content = content;
        this.categories = categories;
        this.hashtags = hashtags;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getUid() { return uid; }
    public void setUid(String uid) { this.uid = uid; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public List<String> getCategories() { return categories; }
    public void setCategories(List<String> categories) { this.categories = categories; }
    
    public Set<String> getHashtags() { return hashtags; }
    public void setHashtags(Set<String> hashtags) { this.hashtags = hashtags; }

    @Override
    public String toString() {
        return String.format("Blog [ID: %d]\n" +
                           "Content: %s\n" +
                           "Categories: %s\n" +
                           "Hashtags: %s",
                           id, content, 
                           String.join(", ", categories),
                           String.join(", ", hashtags));
    }
} 