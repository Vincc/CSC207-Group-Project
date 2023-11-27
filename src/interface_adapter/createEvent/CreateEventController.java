package interface_adapter.createEvent;
import use_case.makeEvent.makeEventInputBoundary;
import use_case.makeEvent.makeEventInputData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CreateEventController {
    final makeEventInputBoundary makeEventucInteractor;


    public CreateEventController(makeEventInputBoundary makeEventucInteractor){
        this.makeEventucInteractor = makeEventucInteractor;

    }
    public void executeMakeEvent(String eventName, String location, LocalDate eventDate, LocalTime eventTime, String eventLabel, String eventLevel, int eventMaxAttendance){
        makeEventInputData MakeEventInputData = new makeEventInputData( eventName,  location,  eventDate, eventTime,  eventLabel,  eventLevel,  eventMaxAttendance);
        makeEventucInteractor.execute(MakeEventInputData);

    }
}
