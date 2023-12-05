package interface_adapter.cancel;

import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.*;

public class CancelViewModelTest {

    private CancelViewModel cancelViewModel;
    private boolean propertyChanged;

    @Before
    public void setUp() {
        cancelViewModel = new CancelViewModel();
        propertyChanged = false;

        PropertyChangeListener testListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                propertyChanged = true;
            }
        };

        cancelViewModel.addPropertyChangeListener(testListener);
    }

    @Test
    public void testStateChangeFiresPropertyChangeEvent() {
        CancelState newState = new CancelState(); // Assuming CancelState is a testable object
        cancelViewModel.setState(newState);
        cancelViewModel.firePropertyChanged();

        assertTrue("PropertyChangeEvent should be fired when state changes", propertyChanged);
    }

    @Test
    public void testGetPropertyState() {
        CancelState expectedState = new CancelState();
        cancelViewModel.setState(expectedState);

        CancelState actualState = cancelViewModel.getState();

        assertEquals("Getter for state should return the current state", expectedState, actualState);
    }

}