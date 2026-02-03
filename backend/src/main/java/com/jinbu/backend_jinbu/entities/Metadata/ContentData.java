package com.jinbu.backend_jinbu.entities.Metadata;

import java.util.List;

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
@Table(name = "content_data")
public class ContentData {
    
    @Id
    @Column(name = "photo_id")
    private Long id;

    @NonNull
    @Column(name = "description", nullable = false)
    private String description;

    // Manejar los tags
    private List<String> tags;

    @NonNull
    @Column(name = "category", nullable = false)
    private String category;

    @NonNull
    @Column(name = "location", nullable = false)
    private String locationName;

    @NonNull
    @Column(name = "width", nullable = false)
    private int width;

    @NonNull
    @Column(name = "height", nullable = false)
    private int height;

    @OneToOne
    @MapsId
    @JoinColumn(name = "photo_id")
    private Photo photo;
}
