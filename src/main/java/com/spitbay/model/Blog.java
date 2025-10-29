package com.spitbay.model;

import java.util.List;
import java.util.Set;

// Blog model for community sharing
public class Blog {
    private int id;
    private String authorUid;
    private String content;
    private List<String> categories;
    private Set<String> hashtags;
    
    public Blog(String authorUid, String content, List<String> categories, Set<String> hashtags) {
        this.authorUid = authorUid;
        this.content = content;
        this.categories = categories;
        this.hashtags = hashtags;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getAuthorUid() { return authorUid; }
    public void setAuthorUid(String authorUid) { this.authorUid = authorUid; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public List<String> getCategories() { return categories; }
    public void setCategories(List<String> categories) { this.categories = categories; }
    
    public Set<String> getHashtags() { return hashtags; }
    public void setHashtags(Set<String> hashtags) { this.hashtags = hashtags; }
    
    // Validate blog data
    public boolean isValid() {
        return authorUid != null && !authorUid.trim().isEmpty() &&
               content != null && !content.trim().isEmpty() &&
               categories != null && !categories.isEmpty();
    }
    
    // Check if blog matches given hashtag
    public boolean containsHashtag(String hashtag) {
        return hashtags != null && hashtags.contains(hashtag.toLowerCase());
    }
    
    // Check if blog belongs to any category
    public boolean belongsToCategory(String category) {
        if (categories == null) {
            return false;
        }
        
        for (int i = 0; i < categories.size(); i++) {
            String c = categories.get(i);
            if (c != null && c.equalsIgnoreCase(category)) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        String categoryStr = "None";
        if (categories != null && categories.size() > 0) {
            StringBuilder catBuilder = new StringBuilder();
            for (int i = 0; i < categories.size(); i++) {
                if (i > 0) catBuilder.append(", ");
                catBuilder.append(categories.get(i));
            }
            categoryStr = catBuilder.toString();
        }
        
        String hashtagStr = "None";
        if (hashtags != null && hashtags.size() > 0) {
            StringBuilder hashBuilder = new StringBuilder();
            int count = 0;
            for (String hashtag : hashtags) {
                if (count > 0) hashBuilder.append(", ");
                hashBuilder.append(hashtag);
                count++;
            }
            hashtagStr = hashBuilder.toString();
        }
                
        return String.format("Blog #%d: %s\nCategories: %s\nHashtags: %s", 
                           id, content, categoryStr, hashtagStr);
    }
}
