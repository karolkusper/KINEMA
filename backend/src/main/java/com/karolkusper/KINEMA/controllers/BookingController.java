package com.karolkusper.KINEMA.controllers;

import com.karolkusper.KINEMA.entity.*;
import com.karolkusper.KINEMA.requests.BookingRequest;
import com.karolkusper.KINEMA.service.Booking.BookingServiceImpl;
import com.karolkusper.KINEMA.service.Screening.ScreeningServiceImpl;
import com.karolkusper.KINEMA.service.Seat.SeatServiceImpl;
import com.karolkusper.KINEMA.service.SeatAvailability.SeatAvailabilityServiceImpl;
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
    private final SeatAvailabilityServiceImpl seatAvailabilityService;

    @Autowired
    public BookingController(BookingServiceImpl bookingService, UserServiceImpl userService, ScreeningServiceImpl screeningService, SeatServiceImpl seatService, SeatAvailabilityServiceImpl seatAvailabilityService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.screeningService = screeningService;
        this.seatService = seatService;
        this.seatAvailabilityService = seatAvailabilityService;
    }

    @GetMapping()
    public List<Booking> getAllBookings(){
        return bookingService.findAll();
    }



    @GetMapping("/admin/{bookingId}")
    public Booking getBookingDetailsById(@PathVariable Integer bookingId){
        return bookingService.findById(bookingId);
    }

    @GetMapping("/{userId}")
    public List<Booking> getBookingByUserId(@PathVariable Integer userId){
        return bookingService.findAllByUserId(userId);
    }


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
    public ResponseEntity<Void> deleteBooking(@PathVariable int bookingId) {
        Booking booking = bookingService.findById(bookingId);
        if (booking == null) {
            return ResponseEntity.notFound().build();
        }

        seatAvailabilityService.setSeatsAvailable(booking.getScreening().getId(), booking.getSeats());
        bookingService.deleteById(bookingId);

        return ResponseEntity.ok().build();
    }
}
