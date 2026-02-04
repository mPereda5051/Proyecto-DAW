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
@Table(name = "exif_data")
public class ExifData {
    
    @Id
    @Column(name = "photo_id")
    private Long id;

    @Column(name = "camera_maker")
    private String cameraMaker;
    
    @NonNull
    @Column(name = "camera_model", nullable = false)
    private String cameraModel;

    @Column(name = "iso")
    private int iso;

    @Column(name = "aperture")
    private double aperture;

    @Column(name = "shutter_speed")
    private double shutterSpeed;

    @Column(name = "focal_length")
    private String focalLength;

    @Column(name = "flash")
    private boolean flash;

    @Column(name = "lens_model")
    private String lensModel;

    @OneToOne
    @MapsId
    @JoinColumn(name = "photo_id")
    private Photo photo;
}
