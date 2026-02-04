package com.jinbu.backend_jinbu.entities;

import java.util.Date;

import com.jinbu.backend_jinbu.entities.metadata.ExifData;
import com.jinbu.backend_jinbu.entities.metadata.SocialData;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne(optional = false)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private User user;

    @NonNull
    @Column(name = "creation_date", nullable = false)
    private Date uploadDate;

    @OneToOne(mappedBy = "photo", cascade = CascadeType.ALL)
    private ExifData exifData;

    @OneToOne(mappedBy = "photo", cascade = CascadeType.ALL)
    private SocialData socialData;

    @OneToOne(mappedBy = "photo", cascade = CascadeType.ALL)
    private SocialData contenData;

    @OneToOne(mappedBy = "photo", cascade = CascadeType.ALL)
    private SocialData ModerationData;
}
