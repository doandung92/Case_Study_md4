package com.example.demo.model.article;

import com.example.demo.model.user.Users;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean status;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Post post;
}
