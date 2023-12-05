package interface_adapter.createEvent;

import org.junit.Before;
import org.junit.Test;
import use_case.makeEvent.makeEventInputBoundary;
import use_case.makeEvent.makeEventInputData;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;


public class CreateEventControllerTest {

    class MakeEventInputBoundaryStub implements makeEventInputBoundary {
        boolean isCalled = false;
        makeEventInputData receivedData;

        @Override
        public void execute(makeEventInputData data) {
            this.isCalled = true;
            this.receivedData = data;
        }
    }

    @Test
    public void testExecuteMakeEvent() {
        // Arrange
        MakeEventInputBoundaryStub stub = new MakeEventInputBoundaryStub();
        CreateEventController controller = new CreateEventController(stub);

        String organiserName = "Organiser";
        String eventName = "Event";
        String location = "Location";
        LocalDate eventDate = LocalDate.now();
        LocalDate eventEndDate = LocalDate.now().plusDays(1);
        LocalTime eventTime = LocalTime.now();
        LocalTime eventEndTime = LocalTime.now().plusHours(2);
        String eventLabel = "Label";
        String eventLevel = "Level";
        int eventMaxAttendance = 100;

        // Act
        controller.executeMakeEvent(organiserName, eventName, location, eventDate, eventEndDate, eventTime, eventEndTime, eventLabel, eventLevel, eventMaxAttendance);

        // Assert

        assertTrue(stub.isCalled);
        assertNotNull(stub.receivedData);
        assertEquals(eventName, stub.receivedData.getEventName());
        assertEquals(organiserName, stub.receivedData.getOrganiserName());
        assertEquals(location, stub.receivedData.getLocation());
        assertEquals(eventDate, stub.receivedData.getEventDate());
        assertEquals(eventEndDate, stub.receivedData.getEventEndDate());
        assertEquals(eventTime, stub.receivedData.getEventTime());
        assertEquals(eventEndTime, stub.receivedData.getEventEndTime());
        assertEquals(eventLevel, stub.receivedData.getEventLevel());
        assertEquals(eventMaxAttendance, stub.receivedData.getEventMaxAttendance());

    }
}