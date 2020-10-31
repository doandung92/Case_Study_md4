package com.example.demo.service.post;

import com.example.demo.model.article.Post;

public interface IPostService {
    Iterable<Post> findAll();
    Post save(Post post);
    void remove(Long id);
}
