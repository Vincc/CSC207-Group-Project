package use_case.makeEvent;

import entity.SportsEvent;

public interface makeEventDataAccessInterface {
    boolean existsByName(String identifier);

    void save(SportsEvent sportsEvent);
}
