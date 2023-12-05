package interface_adapter.logged_in;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoggedInStateTest {

    @Test
    public void testDefaultConstructor() {
        LoggedInState state = new LoggedInState();
        assertEquals("", state.getUsername());
    }

    @Test
    public void testCopyConstructor() {
        LoggedInState original = new LoggedInState();
        original.setUsername("user123");
        LoggedInState copy = new LoggedInState(original);
        assertEquals("user123", copy.getUsername());
    }

    @Test
    public void testGetUsername() {
        LoggedInState state = new LoggedInState();
        state.setUsername("testUser");
        assertEquals("testUser", state.getUsername());
    }

    @Test
    public void testSetUsername() {
        LoggedInState state = new LoggedInState();
        state.setUsername("newUser");
        assertEquals("newUser", state.getUsername());
    }


}