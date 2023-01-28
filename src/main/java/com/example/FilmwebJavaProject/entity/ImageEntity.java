package com.example.FilmwebJavaProject.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToMany(mappedBy = "images")
    private Collection<Movie> movies;

    public ImageEntity() {
    }

    public ImageEntity(Integer id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
    }

    public ImageEntity(Integer id, String name, String imagePath, Collection<Movie> movies) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.movies = movies;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Collection<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Collection<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "ImageEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image_path='" + imagePath + '\'' +
                '}';
    }
}