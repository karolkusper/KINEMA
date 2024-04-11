package com.karolkusper.KINEMA.dao;

import com.karolkusper.KINEMA.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

}
