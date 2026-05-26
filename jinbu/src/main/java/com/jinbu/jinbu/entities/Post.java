package com.jinbu.jinbu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @CreationTimestamp
    private Instant createdOn;

    @UpdateTimestamp
    private Instant updatedOn;

    @Column(name = "title")
    @NotBlank
    private String title;

    @Column(name = "content")
    @NotBlank
    private String content;

    @Column(name = "likes")
    private Long likes = 0L;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
    private Photo photo;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;
}