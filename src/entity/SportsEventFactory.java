package entity;

import java.time.LocalDateTime;

public class CommonSportsEventFactory implements SportsEventFactory{


    @Override
    public SportsEvent create(String name, LocalDateTime date, String organizer, int maxAttendance, String lvlofPlay, String location) {
        return new CommonSportsEvent(organizer,name, date, location, maxAttendance, lvlofPlay);
    }
}
