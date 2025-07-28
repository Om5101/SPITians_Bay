package service;

import dao.BlogDAO;
import dao.BlogDAOImpl;
import model.Blog;

import java.sql.SQLException;
import java.util.List;

public class BlogService {
    private BlogDAO blogDAO;
    
    public BlogService() throws SQLException {
        this.blogDAO = new BlogDAOImpl();
    }
    
    public boolean addBlog(Blog blog) {
        return blogDAO.addBlog(blog);
    }
    
    public boolean updateBlog(Blog blog) {
        return blogDAO.updateBlog(blog);
    }
    
    public boolean deleteBlog(int blogId) {
        return blogDAO.deleteBlog(blogId);
    }
    
    public Blog getBlog(int blogId) {
        return blogDAO.getBlog(blogId);
    }
    
    public Blog getBlogByUid(String uid) {
        return blogDAO.getBlogByUid(uid);
    }
    
    public List<Blog> getAllBlogs() {
        return blogDAO.getAllBlogs();
    }
    
    public List<Blog> getBlogsByCategories(List<String> categories) {
        return blogDAO.getBlogsByCategories(categories);
    }
    
    public List<Blog> getBlogsByHashtags(List<String> hashtags) {
        return blogDAO.getBlogsByHashtags(hashtags);
    }
    
    public List<Blog> getBlogsByCategoriesAndHashtags(List<String> categories, List<String> hashtags) {
        return blogDAO.getBlogsByCategoriesAndHashtags(categories, hashtags);
    }
} 