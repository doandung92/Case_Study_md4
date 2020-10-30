package com.example.demo.repo.post;

import com.example.demo.model.article.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPostRepo extends PagingAndSortingRepository<Post,Long> {
}
