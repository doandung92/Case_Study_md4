package com.example.demo.service.blog;

import com.example.demo.model.user.Users;
import com.example.demo.repo.blog.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService{

    @Autowired
    private IBlogRepo blogRepo;
    @Override
    public Iterable<Users> findAll() {
        return blogRepo.findAll();
    }

    @Override
    public Optional<Users> findById(Long id) {
        return blogRepo.findById(id);
    }

    @Override
    public void save(Users users) {
        blogRepo.save(users);
    }

    @Override
    public void remove(Long id) {
        blogRepo.deleteById(id);
    }

    @Override
    public Users findByUsersName(String name) {
        return blogRepo.findByUserName(name);
    }
}
