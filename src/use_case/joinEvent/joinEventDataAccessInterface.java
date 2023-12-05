package use_case.joinEvent;

import entity.SportsEvent;

public interface joinEventDataAccessInterface {
    void addParticipant(String eventname, String username);
}
