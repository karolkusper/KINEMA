package com.karolkusper.KINEMA.controllers;

import com.karolkusper.KINEMA.entity.CinemaHall;
import com.karolkusper.KINEMA.entity.Movie;
import com.karolkusper.KINEMA.entity.Screening;
import com.karolkusper.KINEMA.requests.ScreeningRequest;
import com.karolkusper.KINEMA.service.CinemaHall.CinemaHallServiceImpl;
import com.karolkusper.KINEMA.service.Movie.MovieServiceImpl;
import com.karolkusper.KINEMA.service.Screening.ScreeningServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screenings")
public class ScreeningController {

    private final ScreeningServiceImpl screeningService;
    private final MovieServiceImpl movieService;
    private final CinemaHallServiceImpl cinemaHallService;

    public ScreeningController(ScreeningServiceImpl screeningService, MovieServiceImpl movieService, CinemaHallServiceImpl cinemaHallService) {
        this.screeningService = screeningService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @GetMapping
    public List<Screening> getAllScreenings(){
        return screeningService.findAll();
    }

    @GetMapping("/{screeningId}")
    public Screening getScreeningDetailsById(@PathVariable Integer screeningId){
        return screeningService.findById(screeningId);
    }

//    @PostMapping()
//    public Screening addScreening(@RequestBody Screening screening){
//        return  screeningService.save(screening);
//    }

    @PostMapping()
    public Screening addScreening(@RequestBody ScreeningRequest screeningRequest) {
        System.out.println(screeningRequest.toString());
        CinemaHall cinemaHall = cinemaHallService.findById(screeningRequest.getCinemaHall());
        Movie movie = movieService.findById(screeningRequest.getMovie());

        Screening screening = new Screening();
        screening.setCinemaHall(cinemaHall);
        screening.setMovie(movie);
        screening.setStartTime(screeningRequest.getStartTime());
        screening.setEndTime(screeningRequest.getEndTime());
        screening.setFormat(screeningRequest.getFormat());

        return screeningService.save(screening);
    }

    @PutMapping()
    public Screening updateScreening(@RequestBody Screening screening){
        Screening existingScreening = screeningService.findById(screening.getId());

        if(existingScreening!=null){
            return screeningService.save(screening);
        }
        else{
            throw new RuntimeException("There is no screening with id="+screening.getId());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScreening(@PathVariable int id) {
        screeningService.deleteScreening(id);
        return ResponseEntity.noContent().build();
    }

}
