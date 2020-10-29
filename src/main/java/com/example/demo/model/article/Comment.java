package com.example.demo.model.article;

import com.example.demo.model.user.Users;
import com.sun.tools.javac.jvm.Gen;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "content",nullable = false,columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Post post;

}
