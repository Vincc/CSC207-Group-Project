package data_access;

import entity.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FileEventDataAccessObjectTest {

    private FileEventDataAccessObject eventDAO;

    private FileEventDataAccessObject eventDAO2;
    @Before
    public void setUp() throws IOException {

        String jsonPathEventTest = "test_events.json";
        UserFactory userFactory = new CommonUserFactory();
        SportsEventFactory sportsEventFactory = new CommonSportsEventFactory();

        eventDAO = new FileEventDataAccessObject(jsonPathEventTest, userFactory, sportsEventFactory);
        eventDAO2 = new FileEventDataAccessObject("test_events_retrieve.json", userFactory, sportsEventFactory);
    }

    @Test
    public void getSportEvent() {

        // Retrieve the event
        SportsEvent retrievedEvent = eventDAO2.getSportEvent("Event 1 test");

        assertEquals("Event 1 test", retrievedEvent.getName());
        assertEquals( LocalDate.of(2023, 12, 1), retrievedEvent.getDate());
        assertEquals(LocalDate.of(2023, 12, 2), retrievedEvent.getEventEndDate());
        assertEquals(LocalTime.of(15, 30), retrievedEvent.getTime());
        assertEquals(LocalTime.of(17, 30), retrievedEvent.getEventEndTime());
        assertEquals("Organizer 1 test", retrievedEvent.getOrganizer());
        assertEquals(10, retrievedEvent.getMaxAttendance());
        assertEquals("pro", retrievedEvent.lvlofPlay());
        assertEquals("Toronto dt", retrievedEvent.getLocation());
    }

    @Test
    public void existsByName() {
        assertTrue(eventDAO2.existsByName("Event 1 test"));
        assertFalse(eventDAO2.existsByName("event 2"));
    }

    @Test
    public void addParticipant() {

        eventDAO.addParticipant("Event 1 test", "attendance1");
        eventDAO.addParticipant("Event 1 test", "attendance2");

        SportsEvent retrievedEvent = eventDAO.getSportEvent("Event 1 test");
        assertEquals(Arrays.asList("attendance1", "attendance2"), retrievedEvent.getAttendance());

    }

    @Test
    public void testSaveEvent() {
        SportsEvent commonSportsEventTest = new CommonSportsEvent(
                "Organizer 1 test",
                "Event 1 test",
                LocalDate.of(2023, 12, 1), // Start date
                LocalDate.of(2023, 12, 2), // End date
                LocalTime.of(15, 30),      // Start time
                LocalTime.of(17, 30),      // End time
                "Toronto dt",
                10,
                "pro"
        );

        // Save the event
        eventDAO.save(commonSportsEventTest);

        // Retrieve the event
        SportsEvent retrievedEvent = eventDAO.getSportEvent("Event 1 test");


        assertEquals("Event 1 test", retrievedEvent.getName());
        assertEquals( LocalDate.of(2023, 12, 1), retrievedEvent.getDate());
        assertEquals(LocalDate.of(2023, 12, 2), retrievedEvent.getEventEndDate());
        assertEquals(LocalTime.of(15, 30), retrievedEvent.getTime());
        assertEquals(LocalTime.of(17, 30), retrievedEvent.getEventEndTime());
        assertEquals("Organizer 1 test", retrievedEvent.getOrganizer());
        assertEquals(10, retrievedEvent.getMaxAttendance());
        assertEquals("pro", retrievedEvent.lvlofPlay());
        assertEquals("Toronto dt", retrievedEvent.getLocation());

    }
}