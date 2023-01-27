package com.example.FilmwebJavaProject.dao;


import com.example.FilmwebJavaProject.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity,Integer> {

    ImageEntity findImageEntityByImagePath(String image_path);
}
