package src.entity;

import entity.CommonSportsEvent;
import entity.SportsEvent;
import entity.SportsEventFactory;

import java.time.LocalDate;
import java.time.LocalTime;

public class CommonSportsEventFactory implements SportsEventFactory {


    @Override
    public SportsEvent create(String name, LocalDate date, LocalTime time, String sportType, int maxAttendance, String lvlofPlay, String location) {
        return new CommonSportsEvent(sportType,name, date, time, location, maxAttendance, lvlofPlay);
    }
}