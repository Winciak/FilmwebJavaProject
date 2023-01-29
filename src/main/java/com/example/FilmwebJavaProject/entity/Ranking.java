package com.example.FilmwebJavaProject.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ranking")
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ranking", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "ranking")
    private List<Rankings_movies> rankings_moviesList;

    public Ranking() {
    }

    public Ranking(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ranking(int id, String name, List<Rankings_movies> rankings_moviesList) {
        this.id = id;
        this.name = name;
        this.rankings_moviesList = rankings_moviesList;
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

    public List<Rankings_movies> getRankings_moviesList() {
        return rankings_moviesList;
    }

    public void setRankings_moviesList(List<Rankings_movies> rankings_moviesList) {
        this.rankings_moviesList = rankings_moviesList;
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}