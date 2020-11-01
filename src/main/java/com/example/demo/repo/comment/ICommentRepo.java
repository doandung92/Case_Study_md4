package com.example.demo.repo.comment;

import com.example.demo.model.article.Comment;
import com.example.demo.model.article.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICommentRepo extends PagingAndSortingRepository<Comment,Long> {
    Page<Comment> findByPost(Post post, Pageable pageable);
}
