package src.use_case.CreateEvent;

import entity.SportsEvent;

public interface CreateEventDataAccessInterface {

    boolean eventExists(String name);

    void save(SportsEvent sportsEvent);

}
