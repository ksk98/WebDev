package com.project.ImageGallery.controller;

import com.project.ImageGallery.model.Gallery;
import com.project.ImageGallery.model.Image;
import com.project.ImageGallery.model.User;
import com.project.ImageGallery.schema.GallerySchema;
import com.project.ImageGallery.schema.ImagesUploadSchema;
import com.project.ImageGallery.service.GalleryService;
import com.project.ImageGallery.service.ImageService;
import com.project.ImageGallery.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/gallery")
@AllArgsConstructor
public class GalleryController extends BaseController {
    private final ImageService imageService;
    private final GalleryService galleryService;
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<Gallery> createGallery(@RequestBody GallerySchema payload) {
        User owner = verifyUserForOwnerThrowUnauthorized(payload.getOwnerId(), userService.getUserRepository());

        Gallery gallery = galleryService.createGallery(userService.getUser(payload.getOwnerId()), payload.getName());
        List<Image> images = payload.getImages().stream().map(
                schema -> imageService.createImage(owner, schema.getContent(), gallery, schema.getName())).toList();

        return new ResponseEntity<>(galleryService.addImagesTo(gallery.getId(), images), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gallery> getGallery(@PathVariable long id) {
        Gallery gallery = galleryService.getGallery(id);
        verifyUserForOwnerThrowUnauthorized(gallery.getOwner().getId(), userService.getUserRepository());

        return new ResponseEntity<>(gallery, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gallery> updateGallery(@PathVariable long id, @RequestBody GallerySchema payload) {
        Gallery gallery = galleryService.getGallery(id);
        verifyUserForOwnerThrowUnauthorized(gallery.getOwner().getId(), userService.getUserRepository());

        return new ResponseEntity<>(galleryService.updateGallery(Gallery.builder().name(payload.getName()).build()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GallerySchema> deleteGallery(@PathVariable long id) {
        Gallery gallery = galleryService.getGallery(id);
        verifyUserForOwnerThrowUnauthorized(gallery.getOwner().getId(), userService.getUserRepository());

        galleryService.deleteGallery(id);

        return new ResponseEntity<>(GallerySchema.builder().name(gallery.getName()).build(), HttpStatus.OK);
    }

    @PostMapping("/{id}/images")
    public ResponseEntity<Gallery> addImages(@PathVariable long id, @RequestBody ImagesUploadSchema payload) {
        Gallery gallery = galleryService.getGallery(id);
        verifyUserForOwnerThrowUnauthorized(gallery.getOwner().getId(), userService.getUserRepository());

        return new ResponseEntity<>(galleryService.addImagesTo(id, payload.getImages().stream().map(
                schema -> Image
                        .builder()
                        .owner(gallery.getOwner())
                        .gallery(gallery)
                        .content(schema.getContent())
                        .build()
                ).collect(Collectors.toList())), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/images")
    public ResponseEntity<Gallery> removeImages(@PathVariable long id, @RequestBody ImagesUploadSchema payload) {
        Gallery gallery = galleryService.getGallery(id);
        verifyUserForOwnerThrowUnauthorized(gallery.getOwner().getId(), userService.getUserRepository());

        return new ResponseEntity<>(galleryService.removeImagesFrom(id, payload.getImages().stream().map(
                schema -> Image
                        .builder()
                        .owner(gallery.getOwner())
                        .gallery(gallery)
                        .content(schema.getContent())
                        .build()
        ).collect(Collectors.toList())), HttpStatus.OK);
    }
}