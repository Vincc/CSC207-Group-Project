package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface SportsEvent {
    String getName();
    LocalDateTime getDate();

    String getLocation();
    String getOrganizer();
    String lvlofPlay();
    int getMaxAttendance();

    String getEventDescription();

    void setEventDescription(String description);

    ArrayList<String> getAttendance();

    void addAttendance(String user);
}
