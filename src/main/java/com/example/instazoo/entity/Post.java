package com.example.instazoo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String caption;

    String location;

    Integer likes;

    @ElementCollection(targetClass = String.class)
    Set<String> likedUsers = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    User user;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "post", orphanRemoval = true)
    List<Comment> comments = new ArrayList<>();

    LocalDateTime createdDate;

    @PrePersist // bu metod @CreationTimestamp annotasiyasının gördüyü işi görür
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }


}

