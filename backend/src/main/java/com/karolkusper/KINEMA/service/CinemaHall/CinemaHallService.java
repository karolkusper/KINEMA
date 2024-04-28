package com.karolkusper.KINEMA.service.CinemaHall;

import com.karolkusper.KINEMA.entity.CinemaHall;

import java.util.List;

public interface CinemaHallService {
    List<CinemaHall> findAll();
    CinemaHall findById(int id);

    CinemaHall save(CinemaHall movie);
    void deleteById(int id);
}
