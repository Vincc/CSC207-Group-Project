package use_case.CreateEvent;

import junit.framework.TestCase;

public class CreateEventInputDataTest extends TestCase {

    public void testGetUsername() {
        String username = "user1";
        CreateEventInputData createEventInputData = new CreateEventInputData(username);
        assertEquals(username,createEventInputData.getUsername());

    }
}