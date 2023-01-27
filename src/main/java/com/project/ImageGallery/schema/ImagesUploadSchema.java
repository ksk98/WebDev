package com.project.ImageGallery.schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class ImagesUploadSchema {
    private List<ImageSchema> images;
}
