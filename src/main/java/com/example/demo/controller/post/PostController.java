package com.example.demo.controller.post;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.model.article.Comment;
import com.example.demo.model.article.Post;
import com.example.demo.model.user.Users;
import com.example.demo.service.commentService.ICommentService;
import com.example.demo.service.post.IPostService;
import com.example.demo.service.user.IUsersService;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private IPostService postService;

    @Autowired
    private IUsersService usersService;
    @Autowired
    private ICommentService commentService;

    private String getPrincipal() {

        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    String mCloudName = "dtcimirzt";
    String mApiKey = "997964747139867";
    String mApiSecret = "aHfm4-P3L-byZX4H8SQqYUfmZvc";
    Cloudinary cloudinary = new Cloudinary("cloudinary://" + mApiKey + ":" + mApiSecret + "@" + mCloudName);

    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post) {
        if (!post.getImgFile().isEmpty()) {
            MultipartFile postImgFile = post.getImgFile();

            try {
                File postImg = Files.createTempFile("temp", postImgFile.getOriginalFilename()).toFile();
                postImgFile.transferTo(postImg);
                Map responseAV = cloudinary.uploader().upload(postImg, ObjectUtils.emptyMap());
                JSONObject jsonAV = new JSONObject(responseAV);
                String urlAV = jsonAV.getString("url");
                post.setImg(urlAV);
                post.setUsers(usersService.findByUsersName(getPrincipal()));
                post.setCreateTime(new Timestamp(System.currentTimeMillis()));
                postService.save(post);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            post.setUsers(usersService.findByUsersName(getPrincipal()));
            post.setCreateTime(new Timestamp(System.currentTimeMillis()));
            postService.save(post);
        }
        return "redirect:/homepage";
    }

}
