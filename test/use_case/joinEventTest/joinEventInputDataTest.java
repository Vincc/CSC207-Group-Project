package use_case.joinEventTest;

import use_case.joinEvent.joinEventInputData;

import static org.junit.Assert.assertEquals;

public class joinEventInputDataTest {
    public void testJoinEventInputData() {
        String username = "Robert";
        String eventName = "Friday";
        joinEventInputData join = new joinEventInputData(username, eventName);
        assertEquals(username, join.getUsername());
        assertEquals(username, join.getEventName());
    }
}
