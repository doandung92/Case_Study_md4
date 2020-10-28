package com.example.demo.repo.security;

import com.example.demo.model.user.Users;
import org.springframework.data.repository.CrudRepository;

public interface ISecurityRepository extends CrudRepository<Users,Long> {
     Users findByUserName(String name);
}
