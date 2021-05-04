package com.xyc.blog.service.impl;

import com.xyc.blog.Utils.MarkDownUtils;
import com.xyc.blog.dao.BlogDao;
import com.xyc.blog.pojo.Blog;
import com.xyc.blog.service.BlogService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogDao blogDao;

    @Transactional
    @Override
    public List<Blog> getAllBlogsByTypeId(Long TypeId) {
        return blogDao.getAllBlogsByTypeId(TypeId);
    }

    @Transactional
    @Override
    public Blog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Transactional
    @Override
    public List<Blog> getAllBlog() {
        return blogDao.getAllBlog();
    }

    @Transactional
    @Override
    public List<Blog> getBlogsByCondition(Blog blog) {
        if (blog.getTitle().equals("") && blog.getTypeId() == null) return blogDao.getAllBlog();
        return blogDao.getBlogsByCondition(blog);
    }

    @Transactional
    @Override
    public List<Blog> getIndexBlog() {
        return blogDao.getIndexBlog();
    }

    @Override
    public List<Blog> getBlogByQuery(Blog blog) {
        return blogDao.getBlogByQuery(blog);
    }

    @Transactional
    @Override
    public Blog getDetailBlogById(Long id) {
        Blog blog = blogDao.getDetailBlogById(id);
        String content = blog.getContent();
        blog.setContent(MarkDownUtils.markdownToHtmlExtensions(content));  //将Markdown格式转换成html
        return blogDao.getDetailBlogById(id);
    }

    @Transactional
    @Override
    public int viewsIncrease(int views, Long id) {
        return blogDao.viewsIncrease(views, id);
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        return blogDao.saveBlog(blog);
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        return blogDao.updateBlog(blog);
    }

    @Transactional
    @Override
    public int deleteBlog(Long id) {
        return blogDao.deleteBlog(id);
    }
}
