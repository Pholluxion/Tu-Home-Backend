package com.phollux.tuhome.image.service;

import com.phollux.tuhome.image.model.ImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface ImageService {
    List<ImageDTO> findAll();

    ImageDTO get(UUID id);

    void delete(UUID id);

    ImageDTO create(MultipartFile file) throws IOException;

}
