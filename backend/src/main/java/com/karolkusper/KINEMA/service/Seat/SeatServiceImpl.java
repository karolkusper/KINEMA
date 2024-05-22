package com.karolkusper.KINEMA.service.Seat;

import com.karolkusper.KINEMA.dao.SeatRepository;
import com.karolkusper.KINEMA.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService{

    private final SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Override
    public List<Seat> findAll() {
        return seatRepository.findAll();
    }

    public List<Seat> findByCinemaHallId(int id){
        return seatRepository.findSeatsByCinemaHallId(id);
    }


    public Seat reserveSeat(int seatId) {
        Seat seat = seatRepository.findById(seatId).orElseThrow(() -> new RuntimeException("Seat not found"));
        if (seat.getAvailable()) {
            seat.setAvailable(false);
            return seatRepository.save(seat);
        } else {
            throw new RuntimeException("Seat already reserved");
        }
    }

    @Override
    public Seat findById(int id) {
        Seat seat = null;
        Optional<Seat> result = seatRepository.findById(id);
        if(result.isPresent()){
            seat=result.get();
        }
        else {
            throw new RuntimeException("There is no seat with id="+id);
        }
        return seat;
    }

    @Override
    public Seat save(Seat seat) {
        int id = seat.getId();
        if(id==0){
           return seatRepository.save(seat);
        }
        else{
            Seat existingSeat = seatRepository.findById(id)
                    .orElseThrow(()->new RuntimeException("There is no seat with id="+id));
            existingSeat.setSeatColumn(seat.getSeatColumn());
            existingSeat.setSeatRow(seat.getSeatRow());
            existingSeat.setCinemaHall(seat.getCinemaHall());
            existingSeat.setAvailable(seat.getAvailable());

            return seatRepository.save(existingSeat);
        }

    }

    @Override
    public void deleteById(int id) {
        //to do
        System.out.println("Usuwanie seat o id="+id);
    }
}
