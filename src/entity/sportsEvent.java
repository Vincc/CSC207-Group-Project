package entity;

import java.time.LocalDateTime;

public interface sportsEvent {
    String getName();
    LocalDateTime getDate();

    String getLocation();
    User getOrganizer();
    String lvlofPlay();
    int getMaxAttendance();
}
