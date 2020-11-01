package com.example.demo.controller.comment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @GetMapping("/commentPage/{id}")
    public String commentPage(@PathVariable Long id, Model model) {
//        Post post = postService.findById(id).get();
//        Users mainUsers = usersService.findById(usersService.findByUsersName(getPrincipal()).getId()).get();
//        Iterable<Comment> comments = commentService.findByPost(post); // findbyid
//        model.addAttribute("mainUser",mainUsers);
//        model.addAttribute("post", post);
//        model.addAttribute("comment",new Comment());
//        model.addAttribute("comments",comments);
        return "commentPage";
    }
    @PostMapping("/create")
    public String createComment(){
        System.out.println("-------");
        return "homepage";
    }
}
