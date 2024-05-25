package com.karolkusper.KINEMA.controllers;

import com.karolkusper.KINEMA.entity.Booking;
import com.karolkusper.KINEMA.entity.Screening;
import com.karolkusper.KINEMA.entity.Seat;
import com.karolkusper.KINEMA.entity.User;
import com.karolkusper.KINEMA.requests.BookingRequest;
import com.karolkusper.KINEMA.service.Booking.BookingServiceImpl;
import com.karolkusper.KINEMA.service.Screening.ScreeningServiceImpl;
import com.karolkusper.KINEMA.service.Seat.SeatServiceImpl;
import com.karolkusper.KINEMA.service.User.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingServiceImpl bookingService;
    private final UserServiceImpl userService;
    private final ScreeningServiceImpl screeningService;
    private final SeatServiceImpl seatService;

    @Autowired
    public BookingController(BookingServiceImpl bookingService, UserServiceImpl userService, ScreeningServiceImpl screeningService, SeatServiceImpl seatService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.screeningService = screeningService;
        this.seatService = seatService;
    }

    @GetMapping()
    public List<Booking> getAllBookings(){
        return bookingService.findAll();
    }

    @GetMapping("/{bookingId}")
    public Booking getBookingDetailsById(@PathVariable Integer bookingId){
        return bookingService.findById(bookingId);
    }

//    @PostMapping
//    public Booking addBooking(@RequestBody Booking booking){
//        System.out.println("Received booking: " + booking);
//        return bookingService.save(booking);
//    }

    @PostMapping()
    public ResponseEntity<Booking> addBooking(@RequestBody BookingRequest bookingRequest) {
        User user = userService.findById(bookingRequest.getUserId());

        Screening screening = screeningService.findById(bookingRequest.getScreeningId());

        List<Seat> seats = new ArrayList<>();
        for (int id : bookingRequest.getSeatIds())
        {
            seats.add(seatService.findById(id));
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setScreening(screening);
        booking.setTicketType(bookingRequest.getTicketType());
        booking.setSeats(seats);

        Booking savedBooking = bookingService.save(booking);
        return ResponseEntity.ok(savedBooking);
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
