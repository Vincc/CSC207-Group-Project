package use_case.makeEvent;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class makeEventInputDataTest {

    @Test
    public void testMakeEventInputData() {
        String organiserName = "TestOrganizer";
        String eventName = "TestEvent";
        String location = "TestLocation";
        LocalDate eventDate = LocalDate.of(2004, 12, 2);
        LocalDate eventEndDate = LocalDate.of(2004, 12, 2);
        LocalTime eventTime = LocalTime.of(12, 0);
        LocalTime eventEndTime = LocalTime.of(13, 0);
        String eventLabel = "Sports";
        String eventLevel = "Intermediate";
        int eventMaxAttendance = 100;

        makeEventInputData inputData = new makeEventInputData(organiserName, eventName, location, eventDate, eventEndDate, eventTime, eventEndTime, eventLabel, eventLevel, eventMaxAttendance);

        assertEquals(organiserName, inputData.getOrganiserName());
        assertEquals(eventName, inputData.getEventName());
        assertEquals(location, inputData.getLocation());
        assertEquals(eventDate, inputData.getEventDate());
        assertEquals(eventEndDate, inputData.getEventEndDate());
        assertEquals(eventTime, inputData.getEventTime());
        assertEquals(eventEndTime, inputData.getEventEndTime());
        assertEquals(eventLabel, inputData.getEventLabel());
        assertEquals(eventLevel, inputData.getEventLevel());
        assertEquals(eventMaxAttendance, inputData.getEventMaxAttendance());
    }
}