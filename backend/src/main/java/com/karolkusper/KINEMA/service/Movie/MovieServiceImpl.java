package com.karolkusper.KINEMA.service.Movie;

import com.karolkusper.KINEMA.dao.MovieRepository;
import com.karolkusper.KINEMA.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(int id) {
        Movie movie = null;

        Optional<Movie> result = movieRepository.findById(id);
        if(result.isPresent()){
            movie = result.get();
        }
        else{
            throw new RuntimeException("Did not find movie with this id: "+id);
        }

        return movie;
    }

    @Override
    public Movie save(Movie movie) {

        if(movie.getId()==0){ //adding new movie
            return movieRepository.save(movie);
        }
        else{ //update movie
            int movieId = movie.getId();
            Movie existingMovie = movieRepository.findById(movieId)
                    .orElseThrow(() -> new RuntimeException("Movie not found with id: " + movieId));

            existingMovie.setTitle(movie.getTitle());
            existingMovie.setDescription(movie.getDescription());
            existingMovie.setDirector(movie.getDirector());
            existingMovie.setProductionYear(movie.getProductionYear());
            existingMovie.setMovieGenre(movie.getMovieGenre());

            return movieRepository.save(existingMovie);

        }
    }

    @Override
    public void deleteById(int id) {
        //to do
        System.out.println("Usuwanie filmu...");
    }
}
