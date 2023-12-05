package interface_adapter.createEvent;
import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInputData;
import use_case.makeEvent.makeEventInputBoundary;
import use_case.makeEvent.makeEventInputData;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateEventController {
    final makeEventInputBoundary makeEventucInteractor;
    final CancelInputBoundary cancelInteractor;

    public CreateEventController(makeEventInputBoundary makeEventucInteractor, CancelInputBoundary cancelInteractor){
        this.makeEventucInteractor = makeEventucInteractor;

        this.cancelInteractor = cancelInteractor;
    }
    public void executeMakeEvent(String organiserName, String eventName, String location, LocalDate eventDate, LocalDate eventEndDate, LocalTime eventTime, LocalTime eventEndTime, String eventLabel, String eventLevel, int eventMaxAttendance){
        makeEventInputData MakeEventInputData = new makeEventInputData(organiserName, eventName,  location,  eventDate, eventEndDate, eventTime, eventEndTime, eventLabel,  eventLevel,  eventMaxAttendance);
        makeEventucInteractor.execute(MakeEventInputData);

    }
    public void executeCancel(String username){
        CancelInputData cancelInputData = new CancelInputData(username);
        cancelInteractor.execute(cancelInputData);
    }
}
