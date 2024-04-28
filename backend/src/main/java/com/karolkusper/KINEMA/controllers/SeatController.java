package com.karolkusper.KINEMA.controllers;

import com.karolkusper.KINEMA.entity.Seat;
import com.karolkusper.KINEMA.service.Seat.SeatServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seats")
public class SeatController {
    private final SeatServiceImpl seatService;

    public SeatController(SeatServiceImpl seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/{seatId}")
    public Seat getSeatById(@PathVariable Integer seatId){
        return seatService.findById(seatId);
    }
}
