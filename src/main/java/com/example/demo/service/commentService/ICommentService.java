package com.example.demo.service.commentService;

import com.example.demo.model.article.Comment;
import com.example.demo.model.article.Post;
import org.springframework.data.domain.Page;;

import java.awt.print.Pageable;
import java.util.Optional;

public interface ICommentService {
    Iterable<Comment> findAll();
    void save (Comment comment);
    Optional<Comment> findById(Long id);
    Comment remove(Long id);
    Page<Comment> findByPost(Post post, Pageable pageable);
}
