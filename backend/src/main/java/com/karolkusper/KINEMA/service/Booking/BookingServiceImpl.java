package com.karolkusper.KINEMA.service.Booking;

import com.karolkusper.KINEMA.dao.BookingRepository;
import com.karolkusper.KINEMA.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(int id) {
        Booking booking = null;
        Optional<Booking> result = bookingRepository.findById(id);
        if(result.isPresent()){
            booking=result.get();

        }
        else{
            throw new RuntimeException("There is no booking with id="+id);
        }
        return booking;
    }

    public List<Booking> findAllByUserId(int userId)
    {
        return  bookingRepository.findByUserId(userId);
    }

    @Override
    public Booking save(Booking booking) {
        int id = booking.getId();

        if(id==0){
            return bookingRepository.save(booking);
        }
        else{
            Booking existingBooking =bookingRepository.findById(id).orElseThrow(()->new RuntimeException("There is no booking with id="+id));

            existingBooking.setUser(booking.getUser());
            existingBooking.setScreening(booking.getScreening());
            existingBooking.setTicketType(booking.getTicketType());
            existingBooking.setSeats(booking.getSeats());

            return  bookingRepository.save(existingBooking);

        }
    }

    @Override
    public void deleteById(int id) {
        bookingRepository.deleteById(id);
    }
}
