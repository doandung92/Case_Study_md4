package com.example.demo.service.blog;

import com.example.demo.model.user.Users;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface IBlogService {
    Iterable<Users> findAll();
    Optional<Users> findById(Long id);
    void save (Users users);
    void remove (Long id);
    Users findByUsersName(String name);
}
