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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypesShowController {

    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;


    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id, @RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum, Model model) {
        PageHelper.startPage(pagenum, 1000);
        List<Type> allTypes = typeService.getAllTypes();
        if (id == -1) {
            id = allTypes.get(0).getId();
        }
        List<Blog> allBlogs = blogService.getAllBlogsByTypeId(id);
        PageInfo<Blog> pageInfo = new PageInfo<>(allBlogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("allTypes", allTypes);
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
