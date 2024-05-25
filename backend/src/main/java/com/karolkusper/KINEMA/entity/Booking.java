package com.karolkusper.KINEMA.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;
    @Column(name = "ticket_type")
    private String ticketType;
    @ManyToMany
    @JoinTable(
            name = "booking_seats",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    private List<Seat> seats;

    public Booking(){}

    public Booking(User user, Screening screening, String ticketType, List<Seat> seatIds) {
        this.user = user;
        this.screening = screening;
        this.ticketType = ticketType;
        this.seats = seatIds;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seatIds) {
        this.seats = seatIds;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", user=" + user +
                ", screening=" + screening +
                ", ticketType='" + ticketType + '\'' +
                ", seats=" + seats +
                '}';
    }
}
