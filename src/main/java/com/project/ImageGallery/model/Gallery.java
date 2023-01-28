package com.project.ImageGallery.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "GALLERIES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Gallery implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID")
    @JsonBackReference
    private User owner;

    @Column(name = "IMAGES", nullable = false)
    @OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "gallery")
    @Builder.Default
    @JsonManagedReference
    private List<Image> images = new LinkedList<>();

    @Column(name = "NAME", nullable = false)
    @Builder.Default
    private String name = "New gallery";
}
