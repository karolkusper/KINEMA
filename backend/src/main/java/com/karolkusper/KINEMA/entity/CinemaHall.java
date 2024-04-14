package com.karolkusper.KINEMA.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cinema_halls")

public class CinemaHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "hall_name")
    private String hallName;

    @Column(name="seats_capacity")

    private int seatsCapacity;

    public CinemaHall(){}

    public CinemaHall(String hallName, int seatsCapacity) {
        this.hallName = hallName;
        this.seatsCapacity = seatsCapacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getSeatsCapacity() {
        return seatsCapacity;
    }

    public void setSeatsCapacity(int seatsCapacity) {
        this.seatsCapacity = seatsCapacity;
    }

    @Override
    public String toString() {
        return "CinemaHall{" +
                "id=" + id +
                ", hallName='" + hallName + '\'' +
                ", seatsCapacity=" + seatsCapacity +
                '}';
    }
}
