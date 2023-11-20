package use_case.CreateEvent;

import entity.SportsEvent;
import entity.User;

public interface CreateEventDataAccessInterface {

    boolean eventExists(String name);

    void save(SportsEvent sportsEvent);

}
