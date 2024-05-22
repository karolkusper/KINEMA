package com.karolkusper.KINEMA.controllers;

import com.karolkusper.KINEMA.entity.SeatAvailability;
import com.karolkusper.KINEMA.service.SeatAvailability.SeatAvailabilityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/seat-availability")
public class SeatAvailabilityController {

    private final SeatAvailabilityServiceImpl seatService;

    @Autowired
    public SeatAvailabilityController(SeatAvailabilityServiceImpl  seatService) {
        this.seatService = seatService;
    }
    @GetMapping("/{screeningId}")
    public List<SeatAvailability> getSeatAvailability(@PathVariable int screeningId) {
        return seatService.findByScreeningId(screeningId);
    }
    @PostMapping("/reserve")
    public SeatAvailability reserveSeat(@RequestBody Map<String, Integer> request) {
        int screeningId = request.get("screeningId");
        int seatId = request.get("seatId");
        return seatService.reserveSeat(screeningId, seatId);
    }
}
