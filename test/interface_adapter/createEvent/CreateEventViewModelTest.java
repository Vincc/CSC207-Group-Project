package interface_adapter.createEvent;

import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.*;

public class CreateEventViewModelTest {
    private CreateEventViewModel viewModel;
    private boolean eventFired;

    @Before
    public void setUp() {
        viewModel = new CreateEventViewModel();
        eventFired = false;
    }

    @Test
    public void testSetState() {
        CreateEventState newState = new CreateEventState();
        viewModel.setState(newState);
        assertEquals(newState, viewModel.getState());
    }

    @Test
    public void testFirePropertyChanged() {
        viewModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("state".equals(evt.getPropertyName())) {
                    eventFired = true;
                }
            }
        });

        viewModel.firePropertyChanged();
        assertTrue(eventFired);
    }


}