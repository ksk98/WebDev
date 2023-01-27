package com.project.ImageGallery.repository;

import com.project.ImageGallery.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
