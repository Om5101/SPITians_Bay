package com.spitbay.dao;

import com.spitbay.model.Blog;
import com.spitbay.util.DatabaseConnection;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class BlogDAOImpl implements BlogDAO {
    private Connection connection;

    public BlogDAOImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    @Override
    public boolean addBlog(Blog blog) {
        String sql = "INSERT INTO blogs (uid, content) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, blog.getUid());
            stmt.setString(2, blog.getContent());
            
            if (stmt.executeUpdate() > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int blogId = rs.getInt(1);
                    blog.setId(blogId);
                    
                    // Add categories
                    addCategories(blogId, blog.getCategories());
                    
                    // Add hashtags
                    addHashtags(blogId, blog.getHashtags());
                    
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBlog(Blog blog) {
        String sql = "UPDATE blogs SET content = ? WHERE id = ? AND uid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, blog.getContent());
            stmt.setInt(2, blog.getId());
            stmt.setString(3, blog.getUid());
            
            if (stmt.executeUpdate() > 0) {
                // Update categories
                deleteCategories(blog.getId());
                addCategories(blog.getId(), blog.getCategories());
                
                // Update hashtags
                deleteHashtags(blog.getId());
                addHashtags(blog.getId(), blog.getHashtags());
                
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBlog(int blogId) {
        String sql = "DELETE FROM blogs WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, blogId);
            
            // Delete categories and hashtags first
            deleteCategories(blogId);
            deleteHashtags(blogId);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Blog getBlog(int blogId) {
        String sql = "SELECT * FROM blogs WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, blogId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                Blog blog = new Blog(
                    rs.getString("uid"),
                    rs.getString("content"),
                    getCategories(blogId),
                    getHashtags(blogId)
                );
                blog.setId(blogId);
                return blog;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Blog> getBlogByUid(String uid) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM blogs WHERE uid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, uid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int blogId = rs.getInt("id");
                Blog blog = new Blog(
                    rs.getString("uid"),
                    rs.getString("content"),
                    getCategories(blogId),
                    getHashtags(blogId)
                );
                blog.setId(blogId);
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM blogs";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                int blogId = rs.getInt("id");
                Blog blog = new Blog(
                    rs.getString("uid"),
                    rs.getString("content"),
                    getCategories(blogId),
                    getHashtags(blogId)
                );
                blog.setId(blogId);
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    @Override
    public List<Blog> getBlogsByCategories(List<String> categories) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT DISTINCT b.* FROM blogs b " +
                    "JOIN blog_categories bc ON b.id = bc.blog_id " +
                    "WHERE bc.category IN (" + categories.stream().map(c -> "?").collect(Collectors.joining(",")) + ")";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int i = 0; i < categories.size(); i++) {
                stmt.setString(i + 1, categories.get(i));
            }
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int blogId = rs.getInt("id");
                Blog blog = new Blog(
                    rs.getString("uid"),
                    rs.getString("content"),
                    getCategories(blogId),
                    getHashtags(blogId)
                );
                blog.setId(blogId);
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    @Override
    public List<Blog> getBlogsByHashtags(Set<String> hashtags) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT DISTINCT b.* FROM blogs b " +
                    "JOIN blog_hashtags bh ON b.id = bh.blog_id " +
                    "WHERE bh.hashtag IN (" + hashtags.stream().map(h -> "?").collect(Collectors.joining(",")) + ")";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            int i = 1;
            for (String hashtag : hashtags) {
                stmt.setString(i++, hashtag);
            }
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int blogId = rs.getInt("id");
                Blog blog = new Blog(
                    rs.getString("uid"),
                    rs.getString("content"),
                    getCategories(blogId),
                    getHashtags(blogId)
                );
                blog.setId(blogId);
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    @Override
    public Set<String> getAllHashtags() {
        Set<String> hashtags = new HashSet<>();
        String sql = "SELECT DISTINCT hashtag FROM blog_hashtags";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                hashtags.add(rs.getString("hashtag"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hashtags;
    }

    private void addCategories(int blogId, List<String> categories) throws SQLException {
        String sql = "INSERT INTO blog_categories (blog_id, category) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (String category : categories) {
                stmt.setInt(1, blogId);
                stmt.setString(2, category);
                stmt.executeUpdate();
            }
        }
    }

    private void deleteCategories(int blogId) throws SQLException {
        String sql = "DELETE FROM blog_categories WHERE blog_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, blogId);
            stmt.executeUpdate();
        }
    }

    private List<String> getCategories(int blogId) {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT category FROM blog_categories WHERE blog_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, blogId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                categories.add(rs.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    private void addHashtags(int blogId, Set<String> hashtags) throws SQLException {
        String sql = "INSERT INTO blog_hashtags (blog_id, hashtag) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (String hashtag : hashtags) {
                stmt.setInt(1, blogId);
                stmt.setString(2, hashtag);
                stmt.executeUpdate();
            }
        }
    }

    private void deleteHashtags(int blogId) throws SQLException {
        String sql = "DELETE FROM blog_hashtags WHERE blog_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, blogId);
            stmt.executeUpdate();
        }
    }

    private Set<String> getHashtags(int blogId) {
        Set<String> hashtags = new HashSet<>();
        String sql = "SELECT hashtag FROM blog_hashtags WHERE blog_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, blogId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                hashtags.add(rs.getString("hashtag"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hashtags;
    }
}