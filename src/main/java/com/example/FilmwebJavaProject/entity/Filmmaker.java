package com.example.FilmwebJavaProject.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "filmmaker")
public class Filmmaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filmmaker", nullable = false)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "death_date")
    private Date deathDate;

    @Column(name = "about")
    private String about;

    @OneToMany(mappedBy = "filmmaker")
    private List<Filmmakers_movies> filmmakers_movies;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "filmmakers_images",
            joinColumns = @JoinColumn(name = "filmmaker_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Collection<ImageEntity> images;

    public Filmmaker() {
    }

    public Filmmaker(int id, String firstName, String lastName, Date birthDate, String birthPlace, Date deathDate, String about, List<Filmmakers_movies> filmmakers_movies) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.deathDate = deathDate;
        this.about = about;
        this.filmmakers_movies = filmmakers_movies;
    }

    public Filmmaker(int id, String firstName, String lastName, Date birthDate, String birthPlace, Date deathDate, String about, List<Filmmakers_movies> filmmakers_movies, Collection<ImageEntity> images) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
        this.deathDate = deathDate;
        this.about = about;
        this.filmmakers_movies = filmmakers_movies;
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Filmmakers_movies> getFilmmakers_movies() {
        return filmmakers_movies;
    }

    public void setFilmmakers_movies(List<Filmmakers_movies> filmmakers_movies) {
        this.filmmakers_movies = filmmakers_movies;
    }

    public Collection<ImageEntity> getImages() {
        return images;
    }

    public void setImages(Collection<ImageEntity> images) {
        this.images = images;
    }
}