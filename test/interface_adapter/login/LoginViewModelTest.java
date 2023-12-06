package interface_adapter.login;

import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.*;

public class LoginViewModelTest {
    private LoginViewModel viewModel;


    private LoginState testState;

    @Before
    public void setUp() {
        viewModel = new LoginViewModel();
        testState = new LoginState();
    }

    @Test
    public void testInitialState() {
        assertNotNull(viewModel.getState());
    }

    @Test
    public void testSetState() {
        viewModel.setState(testState);
        assertEquals(testState, viewModel.getState());
    }

    @Test
    public void testPropertyChangeSupport() {
        PropertyChangeListener listener = event -> {
            assertEquals("state", event.getPropertyName(), "Property name should be 'state'");
            assertNull(event.getOldValue());
            assertEquals(testState, event.getNewValue());
        };
        viewModel.addPropertyChangeListener(listener);
        viewModel.setState(testState);
    }
}