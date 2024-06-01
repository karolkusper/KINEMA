package com.karolkusper.KINEMA.requests;

import java.util.Date;

public class ScreeningRequest {

        private int cinemaHall;
        private int movie;


        private Date startTime;
        private Date endTime;
        private String format;

    public int getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(int cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public int getMovie() {
        return movie;
    }

    public void setMovie(int movie) {
        this.movie = movie;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "ScreeningRequest{" +
                "cinemaHall=" + cinemaHall +
                ", movie=" + movie +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", format='" + format + '\'' +
                '}';
    }
}
