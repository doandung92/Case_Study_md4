package com.example.demo.controller.comment;

import com.example.demo.model.article.Comment;
import com.example.demo.model.article.Post;
import com.example.demo.model.user.Users;
import com.example.demo.service.commentService.ICommentService;
import com.example.demo.service.post.IPostService;
import com.example.demo.service.user.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String commentPage(@PathVariable Long id, Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<Comment> comments;
        Post post = postService.findById(id).get();
        Users mainUsers = usersService.findByUsersName(getPrincipal());
        comments = commentService.findByPost(post, (java.awt.print.Pageable) pageable);
        model.addAttribute("mainUser", mainUsers);
        model.addAttribute("post", post);
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments",comments);
        return "commentPage";
    }

    @PostMapping("/create/{id}")
    public String createComment(@ModelAttribute("comment") Comment comment, @PathVariable Long id,RedirectAttributes redirectAttributes) {
        Post post = postService.findById(id).get();
        Users mainUsers = usersService.findByUsersName(getPrincipal());
        System.out.println(id + "--------------------");
        comment.setPost(post);
        comment.setUsers(mainUsers);
        commentService.save(comment);
        return "redirect:/comment/commentPage/"+id;
    }
}
