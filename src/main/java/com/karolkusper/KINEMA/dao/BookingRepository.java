package com.karolkusper.KINEMA.dao;

import com.karolkusper.KINEMA.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
