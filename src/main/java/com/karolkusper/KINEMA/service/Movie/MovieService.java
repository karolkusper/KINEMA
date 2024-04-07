package com.karolkusper.KINEMA.service.Movie;



import com.karolkusper.KINEMA.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(int id);

    Movie save(Movie movie);
    void deleteById(int id);
}
