package com.test.dream_shop.service.image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.test.dream_shop.dto.ImageDto;
import com.test.dream_shop.model.Image;

public interface IImageService {

    Image getImageById(Long id);
    void deleteImage(Long id);
    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file, Long imageId);
}
