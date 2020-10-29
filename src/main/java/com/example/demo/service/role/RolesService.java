package com.example.demo.service.role;

import com.example.demo.model.user.Roles;
import com.example.demo.repo.role.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService implements IRoleService {
    @Autowired
    private IRoleRepo roleRepo;
    @Override
    public Roles findByName(String name) {
        return roleRepo.findByNameRole(name);
    }
}
