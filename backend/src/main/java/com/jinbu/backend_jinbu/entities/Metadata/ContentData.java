package com.jinbu.backend_jinbu.entities.Metadata;

import java.util.List;

import com.jinbu.backend_jinbu.entities.Photo;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

public class ContentData {
    private String description;

    private List<String> tags;

    private String category;

    private String locationName;

    @OneToOne
    @MapsId
    @JoinColumn(name = "photo_id")
    private Photo photo;
}
