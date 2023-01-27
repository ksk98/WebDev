package com.project.ImageGallery.service;

import com.project.ImageGallery.model.Gallery;
import com.project.ImageGallery.model.Image;
import com.project.ImageGallery.model.User;
import com.project.ImageGallery.repository.GalleryRepository;
import com.project.ImageGallery.repository.ImageRepository;
import com.project.ImageGallery.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final GalleryRepository galleryRepository;

    public Image createImage(User owner, byte[] content, Gallery gallery, String name) {
        if (owner == null || !userRepository.existsById(owner.getId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Owner doesn't exist.");

        if (content == null || content.length == 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image cannot be empty.");

        if (gallery == null || !galleryRepository.existsById(gallery.getId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gallery doesn't exist.");

        Image image = Image.builder()
                .owner(owner)
                .content(content)
                .gallery(gallery)
                .name(name)
                .build();

        return imageRepository.save(image);
    }

    public Image getImage(long id) {
        return getImageOrThrowNotFound(id);
    }

    public Image deleteImage(long id) {
        Image image = getImageOrThrowNotFound(id);

        imageRepository.delete(image);

        return image;
    }

    private Image getImageOrThrowNotFound(long id) {
        Optional<Image> targetQuery = imageRepository.findById(id);
        if (targetQuery.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return targetQuery.get();
    }
}
