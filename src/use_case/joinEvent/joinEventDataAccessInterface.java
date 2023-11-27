package use_case.joinEvent;

import entity.SportsEvent;

public interface joinEventDataAccessInterface {
    void save(SportsEvent sportsEvent);
    boolean existsByName(String identifier);

}
