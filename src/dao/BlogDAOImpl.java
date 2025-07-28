package dao;

import model.Blog;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogDAOImpl implements BlogDAO {
    private Connection connection;
    
    public BlogDAOImpl() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    
    @Override
    public boolean addBlog(Blog blog) {
        String blogQuery = "INSERT INTO blogs (uid, content) VALUES (?, ?)";
        String categoryQuery = "INSERT INTO blog_categories (blog_id, category_name) VALUES (?, ?)";
        String hashtagQuery = "INSERT INTO blog_hashtags (blog_id, hashtag) VALUES (?, ?)";
        
        try {
            connection.setAutoCommit(false);
            
            // Insert blog
            try (PreparedStatement stmt = connection.prepareStatement(blogQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, blog.getUid());
                stmt.setString(2, blog.getContent());
                if (stmt.executeUpdate() > 0) {
                    ResultSet rs = stmt.getGeneratedKeys();
                    if (rs.next()) {
                        int blogId = rs.getInt(1);
                        
                        // Insert categories
                        try (PreparedStatement catStmt = connection.prepareStatement(categoryQuery)) {
                            for (String category : blog.getCategories()) {
                                catStmt.setInt(1, blogId);
                                catStmt.setString(2, category);
                                catStmt.addBatch();
                            }
                            catStmt.executeBatch();
                        }
                        
                        // Insert hashtags
                        try (PreparedStatement tagStmt = connection.prepareStatement(hashtagQuery)) {
                            for (String hashtag : blog.getHashtags()) {
                                tagStmt.setInt(1, blogId);
                                tagStmt.setString(2, hashtag);
                                tagStmt.addBatch();
                            }
                            tagStmt.executeBatch();
                        }
                        
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public boolean updateBlog(Blog blog) {
        String blogQuery = "UPDATE blogs SET content = ? WHERE blog_id = ? AND uid = ?";
        String deleteCategoriesQuery = "DELETE FROM blog_categories WHERE blog_id = ?";
        String deleteHashtagsQuery = "DELETE FROM blog_hashtags WHERE blog_id = ?";
        String categoryQuery = "INSERT INTO blog_categories (blog_id, category_name) VALUES (?, ?)";
        String hashtagQuery = "INSERT INTO blog_hashtags (blog_id, hashtag) VALUES (?, ?)";
        
        try {
            connection.setAutoCommit(false);
            
            // Update blog content
            try (PreparedStatement stmt = connection.prepareStatement(blogQuery)) {
                stmt.setString(1, blog.getContent());
                stmt.setInt(2, blog.getBlogId());
                stmt.setString(3, blog.getUid());
                if (stmt.executeUpdate() > 0) {
                    // Delete existing categories and hashtags
                    try (PreparedStatement delCatStmt = connection.prepareStatement(deleteCategoriesQuery);
                         PreparedStatement delTagStmt = connection.prepareStatement(deleteHashtagsQuery)) {
                        delCatStmt.setInt(1, blog.getBlogId());
                        delTagStmt.setInt(1, blog.getBlogId());
                        delCatStmt.executeUpdate();
                        delTagStmt.executeUpdate();
                    }
                    
                    // Insert new categories
                    try (PreparedStatement catStmt = connection.prepareStatement(categoryQuery)) {
                        for (String category : blog.getCategories()) {
                            catStmt.setInt(1, blog.getBlogId());
                            catStmt.setString(2, category);
                            catStmt.addBatch();
                        }
                        catStmt.executeBatch();
                    }
                    
                    // Insert new hashtags
                    try (PreparedStatement tagStmt = connection.prepareStatement(hashtagQuery)) {
                        for (String hashtag : blog.getHashtags()) {
                            tagStmt.setInt(1, blog.getBlogId());
                            tagStmt.setString(2, hashtag);
                            tagStmt.addBatch();
                        }
                        tagStmt.executeBatch();
                    }
                    
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public boolean deleteBlog(int blogId) {
        String query = "DELETE FROM blogs WHERE blog_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, blogId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public Blog getBlog(int blogId) {
        String query = "SELECT b.*, GROUP_CONCAT(DISTINCT bc.category_name) as categories, " +
                      "GROUP_CONCAT(DISTINCT bh.hashtag) as hashtags " +
                      "FROM blogs b " +
                      "LEFT JOIN blog_categories bc ON b.blog_id = bc.blog_id " +
                      "LEFT JOIN blog_hashtags bh ON b.blog_id = bh.blog_id " +
                      "WHERE b.blog_id = ? " +
                      "GROUP BY b.blog_id";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, blogId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractBlogFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Blog getBlogByUid(String uid) {
        String query = "SELECT b.*, GROUP_CONCAT(DISTINCT bc.category_name) as categories, " +
                      "GROUP_CONCAT(DISTINCT bh.hashtag) as hashtags " +
                      "FROM blogs b " +
                      "LEFT JOIN blog_categories bc ON b.blog_id = bc.blog_id " +
                      "LEFT JOIN blog_hashtags bh ON b.blog_id = bh.blog_id " +
                      "WHERE b.uid = ? " +
                      "GROUP BY b.blog_id";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, uid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractBlogFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT b.*, GROUP_CONCAT(DISTINCT bc.category_name) as categories, " +
                      "GROUP_CONCAT(DISTINCT bh.hashtag) as hashtags " +
                      "FROM blogs b " +
                      "LEFT JOIN blog_categories bc ON b.blog_id = bc.blog_id " +
                      "LEFT JOIN blog_hashtags bh ON b.blog_id = bh.blog_id " +
                      "GROUP BY b.blog_id";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                blogs.add(extractBlogFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }
    
    @Override
    public List<Blog> getBlogsByCategories(List<String> categories) {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT b.*, GROUP_CONCAT(DISTINCT bc.category_name) as categories, " +
                      "GROUP_CONCAT(DISTINCT bh.hashtag) as hashtags " +
                      "FROM blogs b " +
                      "LEFT JOIN blog_categories bc ON b.blog_id = bc.blog_id " +
                      "LEFT JOIN blog_hashtags bh ON b.blog_id = bh.blog_id " +
                      "WHERE bc.category_name IN (" + String.join(",", categories.stream().map(c -> "?").toArray(String[]::new)) + ") " +
                      "GROUP BY b.blog_id";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 0; i < categories.size(); i++) {
                stmt.setString(i + 1, categories.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                blogs.add(extractBlogFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }
    
    @Override
    public List<Blog> getBlogsByHashtags(List<String> hashtags) {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT b.*, GROUP_CONCAT(DISTINCT bc.category_name) as categories, " +
                      "GROUP_CONCAT(DISTINCT bh.hashtag) as hashtags " +
                      "FROM blogs b " +
                      "LEFT JOIN blog_categories bc ON b.blog_id = bc.blog_id " +
                      "LEFT JOIN blog_hashtags bh ON b.blog_id = bh.blog_id " +
                      "WHERE bh.hashtag IN (" + String.join(",", hashtags.stream().map(h -> "?").toArray(String[]::new)) + ") " +
                      "GROUP BY b.blog_id";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 0; i < hashtags.size(); i++) {
                stmt.setString(i + 1, hashtags.get(i));
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                blogs.add(extractBlogFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }
    
    @Override
    public List<Blog> getBlogsByCategoriesAndHashtags(List<String> categories, List<String> hashtags) {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT b.*, GROUP_CONCAT(DISTINCT bc.category_name) as categories, " +
                      "GROUP_CONCAT(DISTINCT bh.hashtag) as hashtags " +
                      "FROM blogs b " +
                      "LEFT JOIN blog_categories bc ON b.blog_id = bc.blog_id " +
                      "LEFT JOIN blog_hashtags bh ON b.blog_id = bh.blog_id " +
                      "WHERE bc.category_name IN (" + String.join(",", categories.stream().map(c -> "?").toArray(String[]::new)) + ") " +
                      "AND bh.hashtag IN (" + String.join(",", hashtags.stream().map(h -> "?").toArray(String[]::new)) + ") " +
                      "GROUP BY b.blog_id";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            int paramIndex = 1;
            for (String category : categories) {
                stmt.setString(paramIndex++, category);
            }
            for (String hashtag : hashtags) {
                stmt.setString(paramIndex++, hashtag);
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                blogs.add(extractBlogFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }
    
    private Blog extractBlogFromResultSet(ResultSet rs) throws SQLException {
        Blog blog = new Blog();
        blog.setBlogId(rs.getInt("blog_id"));
        blog.setUid(rs.getString("uid"));
        blog.setContent(rs.getString("content"));
        
        String categories = rs.getString("categories");
        if (categories != null) {
            for (String category : categories.split(",")) {
                blog.addCategory(category.trim());
            }
        }
        
        String hashtags = rs.getString("hashtags");
        if (hashtags != null) {
            for (String hashtag : hashtags.split(",")) {
                blog.addHashtag(hashtag.trim());
            }
        }
        
        return blog;
    }
} 