package com.jinbu.backend_jinbu.entities.Metadata;

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
@Table(name = "moderation_data")
public class ModerationData {

    @Id
    @Column(name = "photo_id")
    private Long id;

    @NonNull
    @Column(name = "is_public", nullable = false)
    private boolean isPublic;

    @NonNull
    @Column(name = "times_reported", nullable = false)
    private int timesReported;

    @NonNull
    @Column(name = "status", nullable = false)
    private String status;

    @OneToOne
    @MapsId
    @JoinColumn(name = "photo_id")
    private Photo photo;
}
