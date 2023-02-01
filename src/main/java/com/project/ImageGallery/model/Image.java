package com.project.ImageGallery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "IMAGES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GALLERY_ID")
    @JsonBackReference
    private Gallery gallery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID")
    @JsonBackReference
    private User owner;

    @Column(name = "NAME", nullable = false)
    @Builder.Default
    private String name = "New image";

    @Lob
    @Column(name = "CONTENT", nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
}
