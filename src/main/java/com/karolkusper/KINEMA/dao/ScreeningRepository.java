package com.karolkusper.KINEMA.dao;

import com.karolkusper.KINEMA.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreeningRepository extends JpaRepository<Screening,Integer> {
}
