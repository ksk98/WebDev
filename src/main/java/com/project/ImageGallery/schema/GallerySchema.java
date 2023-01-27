package com.project.ImageGallery.schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class GallerySchema {
    private long ownerId;
    private String name;
    private List<ImageSchema> images;
}
