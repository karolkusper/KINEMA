package com.karolkusper.KINEMA.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "seat_availability")
public class SeatAvailability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Column(name = "is_available")
    private Boolean isAvailable;

    public SeatAvailability(){}

    public SeatAvailability(Screening screening, Seat seat, Boolean isAvailable) {
        this.screening = screening;
        this.seat = seat;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "SeatAvailability{" +
                "id=" + id +
                ", screening=" + screening +
                ", seat=" + seat +
                ", isAvailable=" + isAvailable +
                '}';
    }
}