package com.karolkusper.KINEMA.service.SeatAvailability;

import com.karolkusper.KINEMA.dao.SeatAvailabilityRepository;
import com.karolkusper.KINEMA.entity.SeatAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SeatAvailabilityServiceImpl {

    private  final SeatAvailabilityRepository seatAvailabilityRepo;

    @Autowired

    public SeatAvailabilityServiceImpl(SeatAvailabilityRepository seatAvailabilityRepo) {
        this.seatAvailabilityRepo = seatAvailabilityRepo;
    }

    public SeatAvailability reserveSeat(int screeningId, int seatId) {
        SeatAvailability seatAvailability =  seatAvailabilityRepo
                .findByScreeningIdAndSeatId(screeningId, seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found for this screening"));

        if (seatAvailability.getAvailable()) {
            seatAvailability.setAvailable(false);
            return seatAvailabilityRepo.save(seatAvailability);
        } else {
            throw new RuntimeException("Seat already reserved");
        }
    }

    public List<SeatAvailability> findByScreeningId(int screeningId){
        return  seatAvailabilityRepo.findByScreeningId(screeningId);
    }
}
