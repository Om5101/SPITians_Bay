package model;

import java.util.ArrayList;
import java.util.List;

public class Blog {
    private int blogId;
    private String uid;
    private String content;
    private List<String> categories;
    private List<String> hashtags;
    
    public Blog() {
        this.categories = new ArrayList<>();
        this.hashtags = new ArrayList<>();
    }
    
    public Blog(String uid, String content) {
        this.uid = uid;
        this.content = content;
        this.categories = new ArrayList<>();
        this.hashtags = new ArrayList<>();
    }
    
    // Getters and Setters
    public int getBlogId() {
        return blogId;
    }
    
    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }
    
    public String getUid() {
        return uid;
    }
    
    public void setUid(String uid) {
        this.uid = uid;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public List<String> getCategories() {
        return categories;
    }
    
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
    
    public List<String> getHashtags() {
        return hashtags;
    }
    
    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }
    
    public void addCategory(String category) {
        this.categories.add(category);
    }
    
    public void addHashtag(String hashtag) {
        this.hashtags.add(hashtag);
    }
} 