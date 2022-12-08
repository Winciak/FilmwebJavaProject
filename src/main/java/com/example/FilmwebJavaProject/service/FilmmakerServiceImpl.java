package com.example.FilmwebJavaProject.service;

import com.example.FilmwebJavaProject.dao.FilmmakerRepository;
import com.example.FilmwebJavaProject.entity.Filmmaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmmakerServiceImpl implements FilmmakerService {

    private FilmmakerRepository filmmakerRepository;

    @Autowired
    public FilmmakerServiceImpl(FilmmakerRepository filmmakerRepository) {
        this.filmmakerRepository = filmmakerRepository;
    }


    @Override
    public List<Filmmaker> findAll() {
        return filmmakerRepository.findAll();
    }

    @Override
    public Filmmaker findById(int id) {

        Optional<Filmmaker> result = filmmakerRepository.findById(id);

        Filmmaker filmmaker;

        if (result.isPresent()) {
            filmmaker = result.get();
        }
        else {
            throw new RuntimeException("Did not find filmmaker id - " + id);
        }

        return filmmaker;
    }

    @Override
    public void save(Filmmaker filmmaker) {
        filmmakerRepository.save(filmmaker);
    }

    @Override
    public void deleteById(int id) {
        filmmakerRepository.deleteById(id);
    }
}
