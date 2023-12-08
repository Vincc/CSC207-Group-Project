package use_case.addToCalendar;

import data_access.GoogleCalendarApi;
import entity.SportsEvent;
import use_case.joinEvent.joinEventInputData;

import java.io.IOException;
import java.security.GeneralSecurityException;


public class addToCalendarInteractor implements addToCalendarInputBoundary{
    final addToCalendarDataAccessInterface addToCalendarDataAccessInterface;

    public addToCalendarInteractor(addToCalendarDataAccessInterface addToCalendarDataAccessInterface) {
        this.addToCalendarDataAccessInterface = addToCalendarDataAccessInterface;
    }

    @Override
    public void execute(addToCalendarInputData addToCalendarInputData) throws GeneralSecurityException, IOException {
        GoogleCalendarApi api = new GoogleCalendarApi();
        String eventname = addToCalendarInputData.getEventName();
        if (addToCalendarDataAccessInterface.existsByName(eventname)){
            SportsEvent event = addToCalendarDataAccessInterface.getSportEvent(eventname);
            api.addToGoogleCalendar(event);

        }

    }
}
