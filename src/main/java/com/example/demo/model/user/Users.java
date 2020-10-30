package com.example.demo.model.user;

import lombok.Data;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Timestamp;


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
    private Timestamp createDate;
    private Timestamp modifiedDate;

    @Transient
    private MultipartFile avatarFile;
}
