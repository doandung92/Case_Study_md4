package com.example.demo.service.user;

import com.example.demo.model.user.Users;

import java.util.Optional;


public interface IUsersService {
    Iterable<Users> findAll();
    Optional<Users> findById(Long id);
    void save (Users users);
    void remove (Long id);
    Users findByUsersName(String name);
}
