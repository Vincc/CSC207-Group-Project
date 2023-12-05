package use_case.makeEvent;

import data_access.FileEventDataAccessObject;
import entity.*;
import junit.framework.TestCase;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class makeEventInteractorTest extends TestCase {

    String organiserName = "TestOrganizer";
    String eventName = "TestEvent";
    String eventLocation = "TestLocation";
    LocalDate eventDate = LocalDate.of(2004, 12, 2);
    LocalDate eventEndDate = LocalDate.of(2004, 12, 2);
    LocalTime eventTime = LocalTime.of(12, 0);
    LocalTime eventEndTime = LocalTime.of(13, 0);
    String eventLabel = "Sports";
    String eventLevel = "Intermediate";
    int eventMaxAttendance = 100;

    makeEventInputData inputData = new makeEventInputData(organiserName, eventName, eventLocation, eventDate, eventEndDate, eventTime, eventEndTime, eventLabel, eventLevel, eventMaxAttendance);

    makeEventOutputBoundary presenter = new makeEventOutputBoundary() {
        @Override
        public void prepareMakeEventSuccessView() {
        }

        @Override
        public void prepareMakeEventFailView(String error) {

        }
    };

    SportsEventFactory sportsEventFactory = new SportsEventFactory() {
        @Override
        public SportsEvent create(String name, LocalDate date, LocalDate endDate, LocalTime time, LocalTime endTime, String organizer, int maxAttendance, String lvlofPlay, String location) {
            assertEquals(organizer,organiserName);
            assertEquals(name,eventName);
            assertEquals(date,eventDate);
            assertEquals(endDate,eventEndDate);
            assertEquals(time,eventTime);
            assertEquals(endTime,eventEndTime);
            assertEquals(maxAttendance,eventMaxAttendance);
            assertEquals(lvlofPlay,eventLevel);
            assertEquals(location,eventLocation);
            return new CommonSportsEvent(organizer,name,date,endDate,time,endTime,location,maxAttendance,lvlofPlay);

        };
    };


    public void testExecute() {
        FileEventDataAccessObject eventDataAccessObject;
        try {
            eventDataAccessObject = new FileEventDataAccessObject("./test_events.json", new CommonUserFactory(), new CommonSportsEventFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        makeEventInputBoundary interactor = new makeEventInteractor(eventDataAccessObject,presenter,sportsEventFactory);
        interactor.execute(inputData);

    }
}