package com.example.FilmwebJavaProject.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Table
@Entity(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genre", nullable = false)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "is required")
    private String name;

    public Genre() {
    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
