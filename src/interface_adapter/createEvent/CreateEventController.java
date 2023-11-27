package interface_adapter.createEvent;
import use_case.makeEvent.makeEventInputBoundary;
import use_case.makeEvent.makeEventInputData;

import javax.ejb.Local;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CreateEventController {
    final makeEventInputBoundary makeEventucInteractor;


    public CreateEventController(makeEventInputBoundary makeEventucInteractor){
        this.makeEventucInteractor = makeEventucInteractor;

    }
    public void executeMakeEvent(String organiserName, String eventName, String location, LocalDate eventDate, LocalDate eventEndDate, LocalTime eventTime, LocalTime eventEndTime, String eventLabel, String eventLevel, int eventMaxAttendance){
        makeEventInputData MakeEventInputData = new makeEventInputData(organiserName, eventName,  location,  eventDate, eventEndDate, eventTime, eventEndTime, eventLabel,  eventLevel,  eventMaxAttendance);
        makeEventucInteractor.execute(MakeEventInputData);

    }
}
