package com.karolkusper.KINEMA.service.Movie;

import com.karolkusper.KINEMA.dao.MovieRepository;
import com.karolkusper.KINEMA.dao.ScreeningRepository;
import com.karolkusper.KINEMA.entity.Movie;
import com.karolkusper.KINEMA.entity.Screening;
import com.karolkusper.KINEMA.service.Screening.ScreeningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final ScreeningRepository screeningRepository;
    private final ScreeningServiceImpl screeningService;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, ScreeningRepository screeningRepository, ScreeningServiceImpl screeningService) {
        this.movieRepository = movieRepository;
        this.screeningRepository = screeningRepository;
        this.screeningService = screeningService;
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

    @Transactional
    public void deleteMovie(int movieId) {
        List<Screening> screenings = screeningRepository.findByMovieId(movieId);

        for (Screening screening : screenings) {
            screeningService.deleteScreening(screening.getId());
        }
        movieRepository.deleteById(movieId);
    }
}
