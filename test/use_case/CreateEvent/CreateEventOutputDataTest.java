package use_case.CreateEvent;

import junit.framework.TestCase;

public class CreateEventOutputDataTest extends TestCase {

    public void testGetUsername() {
        String username = "user1";
        CreateEventOutputData createEventOutputData = new CreateEventOutputData(username);
        assertEquals(username,createEventOutputData.getUsername());
    }
}