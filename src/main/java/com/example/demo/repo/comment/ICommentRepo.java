package com.example.demo.repo.comment;

import com.example.demo.model.article.Comment;
import com.example.demo.model.article.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICommentRepo extends PagingAndSortingRepository<Comment,Long> {
    Iterable<Comment> findByPost(Post post);
}
