package com.karolkusper.KINEMA.service.Screening;

import com.karolkusper.KINEMA.dao.ScreeningRepository;
import com.karolkusper.KINEMA.dao.SeatAvailabilityRepository;
import com.karolkusper.KINEMA.dao.SeatRepository;
import com.karolkusper.KINEMA.entity.Screening;
import com.karolkusper.KINEMA.entity.Seat;
import com.karolkusper.KINEMA.entity.SeatAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ScreeningServiceImpl implements ScreeningService{

    private final ScreeningRepository screeningRepository;


    private final SeatRepository seatRepository;

    private final SeatAvailabilityRepository seatAvailabilityRepository;

    @Autowired
    public ScreeningServiceImpl(ScreeningRepository repository, SeatRepository seatRepository, SeatAvailabilityRepository seatAvailabilityRepository) {
        this.screeningRepository = repository;
        this.seatRepository = seatRepository;
        this.seatAvailabilityRepository = seatAvailabilityRepository;
    }

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
            Screening newScreening = screeningRepository.save(screening);
            initializeSeatAvailability(newScreening);
            return newScreening;
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


    private void initializeSeatAvailability(Screening screening) {
        List<Seat> seats = seatRepository.findSeatsByCinemaHallId(screening.getCinemaHall().getId());
        for (Seat seat : seats) {
            SeatAvailability seatAvailability = new SeatAvailability();
            seatAvailability.setScreening(screening);
            seatAvailability.setSeat(seat);
            seatAvailability.setAvailable(true);
            seatAvailabilityRepository.save(seatAvailability);
        }
    }

    @Transactional
    public void deleteScreening(int screeningId) {

        seatAvailabilityRepository.deleteByScreeningId(screeningId);
        screeningRepository.deleteById(screeningId);
    }
}
