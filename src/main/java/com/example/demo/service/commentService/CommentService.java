package com.example.demo.service.commentService;

import com.example.demo.model.article.Comment;
import com.example.demo.model.article.Post;
import com.example.demo.repo.comment.ICommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentRepo commentRepo;
    @Override
    public Iterable<Comment> findAll() {
        return commentRepo.findAll();
    }

    @Override
    public void save(Comment comment) {
        commentRepo.save(comment);
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepo.findById(id);
    }

    @Override
    public Comment remove(Long id) {
        Comment comment = this.findById(id).get();
        commentRepo.deleteById(id);
        return comment;
    }

    @Override
    public Page<Comment> findByPost(Post post, java.awt.print.Pageable pageable) {
        return commentRepo.findByPost(post, (Pageable) pageable);
    }

}
