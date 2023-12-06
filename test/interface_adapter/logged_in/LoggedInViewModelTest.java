package interface_adapter.logged_in;

import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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


    @Test
    public void testAddPropertyChangeListenerAndFirePropertyChanged() {
        // Create a test listener
        TestPropertyChangeListener testListener = new TestPropertyChangeListener();

        // Add the listener to the view model
        viewModel.addPropertyChangeListener(testListener);

        // Change the state and fire property change
        LoggedInState newState = new LoggedInState(); // Or use an appropriate state object
        viewModel.setState(newState);
        viewModel.firePropertyChanged();

        // Check if the event was fired
        assertTrue(testListener.isEventFired());
    }


    private class TestPropertyChangeListener implements PropertyChangeListener {
        private boolean eventFired = false;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if ("state".equals(evt.getPropertyName())) {
                eventFired = true;
            }
        }

        public boolean isEventFired() {
            return eventFired;
        }
    }
    }


