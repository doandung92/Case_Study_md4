package com.example.demo.model.user;

import com.example.demo.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Entity
@Data

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,unique = true)
    private String userName;
    @Column(nullable = false)
    private String userPassword;
    @ManyToOne
    private Roles role;

    private String fullName;
    private boolean status;
    private String phoneNumber;
    private String email;
    private String address;
    private String gender;
    private String job;
    private String avatar;
    private String cover;
}
