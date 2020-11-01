package com.example.demo.controller.comment;

import com.example.demo.model.article.Comment;
import com.example.demo.model.article.Post;
import com.example.demo.model.user.Users;
import com.example.demo.service.commentService.ICommentService;
import com.example.demo.service.post.IPostService;
import com.example.demo.service.user.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comment")
public class CommentController {
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

    @GetMapping("/commentPage/{id}")
    public String commentPage(@PathVariable Long id, Model model) {
        Post post = postService.findById(id).get();
//        Users mainUsers = usersService.findById(usersService.findByUsersName(getPrincipal()).getId()).get();
        Users mainUsers = usersService.findByUsersName(getPrincipal());
        Iterable<Comment> comments = commentService.findByPost(post); // findbyid
        model.addAttribute("mainUser",mainUsers);
        model.addAttribute("post", post);
        model.addAttribute("comment",new Comment());
//        model.addAttribute("comments",comments);
        return "commentPage";
    }
    @PostMapping("/create")
    public String createComment(@ModelAttribute("comment") Comment comment){
//            comment.setPost(post);
            commentService.save(comment);
        return "redirect:/homepage";
    }
}
