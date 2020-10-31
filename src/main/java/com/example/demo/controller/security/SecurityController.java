package com.example.demo.controller.security;

import com.example.demo.model.article.Post;
import com.example.demo.model.user.Users;
import com.example.demo.service.post.IPostService;
import com.example.demo.service.role.IRoleService;
import com.example.demo.service.user.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUsersService usersService;
    @Autowired
            private IPostService postService;

    //Khai bao bien cloudinary
    String mCloudName = "dtcimirzt";
    String mApiKey = "997964747139867";
    String mApiSecret = "aHfm4-P3L-byZX4H8SQqYUfmZvc";
    @ModelAttribute
    public void showListUsers(Model model){
        model.addAttribute("listUser",usersService.findAll());
    }
    @ModelAttribute
    public void showListPosts(Model model){
        model.addAttribute("posts",postService.findAll());
    }
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
    @GetMapping( value = {"/profile","/"})
    public String profilePage(Model model){
        Users users = usersService.findByUsersName(getPrincipal());
        model.addAttribute("users",users);
        return "profile";
    }
    @GetMapping("/homepage")
    public String homePage(Model model){
        model.addAttribute("post", new Post());
        model.addAttribute("mainUser",usersService.findByUsersName(getPrincipal()));
        return "homepage";
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
