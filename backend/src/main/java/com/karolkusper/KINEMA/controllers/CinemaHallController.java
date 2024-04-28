package com.karolkusper.KINEMA.controllers;

import com.karolkusper.KINEMA.entity.CinemaHall;
import com.karolkusper.KINEMA.service.CinemaHall.CinemaHallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cinemahall")
public class CinemaHallController {
    private final CinemaHallServiceImpl cinemaHallService;

    @Autowired
    public CinemaHallController(CinemaHallServiceImpl cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    @GetMapping
    public List<CinemaHall> getAllCinemaHalls(){
        return cinemaHallService.findAll();
    }

    @GetMapping("/{cinemaHallId}")
    public CinemaHall getCinemaHall(@PathVariable Integer cinemaHallId){
        return cinemaHallService.findById(cinemaHallId);
    }


}
