package com.karolkusper.KINEMA.service.Booking;

import com.karolkusper.KINEMA.entity.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> findAll();
    Booking findById(int id);

    Booking save(Booking booking);

    void deleteById(int id);
}
