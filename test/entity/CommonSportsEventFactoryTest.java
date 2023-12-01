package entity;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class CommonSportsEventFactoryTest {

    private CommonSportsEventFactory sportsEventFactory;

    @Before
    public void setUp() {
        sportsEventFactory = new CommonSportsEventFactory();
    }

    @Test
    public void testEventFactory() {
        String name = "Basketball";
        LocalDate date = LocalDate.of(2023, 11, 29);
        LocalDate endDate = LocalDate.of(2023, 11, 30);
        LocalTime time = LocalTime.of(14, 0);
        LocalTime endTime = LocalTime.of(18, 0);
        String organizer = "organizer1";
        int maxAttendance = 100;
        String levelOfPlay = "pro";
        String location = "Vancouver";

        SportsEvent sportsEvent = sportsEventFactory.create(name, date, endDate, time, endTime, organizer, maxAttendance, levelOfPlay, location);

        assertEquals(name, sportsEvent.getName());
        assertEquals(date, sportsEvent.getDate());
        assertEquals(endDate, sportsEvent.getEventEndDate());
        assertEquals(time, sportsEvent.getTime());
        assertEquals(endTime, sportsEvent.getEventEndTime());
        assertEquals(organizer, sportsEvent.getOrganizer());
        assertEquals(maxAttendance, sportsEvent.getMaxAttendance());
        assertEquals(levelOfPlay, sportsEvent.lvlofPlay());
        assertEquals(location, sportsEvent.getLocation());
    }
}
