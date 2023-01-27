package com.project.ImageGallery.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Gallery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @Nonnull
    private User owner;

    @OneToMany(cascade=ALL)
    @Builder.Default
    private List<Image> images = new ArrayList<>();

    @Builder.Default
    private String name = "New gallery";
}
