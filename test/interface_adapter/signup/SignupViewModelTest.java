package interface_adapter.signup;

import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.*;

public class SignupViewModelTest {
    private SignupViewModel viewModel;
    private boolean propertyChangeFired;

    @Before
    public void setUp() {
        viewModel = new SignupViewModel();
        propertyChangeFired = false;

        // Adding a test listener to detect property changes
        viewModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("state".equals(evt.getPropertyName())) {
                    propertyChangeFired = true;
                }
            }
        });
    }

    @Test
    public void testSetAndGetState() {
        SignupState testState = new SignupState();
        testState.setUsername("testUser");

        viewModel.setState(testState);

        assertEquals("testUser", viewModel.getState().getUsername());
    }

    @Test
    public void testFirePropertyChanged() {
        viewModel.firePropertyChanged();

        assertTrue(propertyChangeFired);
    }

}