package com.example.FilmwebJavaProject.service;

import com.example.FilmwebJavaProject.dao.RankingMoviesRepository;
import com.example.FilmwebJavaProject.dao.RankingRepository;
import com.example.FilmwebJavaProject.entity.Ranking;
import com.example.FilmwebJavaProject.entity.Rankings_movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RankingServiceImpl implements RankingService {

    private RankingRepository rankingRepository;

    private RankingMoviesRepository rankingMoviesRepository;

    @Autowired
    public RankingServiceImpl(RankingRepository rankingRepository, RankingMoviesRepository rankingMoviesRepository) {
        this.rankingRepository = rankingRepository;
        this.rankingMoviesRepository = rankingMoviesRepository;
    }

    @Override
    public Ranking findRankingByName(String name) {
        return rankingRepository.findRankingByName(name);
    }

    @Override
    public List<Rankings_movies> findAllByRankingId(int id) {
        return rankingMoviesRepository.findAllMoviesByRankingId(id);
    }

    @Override
    public List<Ranking> findAll() {
        return rankingRepository.findAll();
    }

    @Override
    public Ranking findById(int id) {

        Optional<Ranking> result = rankingRepository.findById(id);

        Ranking ranking;

        if (result.isPresent()) {
            ranking = result.get();
        }
        else {
            throw new RuntimeException("Did not find ranking id - " + id);
        }

        return ranking;
    }

    @Override
    public void save(Ranking ranking) {
        rankingRepository.save(ranking);
    }

    @Override
    public void saveRankingsMovies(Rankings_movies rankings_movies) {
        rankingMoviesRepository.save(rankings_movies);
    }

    @Override
    public void deleteById(int id) {
        rankingRepository.deleteById(id);
    }
}
