package com.jinbu.backend_jinbu.entities.Metadata;

import com.jinbu.backend_jinbu.entities.Photo;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

public class SocialData {
    private int likes;

    private int views;

    private int comments;

    @OneToOne
    @MapsId
    @JoinColumn(name = "photo_id")
    private Photo photo;
}
