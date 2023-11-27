package entity;

import javax.ejb.Local;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface SportsEventFactory {

    SportsEvent create(String name, LocalDate date, LocalDate endDate, LocalTime time, LocalTime endTime, String organizer,
                       int maxAttendance, String lvlofPlay, String location);
}
