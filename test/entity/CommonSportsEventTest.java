package entity;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CommonSportsEventTest {

    private CommonSportsEvent commonSportsEvent;

    @Before
    public void setUp() {
        // Initialize a CommonSportsEvent instance before each test
         commonSportsEvent = new CommonSportsEvent(
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
    }

    @Test
    public void lvlofPlay() {
        assertEquals("pro", commonSportsEvent.lvlofPlay());
    }

    @Test
    public void getName() {
        assertEquals("Event 1 test", commonSportsEvent.getName());
    }

    @Test
    public void getDate() {
        assertEquals(LocalDate.of(2023, 12, 1), commonSportsEvent.getDate());
    }

    @Test
    public void getTime() {
        assertEquals(LocalTime.of(15, 30), commonSportsEvent.getTime());
    }

    @Test
    public void getLocation() {
        assertEquals("Toronto dt", commonSportsEvent.getLocation());
    }

    @Test
    public void getOrganizer() {
        assertEquals("Organizer 1 test", commonSportsEvent.getOrganizer());
    }

    @Test
    public void getMaxAttendance() {
        assertEquals(10, commonSportsEvent.getMaxAttendance());
    }

    @Test
    public void getEventDescription() {
        assertEquals("", commonSportsEvent.getEventDescription());
    }

    @Test
    public void setEventDescription() {
        commonSportsEvent.setEventDescription("event1 description is here");
        assertEquals("event1 description is here", commonSportsEvent.getEventDescription());
    }

    @Test
    public void getAttendance() {
        assertEquals(new ArrayList<>(), commonSportsEvent.getAttendance());
    }

    @Test
    public void addAttendance() {
        commonSportsEvent.addAttendance("user1");
        commonSportsEvent.addAttendance("user2");
        commonSportsEvent.addAttendance("user3");
        ArrayList<String> expectedAttendance = new ArrayList<>();
        expectedAttendance.add("user1");
        expectedAttendance.add("user2");
        expectedAttendance.add("user3");

        assertArrayEquals(expectedAttendance.toArray(), commonSportsEvent.getAttendance().toArray());
    }

    @Test
    public void getEventEndDate() {
        assertEquals(LocalDate.of(2023, 12, 2), commonSportsEvent.getEventEndDate());
    }

    @Test
    public void getEventEndTime() {
        assertEquals(LocalTime.of(17, 30), commonSportsEvent.getEventEndTime());
    }
}