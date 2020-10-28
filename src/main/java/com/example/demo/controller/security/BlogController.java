package com.example.demo.controller.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
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
    @GetMapping( value = {"/","profile"})
    public String profilePage(){
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
