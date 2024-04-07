package com.karolkusper.KINEMA.controllers;

import com.karolkusper.KINEMA.entity.Booking;
import com.karolkusper.KINEMA.service.Booking.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingServiceImpl bookingService;

    @Autowired
    public BookingController(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping()
    public List<Booking> getAllBookings(){
        return bookingService.findAll();
    }

    @GetMapping("/{bookingId}")
    public Booking getBookingDetailsById(@PathVariable Integer bookingId){
        return bookingService.findById(bookingId);
    }

    @PostMapping
    public Booking addBooking(@RequestBody Booking booking){
        return bookingService.save(booking);
    }

    @PutMapping
    public Booking updateBooking(@RequestBody Booking booking){
        Booking existingBooking = bookingService.findById(booking.getId());
        if(existingBooking!=null){
            return  bookingService.save(booking);
        }
        else
        {
            throw new RuntimeException("There is no booking with id="+booking.getId());
        }
    }

    @DeleteMapping("/{bookingId}")
    public void deleteBooking(@PathVariable Integer bookingId){
        bookingService.deleteById(bookingId);
    }
}
