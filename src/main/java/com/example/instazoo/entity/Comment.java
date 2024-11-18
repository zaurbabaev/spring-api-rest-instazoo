package com.example.instazoo.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Post post;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    Long userId;

    @Column(columnDefinition = "text", nullable = false)
    String message;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createdDate;

}
