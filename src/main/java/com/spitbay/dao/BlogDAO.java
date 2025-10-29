package com.spitbay.dao;

import com.spitbay.database.DatabaseConnection;
import com.spitbay.model.Blog;
import java.sql.*;
import java.util.*;

// Optimized DAO for Blog operations
public class BlogDAO {
    private final DatabaseConnection dbConnection;
    
    public BlogDAO() {
        this.dbConnection = DatabaseConnection.getInstance(); // Use singleton instance
    }
    
    // Insert new blog
    public boolean insertBlog(Blog blog) {
        String sql = "INSERT INTO blogs (uid, content) VALUES (?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, blog.getAuthorUid());
            stmt.setString(2, blog.getContent());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        blog.setId(generatedKeys.getInt(1));
                        insertCategories(blog.getId(), blog.getCategories());
                        if (blog.getHashtags() != null && !blog.getHashtags().isEmpty()) {
                            insertHashtags(blog.getId(), blog.getHashtags());
                        }
                    }
                }
            }
            
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Database error inserting blog", e);
        }
    }
    
    // Get all blogs
    public List<Blog> findAllBlogs() {
        String sql = "SELECT * FROM blogs ORDER BY id DESC";
        List<Blog> blogs = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Blog blog = createBlogFromResultSet(rs);
                    if (blog != null) {
                        blogs.add(blog);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error finding all blogs", e);
        }
        
        return blogs;
    }
    
    // Get blogs by author
    public List<Blog> findBlogsByAuthor(String uid) {
        String sql = "SELECT * FROM blogs WHERE uid = ?";
        List<Blog> blogs = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, uid);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Blog blog = createBlogFromResultSet(rs);
                    if (blog != null) {
                        blogs.add(blog);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error finding blogs by author", e);
        }
        
        return blogs;
    }
    
    // Get blogs by categories
    public List<Blog> findBlogsByCategories(List<String> categories) {
        if (categories == null || categories.isEmpty()) {
            return findAllBlogs();
        }
        
        String sql = "SELECT DISTINCT b.* FROM blogs b JOIN blog_categories bc " +
                    "ON b.id = bc.blog_id WHERE bc.category IN (" +
                    String.join(",", Collections.nCopies(categories.size(), "?")) + ")";
        
        List<Blog> blogs = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            for (int i = 0; i < categories.size(); i++) {
                stmt.setString(i + 1, categories.get(i));
            }
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Blog blog = createBlogFromResultSet(rs);
                    if (blog != null) {
                        blogs.add(blog);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error finding blogs by category", e);
        }
        
        return blogs;
    }
    
    // Insert categories for blog
    private void insertCategories(int blogId, List<String> categories) {
        String sql = "INSERT INTO blog_categories (blog_id, category) VALUES (?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            for (String category : categories) {
                if (category != null && !category.trim().isEmpty()) {
                    stmt.setInt(1, blogId);
                    stmt.setString(2, category.trim());
                    stmt.addBatch();
                }
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("Database error inserting categories", e);
        }
    }
    
    // Insert hashtags for blog
    private void insertHashtags(int blogId, Set<String> hashtags) {
        String sql = "INSERT INTO blog_hashtags (blog_id, hashtag) VALUES (?, ?)";
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            for (String hashtag : hashtags) {
                if (hashtag != null && !hashtag.trim().isEmpty()) {
                    stmt.setInt(1, blogId);
                    stmt.setString(2, hashtag.trim());
                    stmt.addBatch();
                }
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("Database error inserting hashtags", e);
        }
    }
    
    // Get categories for blog
    private List<String> getCategoriesForBlog(int blogId) {
        String sql = "SELECT category FROM blog_categories WHERE blog_id = ?";
        List<String> categories = new ArrayList<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, blogId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    categories.add(rs.getString("category"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error fetching categories", e);
        }
        
        return categories;
    }
    
    // Get hashtags for blog
    private Set<String> getHashtagsForBlog(int blogId) {
        String sql = "SELECT hashtag FROM blog_hashtags WHERE blog_id = ?";
        Set<String> hashtags = new HashSet<>();
        
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, blogId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    hashtags.add(rs.getString("hashtag"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error fetching hashtags", e);
        }
        
        return hashtags;
    }
    
    // Create Blog object from ResultSet
    private Blog createBlogFromResultSet(ResultSet rs) throws SQLException {
        int blogId = rs.getInt("id");
        String authorUid = rs.getString("uid");
        String content = rs.getString("content");
        
        Blog blog = new Blog(authorUid, content, new ArrayList<>(), new HashSet<>());
        blog.setId(blogId);
        blog.setCategories(getCategoriesForBlog(blogId));
        blog.setHashtags(getHashtagsForBlog(blogId));
        
        return blog;
    }
}
