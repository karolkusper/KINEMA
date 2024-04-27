package com.karolkusper.KINEMA.service.Screening;

import com.karolkusper.KINEMA.dao.ScreeningRepository;
import com.karolkusper.KINEMA.entity.Screening;
import com.karolkusper.KINEMA.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ScreeningServiceImpl implements ScreeningService{

    private final ScreeningRepository screeningRepository;

    @Autowired
    public ScreeningServiceImpl(ScreeningRepository repository) {
        this.screeningRepository = repository;
    }

    //TO DO
    @Override
    public List<Screening> findAll() {
        return screeningRepository.findAll();
    }

    @Override
    public Screening findById(int id) {
        Screening screening = null;
        Optional<Screening> result = screeningRepository.findById(id);

        if(result.isPresent()){
            screening=result.get();
        }
        else{
            throw new RuntimeException("Did not find screening with this id: "+id);
        }
        return screening;
    }

    @Override
    public Screening save(Screening screening) {
        int id = screening.getId();
        if(id==0){
            return screeningRepository.save(screening);
        }
        else{
            Screening existingScreening = screeningRepository.findById(id)
                    .orElseThrow(()->new RuntimeException("There is no screening with id="+id));

            existingScreening.setCinemaHall(screening.getCinemaHall());
            existingScreening.setMovie(screening.getMovie());
            existingScreening.setStartTime(screening.getStartTime());
            existingScreening.setEndTime(screening.getEndTime());
            existingScreening.setFormat(screening.getFormat());

            return screeningRepository.save(existingScreening);
        }
    }



    @Override
    public void deleteById(int id) {
        //to do
        System.out.println("Usuwanie screening id="+id);
    }
}
