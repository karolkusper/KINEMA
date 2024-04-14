package com.karolkusper.KINEMA.controllers;

import com.karolkusper.KINEMA.entity.Movie;
import com.karolkusper.KINEMA.service.Movie.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieServiceImpl movieService;

    @Autowired
    public MovieController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    @GetMapping()
    public List<Movie> getAllMovies(){
        return movieService.findAll();
    }

    @GetMapping("/{movieId}")
    public Movie getMovieDetailsById(@PathVariable Integer movieId){
        return movieService.findById(movieId);
    }

    @PostMapping()
    public Movie addMovie(@RequestBody Movie movie)
    {
        movie.setId(0);
        return movieService.save(movie);
    }

    @PutMapping()
    public Movie updateMovie(@RequestBody Movie movie)
    {
        Movie existingMovie = movieService.findById(movie.getId());
        if(existingMovie !=null)
        {
            return movieService.save(movie);
        }
        else
        {
            throw new RuntimeException("There is no movie with id="+movie.getId());
        }
    }

    @DeleteMapping("/{movieId}")
    public void deleteMovie(@PathVariable Integer movieId)
    {
        movieService.deleteById(movieId);
    }
}
