package use_case.addToCalendar;

import entity.SportsEvent;

public interface addToCalendarDataAccessInterface {
    public boolean existsByName(String name);
    public SportsEvent getSportEvent(String eventName);

}
