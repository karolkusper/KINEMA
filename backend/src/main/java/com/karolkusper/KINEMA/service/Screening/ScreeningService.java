package com.karolkusper.KINEMA.service.Screening;

import com.karolkusper.KINEMA.entity.Screening;
import java.util.List;

public interface ScreeningService {
    List<Screening> findAll();
    Screening findById(int id);
    Screening save(Screening movie);
}
