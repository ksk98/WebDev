package com.project.ImageGallery.repository;

import com.project.ImageGallery.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
}
