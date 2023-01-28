package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService
{
    @Autowired
    MovieRepository movieRepository;

    public String addMovie(Movie movie) {
        return movieRepository.addMovie(movie);
    }

    public String addDirector(Director director) {
        return movieRepository.addDirector(director);
    }

    public String addMovieDirectorPair(String mName, String dName) {
        return movieRepository.addMovieDirectorPair(mName, dName);
    }

    public Movie getMovieByName(String mName) {
        return movieRepository.getMovieByName(mName);
    }

    public Director getDirectorByName(String dName) {
        return movieRepository.getDirectorByName(dName);
    }

    public List<String> getMoviesByDirectorName(String dName) {
        return movieRepository.getMoviesByDirectorName(dName);
    }

    public List<String> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public String deleteDirectorByName(String dName) {
        return movieRepository.deleteDirectorByName(dName);
    }

    public String deleteAllDirectors() {
        return movieRepository.deleteAllDirectors();
    }
}

