package com.example.demo.service.commentService;

import com.example.demo.model.article.Comment;
import com.example.demo.model.article.Post;

import java.util.Optional;

public interface ICommentService {
    Iterable<Comment> findAll();
    void save (Comment comment);
    Optional<Comment> findById(Long id);
    Comment remove(Long id);
    Iterable findByPost(Post post);
}
