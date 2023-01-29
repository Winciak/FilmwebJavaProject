package com.example.FilmwebJavaProject.dao;

import com.example.FilmwebJavaProject.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends JpaRepository<Ranking,Integer> {

    Ranking findRankingByName(String name);
}
