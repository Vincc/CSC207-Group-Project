package use_case.createEvent;

import entity.User;

import java.time.LocalDateTime;

public class CreateEventInputData {

    final private LocalDateTime date;
    final private String location; //// change later

    final private String name;

    final private User organizer;

    final int maxAttendencel;

    final private String sportType;
    final private String lvlOfPlay;

    public CreateEventInputData(LocalDateTime date, String location , String name, User organizer, int maxAttendencel, String sportType, String lvlOfPlay) {
        this.date = date;
        this.location = location;
        this.name = name;
        this.organizer = organizer;
        this.maxAttendencel = maxAttendencel;
        this.sportType = sportType;
        this.lvlOfPlay = lvlOfPlay;
    }

    public int getMaxAttendencel() {
        return maxAttendencel;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getLvlOfPlay() {
        return lvlOfPlay;
    }

    public String getName() {
        return name;
    }

    public String getSportType() {
        return sportType;
    }

    public User getOrganizer() {
        return organizer;
    }
}
