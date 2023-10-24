package entity;

import java.time.LocalDateTime;

public interface SportsEventFactory {

    sportsEvent create(String name , LocalDateTime date , User organizer,
                       int maxAttendance, String lvlofPlay, String location);
}
