package com.project.ImageGallery.repository;

import com.project.ImageGallery.model.Gallery;
import com.project.ImageGallery.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    List<Gallery> getAllByOwner(User owner);
}
