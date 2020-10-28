package com.example.demo.controller.security;

import com.example.demo.model.user.Users;
import com.example.demo.service.blog.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    @Autowired
    private IBlogService blogService;
    private String getPrincipal(){

        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
    @GetMapping( value = {"/profile"})
    public String profilePage(Model model){
        Users users = blogService.findByUsersName(getPrincipal());
        model.addAttribute("users",users);
        System.out.println(users.getId());
        System.out.println(users.getAvatar()+"-------");
        return "index";
    }
    @GetMapping("/homepage")
    public String homePage(){
        return "homePage";
    }
    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }
    @GetMapping("/notAuthor")
    public String notAuthorPage(){
        return "notAuthor";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
