package com.example.FilmwebJavaProject.service;

import com.example.FilmwebJavaProject.dao.ImageRepository;
import com.example.FilmwebJavaProject.entity.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<ImageEntity> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public ImageEntity findById(int id) {

        Optional<ImageEntity> result = imageRepository.findById(id);

        ImageEntity image;

        if (result.isPresent()) {
            image = result.get();
        }
        else {
            throw new RuntimeException("Did not find image id - " + id);
        }

       return image;
    }

    @Override
    public void save(ImageEntity image) {

        imageRepository.save(image);
    }

    @Override
    public void deleteById(int id) {

        imageRepository.deleteById(id);
    }

    @Override
    public ImageEntity findImageEntityByImage_path(String image_path) {
        return imageRepository.findImageEntityByImagePath(image_path);
    }
}
