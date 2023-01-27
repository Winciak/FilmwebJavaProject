package com.example.FilmwebJavaProject.service;


import com.example.FilmwebJavaProject.entity.ImageEntity;

import java.util.List;


public interface ImageService {

    List<ImageEntity> findAll();

    ImageEntity findById(int id);

    void save(ImageEntity image);

    void deleteById(int id);

    ImageEntity findImageEntityByImage_path(String image_path);
}
