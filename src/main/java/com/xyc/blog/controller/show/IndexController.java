package com.xyc.blog.controller.show;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyc.blog.pojo.Blog;
import com.xyc.blog.pojo.Type;
import com.xyc.blog.service.BlogService;
import com.xyc.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;

    @GetMapping("/")
    public String index(@RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum, Model model) {

        PageHelper.startPage(pagenum, 8);
        List<Blog> allBlogs = blogService.getIndexBlog();
        List<Type> allTypes = typeService.getDetailTypes();
        PageInfo pageInfo = new PageInfo(allBlogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", allTypes);
        return "index";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        Blog blog = blogService.getDetailBlogById(id);
        model.addAttribute("blog", blog);
        int views = blog.getViews();
        blogService.viewsIncrease(++views, id);
        return "blog";
    }

    @PostMapping("/search")
    public String search(@RequestParam String query, @RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum, Model model) {
        PageHelper.startPage(pagenum, 1000);
        Blog blog = new Blog();
        blog.setTitle(query);
        List<Blog> blogs = blogService.getBlogByQuery(blog);
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }


}
