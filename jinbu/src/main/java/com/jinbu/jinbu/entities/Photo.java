package com.jinbu.jinbu.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "photos")
public class Photo {

    public Photo(@NonNull String name, Date date, Integer iso, Double aperture, Double exposureTime, String width, String height, String extension) {
        this.name = name;
        this.date = date;
        this.iso = iso;
        this.aperture = aperture;
        this.exposure = exposureTime;
        this.width = width;
        this.height = height;
        this.extension = extension;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_taken")
    private Date date;

    @Column(name = "ISO")
    private Integer iso;

    @Column(name = "aperture")
    private Double aperture;

    @Column(name = "exposure")
    private Double exposure;

    @Column(name = "width")
    private String width;

    @Column(name = "height")
    private String height;

    @Column(name = "extension")
    private String extension;

    public String getFullUrl() {
        String projectDomain = System.getenv("S3_ENDPOINT");
        if (projectDomain != null) {
            // Convierte https://project.supabase.co/storage/v1/s3 en https://project.supabase.co
            projectDomain = projectDomain.split("/storage")[0];
            String bucketName = System.getenv("S3_BUCKET_NAME");
            return projectDomain + "/storage/v1/object/public/" + bucketName + "/" + this.getId() + this.getExtension();
        }
        return "https://jinbu-s3-bucket.s3.us-east-1.amazonaws.com/" + this.getId() + this.getExtension();
    }

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", referencedColumnName = "post_id")
    private Post post;
}
