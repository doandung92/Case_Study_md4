package com.example.demo.repo.role;

import com.example.demo.model.user.Roles;
import org.springframework.data.repository.CrudRepository;

public interface IRoleRepo extends CrudRepository<Roles,Long> {
    Roles findByNameRole (String name);
}
