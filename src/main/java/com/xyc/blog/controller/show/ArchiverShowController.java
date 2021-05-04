package com.xyc.blog.controller.show;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyc.blog.pojo.Blog;
import com.xyc.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ArchiverShowController {

    @Autowired
    BlogService blogService;

    @GetMapping("/archiver")
    public String archiver
            (@RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum, Model model) {
        PageHelper.startPage(pagenum, 1000);
        List<Blog> blogs = blogService.getIndexBlog();
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo", pageInfo);

        return "archiver";
    }


}
