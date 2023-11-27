package use_case.makeEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class makeEventInputData {
    private final String eventName;
    private final String location;
    private final LocalDate eventDate;
    private final LocalTime eventTime;
    private final String eventLabel;
    private final String eventLevel;
    private final int eventMaxAttendance;

    public makeEventInputData(String eventName, String location, LocalDate eventDate, LocalTime eventTime, String eventLabel, String eventLevel, int eventMaxAttendance) {
        this.eventName = eventName;
        this.location = location;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLabel = eventLabel;
        this.eventLevel = eventLevel;
        this.eventMaxAttendance = eventMaxAttendance;
    }
    String getEventName() {
        return eventName;
    }
    String getLocation() {return location;}
    LocalDate getEventDate() {return eventDate;}
    LocalTime getEventTime() {
        return eventTime;
    }
    String getEventLabel() {
        return eventLabel;
    }
    String getEventLevel() {
        return eventLevel;
    }
    int getEventMaxAttendance() {
        return eventMaxAttendance;
    }
}
