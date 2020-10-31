package com.example.demo.service.post;

import com.example.demo.model.article.Post;
import com.example.demo.repo.post.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepo repo;
    @Override
    public Iterable<Post> findAll() {
        return repo.findAll();
    }

    @Override
    public Post save(Post post) {
        return repo.save(post);
    }

    @Override
    public void remove(Long id) {
         repo.deleteById(id);
    }
}
