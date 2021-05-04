package com.xyc.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xyc.blog.pojo.Blog;
import com.xyc.blog.pojo.Type;
import com.xyc.blog.service.BlogService;
import com.xyc.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypesController {
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;


    @GetMapping("/types")
    public String types(@RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum, Model model) {
        PageHelper.startPage(pagenum, 5);
        List<Type> allTypes = typeService.getAllTypes();
        //得到分页结果对象
        PageInfo<Type> pageInfo = new PageInfo<>(allTypes);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String toAddType(Model model) {
        model.addAttribute("type", new Type());
        return "admin/types-input";
    }

    @GetMapping("/types/{id}/input")
    public String toEditType(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getTypeById(id));
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String addType(Type type, RedirectAttributes attributes) {
        Type t = typeService.getTypeByName(type.getName());
        if (t != null) {
            attributes.addFlashAttribute("msg", "存在重复分类，操作失败");
            return "redirect:/admin/types/input";
        } else {
            attributes.addFlashAttribute("msg", "操作成功");
        }
        typeService.saveType(type);
        return "redirect:/admin/types";
    }

    @PostMapping("/types/{id}")
    public String editType(@PathVariable Long id, Type type, RedirectAttributes attributes) {
        Type t = typeService.getTypeByName(type.getName());
        if (t != null) {
            attributes.addFlashAttribute("msg", "存在重复分类，操作失败");
            return "redirect:/admin/types/input";
        } else {
            attributes.addFlashAttribute("msg", "操作成功");
        }
        typeService.updateType(type);
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {

        List<Blog> allBlogsByTypeId = blogService.getAllBlogsByTypeId(id);
        if (allBlogsByTypeId.size() == 0) {
            attributes.addFlashAttribute("msg", "操作成功");
            typeService.deleteType(id);
        } else {
            attributes.addFlashAttribute("msg1", "分类下存在博客，操作失败");
        }

        System.out.println(allBlogsByTypeId);
        return "redirect:/admin/types";
    }
}
