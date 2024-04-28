package com.karolkusper.KINEMA.service.CinemaHall;

import com.karolkusper.KINEMA.dao.CinemaHallRepository;
import com.karolkusper.KINEMA.entity.CinemaHall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaHallServiceImpl implements CinemaHallService{

    private final CinemaHallRepository cinemaHallRepository;


    @Autowired
    public CinemaHallServiceImpl(CinemaHallRepository cinemaHallRepository) {
        this.cinemaHallRepository = cinemaHallRepository;
    }

    @Override
    public List<CinemaHall> findAll() {
        return cinemaHallRepository.findAll();
    }

    @Override
    public CinemaHall findById(int id) {
        CinemaHall cinemaHall = null;
        Optional<CinemaHall> result = cinemaHallRepository.findById(id);
        if(result.isPresent()){
            cinemaHall=result.get();
            return cinemaHall;
        }
        else{
            throw new RuntimeException("There is no CinemaHall with id="+id);
        }
    }

    @Override
    public CinemaHall save(CinemaHall cinemaHall) {
        int id = cinemaHall.getId();
        if(id==0){
            return cinemaHallRepository.save(cinemaHall);
        }
        else {
            CinemaHall existingCinemaHall = cinemaHallRepository.findById(id)
                    .orElseThrow(()->new RuntimeException("There is no cinemaHall with id="+id));

            existingCinemaHall.setHallName(cinemaHall.getHallName());
            existingCinemaHall.setSeatsCapacity(cinemaHall.getSeatsCapacity());

            return cinemaHallRepository.save(existingCinemaHall);
        }

    }

    @Override
    public void deleteById(int id) {
        //to do
        System.out.println("Usuwanie cinemaHall id="+id);
    }
}
