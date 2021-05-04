package com.xyc.blog.service;

import com.xyc.blog.pojo.Blog;

import java.util.List;

public interface BlogService {

    List<Blog> getAllBlogsByTypeId(Long TypeId);

    Blog getBlogById(Long id);

    List<Blog> getAllBlog();

    List<Blog> getBlogsByCondition(Blog blog);

    List<Blog> getIndexBlog();

    List<Blog> getBlogByQuery(Blog blog);

    Blog getDetailBlogById(Long id);

    int viewsIncrease(int views, Long id);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    int deleteBlog(Long id);

}
