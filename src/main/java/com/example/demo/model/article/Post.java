package com.example.demo.model.article;

import com.example.demo.model.user.Users;
import lombok.Data;
import org.hibernate.type.NClobType;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String img;
    @Column (name = "content",nullable = false,columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private Users users;

    private Timestamp createTime;
}
