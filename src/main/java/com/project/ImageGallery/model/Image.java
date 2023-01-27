package com.project.ImageGallery.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @Nonnull
    private Gallery gallery;

    @ManyToOne
    @Nonnull
    private User owner;

    @Builder.Default
    private String name = "New image";

    @Nonnull
    private byte[] content;
}
