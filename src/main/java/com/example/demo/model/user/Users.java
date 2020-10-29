package com.example.demo.model.user;

import lombok.Data;

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
    private String phoneNumber;
    private String email;
    private String address;
    private String gender;
    private String job;
    private String avatar;
    private String cover;
}
