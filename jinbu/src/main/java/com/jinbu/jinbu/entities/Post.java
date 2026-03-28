package com.jinbu.jinbu.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Instant createdOn;

    @UpdateTimestamp
    private Instant updatedOn;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "likes")
    private Long likes;

    // URI para imagenes?

    @ManyToOne(optional = false)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User user;
}
