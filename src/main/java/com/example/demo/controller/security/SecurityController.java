package com.example.demo.controller.security;

import com.example.demo.model.user.Roles;
import com.example.demo.model.user.Users;
import com.example.demo.service.role.IRoleService;
import com.example.demo.service.user.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.management.relation.Role;

@Controller
public class SecurityController {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUsersService blogService;
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
        return "profile";
    }
    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("users" ,new Users());
        return "register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute Users users){
        String name = "ROLE_USER";
        Roles roles = roleService.findByName(name);
        users.setRole(roles);
        blogService.save(users);
        return "login";
    }
    @GetMapping("/homepage")
    public String homePage(){
        return "templateHomePage";
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
