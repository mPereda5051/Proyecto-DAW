package com.jinbu.backend_jinbu.entities.metadata;

import com.jinbu.backend_jinbu.entities.Photo;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "social_data")
public class SocialData {

    @Id
    @Column(name = "photo_id")
    private Long id;

    @NonNull
    @Column(name = "likes", nullable = false)
    private int likes;

    @NonNull
    @Column(name = "views", nullable = false)
    private int views;

    @NonNull
    @Column(name = "comments", nullable = false)
    private int comments;

    @OneToOne
    @MapsId
    @JoinColumn(name = "photo_id")
    private Photo photo;
}
