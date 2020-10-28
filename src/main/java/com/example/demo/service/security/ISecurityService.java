package com.example.demo.service.security;

import com.example.demo.model.Users;

public interface ISecurityService {
    Users findByUserName(String name);
}
