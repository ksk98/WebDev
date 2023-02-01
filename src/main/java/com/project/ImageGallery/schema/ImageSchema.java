package com.project.ImageGallery.schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageSchema {
    private long id;
    private String name, contentHeader;
    private byte[] content;
}
