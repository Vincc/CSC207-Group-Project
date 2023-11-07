package use_case.createEvent;

import entity.SportsEvent;
import entity.User;

public interface CreateEventDataAccessInterface {

    boolean eventExists(String name);

    void save(SportsEvent sportsEvent);

}
