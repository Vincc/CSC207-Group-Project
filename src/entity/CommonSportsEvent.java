package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CommonSportsEvent implements SportsEvent {

    private String organizer;
    private String eventName;

    private LocalDateTime eventDateTime;

    private String eventLocation;

    private int maxAttendance;

    private String lvlOfPlay;

    private String eventDescription;

    private ArrayList<User> attendance;

    public CommonSportsEvent(String organizer, String eventName, LocalDateTime eventDateTime, String eventLocation, int maxAttendance, String lvlOfPlay) {
        this.organizer = organizer;
        this.eventName = eventName;
        this.eventDateTime = eventDateTime;
        this.eventLocation = eventLocation;
        this.maxAttendance = maxAttendance;
        this.lvlOfPlay = lvlOfPlay;
        this.attendance = new ArrayList<>();
    }


    public String lvlofPlay() {
        return lvlOfPlay;
    }

    @Override
    public String getName() {
        return eventName;
    }

    @Override
    public LocalDateTime getDate() {
        return eventDateTime;
    }

    @Override
    public String getLocation() {
        return eventLocation;
    }

    @Override
    public String getOrganizer() {
        return organizer;
    }

    @Override
    public int getMaxAttendance() {
        return maxAttendance;
    }

    public String getEventDescription(){return eventDescription; }

    public void setEventDescription(String description){
        this.eventDescription = description;
    }

    public ArrayList<User> getAttendance(){return this.attendance;}

    public void addAttendance(User user){this.attendance.add(user);}
}
