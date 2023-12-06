package interface_adapter.createEvent;

import org.junit.Before;
import org.junit.Test;
import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInputData;
import use_case.makeEvent.makeEventInputBoundary;
import use_case.makeEvent.makeEventInputData;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;


public class CreateEventControllerTest {

    static class MakeEventInputBoundaryStub implements makeEventInputBoundary {
        boolean isCalled = false;
        makeEventInputData receivedData;

        @Override
        public void execute(makeEventInputData data) {
            this.isCalled = true;
            this.receivedData = data;
        }
    }

    class CancelInputBoundaryStub implements CancelInputBoundary {
        boolean isCalled = false;

        CancelInputData cancelInputData;

        public void execute(CancelInputData cancelInputData){
            this.isCalled = true;
            this.cancelInputData = cancelInputData;
        }
    }
    @Test
    public void testExecuteMakeEvent() {
        // Arrange
        MakeEventInputBoundaryStub makeEventStub = new MakeEventInputBoundaryStub();
        CancelInputBoundaryStub cancelInputBoundaryStub = new CancelInputBoundaryStub();
        CreateEventController controller = new CreateEventController(makeEventStub, cancelInputBoundaryStub);

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

        assertTrue(makeEventStub.isCalled);
        assertNotNull(makeEventStub.receivedData);
        assertEquals(eventName, makeEventStub.receivedData.getEventName());
        assertEquals(organiserName, makeEventStub.receivedData.getOrganiserName());
        assertEquals(location, makeEventStub.receivedData.getLocation());
        assertEquals(eventDate, makeEventStub.receivedData.getEventDate());
        assertEquals(eventEndDate, makeEventStub.receivedData.getEventEndDate());
        assertEquals(eventTime, makeEventStub.receivedData.getEventTime());
        assertEquals(eventEndTime, makeEventStub.receivedData.getEventEndTime());
        assertEquals(eventLevel, makeEventStub.receivedData.getEventLevel());
        assertEquals(eventMaxAttendance, makeEventStub.receivedData.getEventMaxAttendance());
    }

    @Test
    public void testExecuteCancel(){
        MakeEventInputBoundaryStub makeEventStub = new MakeEventInputBoundaryStub();
        CancelInputBoundaryStub cancelInputBoundaryStub = new CancelInputBoundaryStub();
        CreateEventController controller = new CreateEventController(makeEventStub, cancelInputBoundaryStub);

        controller.executeCancel("u1");
        assertTrue(cancelInputBoundaryStub.isCalled);
        assertEquals("u1",cancelInputBoundaryStub.cancelInputData.getUsername());

    }

    }