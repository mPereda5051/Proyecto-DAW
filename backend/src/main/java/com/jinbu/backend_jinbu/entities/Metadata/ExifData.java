package com.jinbu.backend_jinbu.entities.Metadata;

import com.jinbu.backend_jinbu.entities.Photo;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

public class ExifData {
    private String cameraMaker;
    
    private String cameraModel;

    private String lens;

    private int iso;

    private double aperture;

    private double shutterSpeed;

    private String focalLength;

    private boolean flash;

    private String lensModel;

    @OneToOne
    @MapsId
    @JoinColumn(name = "photo_id")
    private Photo photo;
}
