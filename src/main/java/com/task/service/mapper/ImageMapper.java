package com.task.service.mapper;

import com.task.entity.Image;
import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    default byte[] toBytes(Image image) {
        return image != null ? image.getImage() : null;
    }

    default Image toEntity(byte[] data) {
        if (data == null) return null;
        return Image.builder().image(data).build();
    }
}
