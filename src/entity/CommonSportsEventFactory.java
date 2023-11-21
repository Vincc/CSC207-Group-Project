package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CommonSportsEventFactory implements SportsEventFactory{


    @Override
    public SportsEvent create(String name, LocalDate date, LocalTime time, String sportType, int maxAttendance, String lvlofPlay, String location, LocalDate eventEndDate, LocalTime eventEndTime) {
        return new CommonSportsEvent(sportType,name, date, time, location, maxAttendance, lvlofPlay, eventEndDate,eventEndTime);
    }
}