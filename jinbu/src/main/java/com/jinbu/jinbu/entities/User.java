package com.jinbu.jinbu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Size(min = 3, message = "Username must be at least 3 characters long")
    @Size(max = 30, message = "Name cannot be more than 30 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username cannot contain special characters")
    @Column(name = "username", nullable = false)
    private String username;

    @NonNull
    @Column(name = "password", nullable = false)
    private String password;

    @NonNull
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_followers",
            joinColumns = @JoinColumn(name = "follower_id"),
            inverseJoinColumns = @JoinColumn(name = "followed_id")
    )
    private Set<User> following = new HashSet<>();

    @ManyToMany(mappedBy = "following")
    private Set<User> followers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Post> posts;
}
