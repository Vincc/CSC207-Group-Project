package entity;

import java.time.LocalDateTime;

public interface SportsEventFactory {

    SportsEvent create(String name , LocalDateTime date , String organizer,
                       int maxAttendance, String lvlofPlay, String location);
}
