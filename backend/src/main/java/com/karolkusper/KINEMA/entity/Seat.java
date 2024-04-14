package com.karolkusper.KINEMA.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "seat_row")
    private String seatRow;

    @Column(name="seat_column")
    private String seatColumn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_hall_id", nullable = false)
    private CinemaHall cinemaHall;

    @Column(name = "is_available")

    private Boolean isAvailable;

    public Seat(){

    }
    public Seat(int id, String seatRow, String seatColumn, CinemaHall cinemaHall, Boolean isAvailable) {
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

    public String getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(String seatRow) {
        this.seatRow = seatRow;
    }

    public String getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(String seatColumn) {
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
