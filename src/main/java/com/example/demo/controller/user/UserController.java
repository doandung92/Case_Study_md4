package com.example.demo.controller.user;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.model.user.Roles;
import com.example.demo.model.user.Users;
import com.example.demo.service.role.IRoleService;
import com.example.demo.service.user.IUsersService;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/users")
@Controller
public class UserController {

    @Autowired
            private IUsersService usersService;
    @Autowired
            private IRoleService roleService;
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

    String mCloudName = "dtcimirzt";
    String mApiKey = "997964747139867";
    String mApiSecret = "aHfm4-P3L-byZX4H8SQqYUfmZvc";
    Cloudinary c = new Cloudinary("cloudinary://"+mApiKey+":"+mApiSecret+"@"+mCloudName);
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
        users.setCreateDate(new Timestamp(System.currentTimeMillis()));
        usersService.save(users);
        return "redirect:/login";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Users users){
        MultipartFile avatarFile = users.getAvatarFile();
        try{
            File avFile= Files.createTempFile("temp", avatarFile.getOriginalFilename()).toFile();
            avatarFile.transferTo(avFile);
            Map responseAV=c.uploader().upload(avFile, ObjectUtils.emptyMap());
            JSONObject jsonAV=new JSONObject(responseAV);
            String urlAV=jsonAV.getString("url");
            System.out.println(urlAV+"------------------");
            System.out.println("lam thon");
            System.out.println(urlAV);
            users.setAvatar(urlAV);
            users.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        }catch (Exception e){
            e.printStackTrace();
        }
        String name= "ROLE_USER";
        users.setRole(roleService.findByName(name));
        usersService.save(users);
        return "redirect:/profile";
    }
    @GetMapping("/friend/{id}")
    public ModelAndView friendProfile(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/friendProfile");
        Users users = usersService.findById(id).get();
        modelAndView.addObject("users",users);
        return modelAndView;
    }

}
