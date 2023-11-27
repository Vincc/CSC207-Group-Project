package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CommonSportsEventFactory implements SportsEventFactory{


    @Override
    public SportsEvent create(String name, LocalDate date, LocalDate endDate, LocalTime time, LocalTime endTime, String sportType, int maxAttendance, String lvlofPlay, String location) {
        return new CommonSportsEvent(sportType,name, date, endDate, time, endTime, location, maxAttendance, lvlofPlay);
    }
}