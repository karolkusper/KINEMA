package com.karolkusper.KINEMA.dao;

import com.karolkusper.KINEMA.entity.Seat;
import com.karolkusper.KINEMA.entity.SeatAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


//@Repository
public interface SeatAvailabilityRepository extends JpaRepository<SeatAvailability,Integer> {
    Optional<SeatAvailability> findByScreeningIdAndSeatId(int screeningId, int seatId);

    @Query("SELECT s FROM SeatAvailability s WHERE s.screening.id = :screeningId")
    List<SeatAvailability> findByScreeningId(@Param("screeningId") int screeningId);
}
