package com.karolkusper.KINEMA.service.Seat;

import com.karolkusper.KINEMA.entity.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> findAll();
    Seat findById(int id);

    Seat save(Seat movie);
    void deleteById(int id);
}
