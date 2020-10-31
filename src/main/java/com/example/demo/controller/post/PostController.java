package com.example.demo.controller.post;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.demo.model.article.Post;
import com.example.demo.service.post.IPostService;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private IPostService postService;

    //    @ModelAttribute("post")
//    public Iterable<Post> posts(){
//        return postService.findAll();
//    }
    String mCloudName = "dtcimirzt";
    String mApiKey = "997964747139867";
    String mApiSecret = "aHfm4-P3L-byZX4H8SQqYUfmZvc";
    Cloudinary c = new Cloudinary("cloudinary://" + mApiKey + ":" + mApiSecret + "@" + mCloudName);
    @PostMapping("/create")
    public String createPost(@RequestParam String content, @RequestParam MultipartFile imgFile) {
//        try {
//            File avFile = Files.createTempFile("temp", imgFile.getOriginalFilename()).toFile();
//            imgFile.transferTo(avFile);
//            Map responseAV = c.uploader().upload(avFile, ObjectUtils.emptyMap());
//            JSONObject jsonAV = new JSONObject(responseAV);
//            String urlAV = jsonAV.getString("url");
            Post post = new Post();
            post.setContent(content);
//            post.setImg(urlAV);
            postService.save(post);

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return "redirect:/homepage";
    }
}
