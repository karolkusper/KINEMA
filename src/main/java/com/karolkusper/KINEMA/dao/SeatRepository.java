package com.karolkusper.KINEMA.dao;

import com.karolkusper.KINEMA.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Integer> {
}
