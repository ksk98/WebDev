package com.project.ImageGallery.schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ImageSchema {
    private String name;
    private byte[] content;
}
