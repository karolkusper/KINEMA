package com.karolkusper.KINEMA.dao;

import com.karolkusper.KINEMA.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat,Integer> {
    @Query("SELECT s FROM Seat s WHERE s.cinemaHall.id = :cinemaHallId")
    List<Seat> findSeatsByCinemaHallId(@Param("cinemaHallId") int cinemaHallId);
}
