package interface_adapter.createEvent;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;


    public class CreateEventStateTest {
        @Test
        public void testCopyConstructor() {
            CreateEventState originalState = new CreateEventState();
            originalState.setUsername("JohnDoe");
            originalState.setPlace("Central Park");
            originalState.setDate(LocalDate.of(2023, 12, 1));
            originalState.setEndDate(LocalDate.of(2023, 12, 2));
            originalState.setTime(LocalTime.of(14, 30));
            originalState.setEndTime(LocalTime.of(16, 0));
            originalState.setLvl("Intermediate");
            originalState.setMaxplayers(10);
            originalState.setSporttype("Football");
            originalState.setDiscription("Friendly match");

            CreateEventState copiedState = new CreateEventState(originalState);

            // Assert that the copied state is equal to the original state
            assertEquals(originalState.getUsername(), copiedState.getUsername());
            assertEquals(originalState.getPlace(), copiedState.getPlace());
            assertEquals(originalState.getDate(), copiedState.getDate());
            assertEquals(originalState.getEndDate(), copiedState.getEndDate());
            assertEquals(originalState.getTime(), copiedState.getTime());
            assertEquals(originalState.getEndTime(), copiedState.getEndTime());
            assertEquals(originalState.getLvl(), copiedState.getLvl());
            assertEquals(originalState.getMaxplayers(), copiedState.getMaxplayers());
            assertEquals(originalState.getSporttype(), copiedState.getSporttype());
            assertEquals(originalState.getDiscription(), copiedState.getDiscription());
        }
    }