package com.xyc.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyc.blog.pojo.Blog;
import com.xyc.blog.pojo.Type;
import com.xyc.blog.pojo.User;
import com.xyc.blog.service.BlogService;
import com.xyc.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    private void setTypes(Model model) {
        model.addAttribute("types", typeService.getAllTypes());
    }

    @GetMapping("/blogs")  //后台显示博客列表
    public String blogs(@RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum, Model model) {
        PageHelper.startPage(pagenum, 5);
        List<Blog> allBlogs = blogService.getAllBlog();
        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlogs);
        model.addAttribute("pageInfo", pageInfo);
        setTypes(model);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")  //按条件查询博客
    public String searchBlogs(Blog blog, @RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum, Model model) {
        PageHelper.startPage(pagenum, 5);
        List<Blog> allBlog = blogService.getBlogsByCondition(blog);
        //得到分页结果对象
        PageInfo pageInfo = new PageInfo(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("message", "查询成功");
        setTypes(model);
        return "admin/blogs";
    }

    @GetMapping("/blogs/input") //去新增博客页面
    public String toAddBlog(Model model){
        Blog blog = new Blog();
        blog.setType(new Type());
        blog.setFlag("原创");
        model.addAttribute("blog", blog);  //返回一个blog对象给前端th:object
        setTypes(model);
        return "admin/blogs-input";
    }

    @GetMapping("/blogs/{id}/input") //去编辑博客页面
    public String toEditBlog(@PathVariable Long id, Model model){
        Blog blog = blogService.getBlogById(id);
        model.addAttribute("blog", blog);     //返回一个blog对象给前端th:object
        setTypes(model);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String addType(Blog blog, HttpSession session, RedirectAttributes attributes) {
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        blog.setUser((User)session.getAttribute("user"));
        blog.setUserId(blog.getUser().getId());
        blog.setUpdateTime(new Date());
        if(blog.getId()==null){
            blog.setViews(0);
            blog.setCreateTime(new Date());
            blogService.saveBlog(blog);
        }else {
            blogService.updateBlog(blog);
        }
        attributes.addFlashAttribute("msg", "操作成功");
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        blogService.deleteBlog(id);
        redirectAttributes.addFlashAttribute("msg", "操作成功");
        return "redirect:/admin/blogs";
    }




}
