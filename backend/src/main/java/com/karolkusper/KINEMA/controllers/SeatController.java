package com.karolkusper.KINEMA.controllers;

import com.karolkusper.KINEMA.entity.Seat;
import com.karolkusper.KINEMA.entity.SeatAvailability;
import com.karolkusper.KINEMA.service.Seat.SeatServiceImpl;
import com.karolkusper.KINEMA.service.SeatAvailability.SeatAvailabilityServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seats")
public class SeatController {
    private final SeatServiceImpl seatService;
    public SeatController(SeatServiceImpl seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/{cinemaHallId}")
    public List<Seat> getSeatsFromHall(@PathVariable Integer cinemaHallId){
        return seatService.findByCinemaHallId(cinemaHallId);
    }

    @GetMapping("/hall/{seatId}")
    public Seat getSeatById(@PathVariable Integer seatId){
        return seatService.findById(seatId);
    }

}
