package com.example.demo.service.post;

import com.example.demo.model.article.Post;
import org.springframework.beans.factory.annotation.Autowired;

public class PostService implements IPostService {
    @Override
    public Iterable<Post> findAll() {
        return null;
    }

    @Override
    public Post save(Post post) {
        return null;
    }

    @Override
    public Post remove(Long id) {
        return null;
    }
}
