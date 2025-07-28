package com.spitbay.dao;

import com.spitbay.model.Blog;
import java.util.List;
import java.util.Set;

public interface BlogDAO {
    boolean addBlog(Blog blog);
    boolean updateBlog(Blog blog);
    boolean deleteBlog(int blogId);
    Blog getBlog(int blogId);
    List<Blog> getBlogByUid(String uid);
    List<Blog> getAllBlogs();
    List<Blog> getBlogsByCategories(List<String> categories);
    List<Blog> getBlogsByHashtags(Set<String> hashtags);
    Set<String> getAllHashtags();
} 