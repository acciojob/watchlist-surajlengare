package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController
{
    @Autowired
    MovieService movieService;

    // 1) add movie to db movieMap
    @PostMapping("/movies/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie)
    {
        String msg = movieService.addMovie(movie);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    // 2) add director to db directorMap
    @PostMapping("/movies/add-director")
    public ResponseEntity addDirector(@RequestBody Director director)
    {
        String msg = movieService.addDirector(director);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    // 3) add movie-director pair to db pairMap
    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("m") String mName, @RequestParam("d") String dName)
    {
        String msg = movieService.addMovieDirectorPair(mName, dName);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    // 4) get movie by name from db movieMap
    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String mName)
    {
        Movie mv = movieService.getMovieByName(mName);
        return new ResponseEntity<>(mv, HttpStatus.FOUND);
    }

    // 5) get director by name from db directorMap
    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String dName)
    {
        Director dir = movieService.getDirectorByName(dName);
        return new ResponseEntity<>(dir, HttpStatus.FOUND);
    }

    // 6) get list of movie names of particular director from db pairMap
    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String dName)
    {
        List<String> list = movieService.getMoviesByDirectorName(dName);
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    // 7) get all movies from db movieMap
    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies()
    {
        List<String> list = movieService.findAllMovies();
        return new ResponseEntity<>(list, HttpStatus.FOUND);
    }

    // 8) delet all movies of particular director from db movieMap, & that director from directorMap & taht paiir from pairMap
    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("d") String dName)
    {
        String msg = movieService.deleteDirectorByName(dName);
        return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
    }

    // 9) delet all directors from directorMap & all songs by that directors from movieMap
    // No need to delet anything from pairMap
    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors()
    {
        String msg = movieService.deleteAllDirectors();
        return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
    }

}
