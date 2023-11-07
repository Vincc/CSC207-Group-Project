package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class CommonUser implements User {

    private final String name;
    private final String password;
    private final LocalDateTime creationTime;

    private List<String> events;

    private double rating;

    private String userDescription;

    private String location;


    /**
     * Requires: password is valid.
     * @param name
     * @param password
     */
    CommonUser(String name, String password, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.events = new ArrayList<>();
        this.userDescription = "";
        this.location = "";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void addEvent(String event){this.events.add(event);}

    public String getUserDescription(){return this.userDescription;}

    public List<String>  getJoinedEvents(){return this.events;}


    public String getLocation(){return this.location;}

    public void setLocation(String location){this.location=location;}

    public void setUserDescription(String description){this.userDescription = description;}
}
