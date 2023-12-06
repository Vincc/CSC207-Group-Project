package use_case.joinEventTest;

import org.junit.Test;
import use_case.joinEvent.joinEventInputData;
import use_case.joinEvent.joinEventOutputData;

import static org.junit.Assert.assertEquals;

public class joinEventOutputDataTest {
    @Test
    public void testjoinEventOutputData() {
        String username = "Robert";
        joinEventOutputData join = new joinEventOutputData(username);
        assertEquals(username, join.getUsername());
    }
}
