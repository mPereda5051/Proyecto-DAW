package com.jinbu.backend_jinbu.entities.Metadata;

import com.jinbu.backend_jinbu.entities.Photo;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

public class ModerationData {
    private boolean isPublic;

    private int timesReported;

    private String status;

    @OneToOne
    @MapsId
    @JoinColumn(name = "photo_id")
    private Photo photo;
}
