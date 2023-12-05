package use_case.joinEvent;

import entity.SportsEvent;

public interface joinEventUserDataAccessInterface {
    void addEvent(String eventname, String username);
}
