package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface SportsEventFactory {

    SportsEvent create(String name, LocalDate date, LocalTime time , String organizer,
                       int maxAttendance, String lvlofPlay, String location);
}
