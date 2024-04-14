package com.karolkusper.KINEMA.controllers;

import com.karolkusper.KINEMA.entity.Screening;
import com.karolkusper.KINEMA.service.Screening.ScreeningServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screenings")
public class ScreeningController {

    private final ScreeningServiceImpl screeningService;

    public ScreeningController(ScreeningServiceImpl screeningService) {
        this.screeningService = screeningService;
    }

    @GetMapping
    public List<Screening> getAllScreenings(){
        return screeningService.findAll();
    }

    @GetMapping("/{screeningId}")
    public Screening getScreeningDetailsById(@PathVariable Integer screeningId){
        return screeningService.findById(screeningId);
    }

    @PostMapping()
    public Screening addScreening(@RequestBody Screening screening){
        return  screeningService.save(screening);
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

    @DeleteMapping("/{screeningId}")
    public void deleteScreening(@PathVariable Integer screeningId){
        screeningService.deleteById(screeningId);
    }

}
