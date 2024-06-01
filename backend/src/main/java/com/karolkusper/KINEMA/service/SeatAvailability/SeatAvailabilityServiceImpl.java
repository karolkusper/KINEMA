package com.karolkusper.KINEMA.service.SeatAvailability;

import com.karolkusper.KINEMA.dao.SeatAvailabilityRepository;
import com.karolkusper.KINEMA.entity.Seat;
import com.karolkusper.KINEMA.entity.SeatAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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


    public List<SeatAvailability> reserveMultipleSeats(int screeningId, List<Integer> seatIds) {
        List<SeatAvailability> updatedSeats = new ArrayList<>();
        for (int seatId : seatIds) {
            SeatAvailability seatAvailability = seatAvailabilityRepo
                    .findByScreeningIdAndSeatId(screeningId, seatId)
                    .orElseThrow(() -> new RuntimeException("Seat not found for this screening"));
            if (seatAvailability.getAvailable()) {
                seatAvailability.setAvailable(false);
                updatedSeats.add(seatAvailabilityRepo.save(seatAvailability));
            } else {
                throw new RuntimeException("Seat already reserved");
            }
        }
        return updatedSeats;
    }

    public List<SeatAvailability> findByScreeningId(int screeningId){
        return  seatAvailabilityRepo.findByScreeningId(screeningId);
    }

    @Transactional
    public void setSeatsAvailable(int screeningId, List<Seat> seats) {
        for (Seat seat : seats) {
            SeatAvailability seatAvailability = seatAvailabilityRepo
                    .findByScreeningIdAndSeatId(screeningId, seat.getId())
                    .orElseThrow(() -> new RuntimeException("Seat not found for this screening"));
            seatAvailability.setAvailable(true);
            seatAvailabilityRepo.save(seatAvailability);
        }
    }
}
