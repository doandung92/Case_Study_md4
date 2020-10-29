package com.example.demo.repo.blog;

import com.example.demo.model.user.Users;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IUsersRepo extends PagingAndSortingRepository<Users,Long> {
    Users findByUserName (String name);
}
