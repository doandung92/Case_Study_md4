package com.example.demo.service.security;

import com.example.demo.model.Users;
import com.example.demo.repo.security.ISecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityService implements ISecurityService, UserDetailsService {
    @Autowired
    private ISecurityRepository securityRepository;
    @Override
    public Users findByUserName(String name) {
        return securityRepository.findByUserName(name);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.findByUserName(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(user.getRole());
        UserDetails userDetails = new User(user.getUserName(), user.getUserPassword(), authorities);
        return userDetails;
    }
}
