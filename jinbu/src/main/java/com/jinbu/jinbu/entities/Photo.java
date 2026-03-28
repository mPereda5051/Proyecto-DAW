package com.jinbu.jinbu.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "photos")
public class Photo {

    public Photo(@NonNull String name, Date date, String iso, String aperture, String exposureTime, String width, String height, String extension) {
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
    private String iso;

    @Column(name = "aperture")
    private String aperture;

    @Column(name = "exposure")
    private String exposure;

    @Column(name = "width")
    private String width;

    @Column(name = "height")
    private String height;

    @Column(name = "extension")
    private String extension;


    // Cambiar valor hardcodeado
      public String getFullUrl() {
          return "https://jinbu-s3-bucket.s3.us-east-1.amazonaws.com/" + this.getId() + this.getExtension();
      }

    // Con @PrimaryKeyKoinColumn hacemos que compartan ID
//    @OneToOne(cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private Post post;
}
