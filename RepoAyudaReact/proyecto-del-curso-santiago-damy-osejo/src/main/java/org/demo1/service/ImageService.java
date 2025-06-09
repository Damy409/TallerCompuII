package org.demo1.service;

import java.util.List;

import org.demo1.model.Image;
import org.demo1.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository repo) {
        this.imageRepository = repo;
    }

    public Image save(MultipartFile file) throws IOException, java.io.IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setData(file.getBytes());
        return imageRepository.save(image);
    }

    public Image get(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public List<Image> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
}
