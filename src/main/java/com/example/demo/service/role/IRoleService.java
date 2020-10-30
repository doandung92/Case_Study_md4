package com.example.demo.service.role;

import com.example.demo.model.user.Roles;
import com.example.demo.repo.role.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;

public interface IRoleService {
    Roles findByName(String name);

}
