package com.karolkusper.KINEMA.dao;

import com.karolkusper.KINEMA.entity.Screening;
import com.karolkusper.KINEMA.entity.SeatAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening,Integer> {
    @Query("SELECT s FROM Screening s WHERE s.movie.id = :movieId")
    List<Screening> findByMovieId(@Param("movieId") int movieId);
}
