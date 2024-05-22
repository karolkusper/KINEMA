package com.karolkusper.KINEMA.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "seat_row")
    private int seatRow;

    @Column(name="seat_column")
    private int seatColumn;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cinema_hall_id", nullable = false)
    private CinemaHall cinemaHall;

    @Column(name = "is_available")

    private Boolean isAvailable;

    public Seat(){

    }
    public Seat(int id, int seatRow, int seatColumn, CinemaHall cinemaHall, Boolean isAvailable) {
        this.id = id;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.cinemaHall = cinemaHall;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", seatRow='" + seatRow + '\'' +
                ", seatColumn='" + seatColumn + '\'' +
                ", cinemaHall=" + cinemaHall +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
