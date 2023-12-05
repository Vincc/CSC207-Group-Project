package interface_adapter.logged_in;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoggedInViewModelTest {
    private LoggedInViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new LoggedInViewModel();
    }

    @Test
    public void testInitialState() {
        assertNotNull("ViewModel should not be null", viewModel);
        assertNull("Initially, loggedInUser should be null", viewModel.getLoggedInUser());
    }

    @Test
    public void testSetLoggedInUser() {
        String testUser = "testUser";
        viewModel.setLoggedInUser(testUser);
        assertEquals("loggedInUser should be set correctly", testUser, viewModel.getLoggedInUser());
    }

    @Test
    public void testSetTitle() {
        String expectedTitle = "Logged In View";
        assertEquals("Title should be 'Logged In View'", expectedTitle, viewModel.TITLE_LABEL);
    }

    @Test
    public void testSetState() {
        LoggedInState state = new LoggedInState();
        viewModel.setState(state);
        assertEquals("State should be set correctly", state, viewModel.getState());
    }



}