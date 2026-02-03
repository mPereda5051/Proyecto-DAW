package com.jinbu.backend_jinbu.Entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "photo_metadata")
public class PhotoMetadata {
    private String body;

    private String maker;

    private int iso;

    private String coords;

    private Date creationDate;
    
    private String focalLength;

    private List<String> tags;

    private String description;

    private String category;

    private double aperture;

    private double shutterSpeed;

    private int width;

    private int height;

    private String lensModel;

    private boolean flashUsed;
}
