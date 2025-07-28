package dao;

import model.Blog;
import java.util.List;

public interface BlogDAO {
    boolean addBlog(Blog blog);
    boolean updateBlog(Blog blog);
    boolean deleteBlog(int blogId);
    Blog getBlog(int blogId);
    Blog getBlogByUid(String uid);
    List<Blog> getAllBlogs();
    List<Blog> getBlogsByCategories(List<String> categories);
    List<Blog> getBlogsByHashtags(List<String> hashtags);
    List<Blog> getBlogsByCategoriesAndHashtags(List<String> categories, List<String> hashtags);
} 