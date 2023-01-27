package com.project.ImageGallery.service;

import com.project.ImageGallery.model.Gallery;
import com.project.ImageGallery.model.Image;
import com.project.ImageGallery.model.User;
import com.project.ImageGallery.repository.GalleryRepository;
import com.project.ImageGallery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@AllArgsConstructor
public class GalleryService {
    private final GalleryRepository galleryRepository;
    private final UserRepository userRepository;
    private final ImageService imageService;

    public Gallery createGallery(User owner, String name) {
        if (owner == null || !userRepository.existsByUsername(owner.getUsername()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner doesn't exist.");

        Gallery gallery = Gallery.builder()
                .owner(owner)
                .name(name)
                .build();

        return galleryRepository.save(gallery);
    }

    public Gallery getGallery(long id) {
        return getGalleryOrThrowNotFound(id);
    }

    public Gallery updateGallery(Gallery gallery) {
        Gallery old = getGalleryOrThrowNotFound(gallery.getId());

        old.setName(gallery.getName());
        return galleryRepository.save(old);
    }

    public Gallery deleteGallery(long id) {
        Optional<Gallery> gallery = galleryRepository.findById(id);

        if (gallery.isPresent()) {
            galleryRepository.deleteById(id);
            return gallery.get();
        }

        return null;
    }

    public Gallery addImagesTo(long id, List<Image> images) {
        Gallery gallery = getGalleryOrThrowNotFound(id);

        images.stream().peek(image -> {
            if (image.getOwner().getId() != id)
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not the owner of added images.");
        });

        Set<Image> allImages = new HashSet<>(gallery.getImages());
        allImages.addAll(images);
        gallery.setImages(allImages.stream().toList());

        return galleryRepository.save(gallery);
    }

    public Gallery removeImagesFrom(long id, List<Image> images) {
        Gallery gallery = getGalleryOrThrowNotFound(id);

        gallery.getImages().iterator().forEachRemaining(image -> imageService.deleteImage(image.getId()));
        gallery.getImages().removeAll(images);

        return galleryRepository.save(gallery);
    }

    private Gallery getGalleryOrThrowNotFound(long id) {
        Optional<Gallery> targetQuery = galleryRepository.findById(id);
        if (targetQuery.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return targetQuery.get();
    }
}
