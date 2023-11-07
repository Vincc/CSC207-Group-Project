package entity;

import java.time.LocalDateTime;

public interface SportsEvent {
    String getName();
    LocalDateTime getDate();

    String getLocation();
    String getOrganizer();
    String lvlofPlay();
    int getMaxAttendance();

    String getEventDescription();

    void setEventDescription(String description);
}
