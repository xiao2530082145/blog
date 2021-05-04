package com.xyc.blog.controller.show;

import com.xyc.blog.pojo.Type;
import com.xyc.blog.pojo.User;
import com.xyc.blog.service.TypeService;
import com.xyc.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AboutController {

    @Autowired
    TypeService typeService;
    @Autowired
    UserService userService;

    @GetMapping("/about")
    public String about(Model model) {
        List<Type> allTypes = typeService.getAllTypes();
        User userInfo = userService.getUserInfo();
        allTypes = allTypes.subList(0, 6);
        model.addAttribute("allTypes", allTypes);
        model.addAttribute("userInfo", userInfo);
        return "about";
    }
}
