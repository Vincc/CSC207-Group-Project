package interface_adapter.createProfile;

import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.*;

public class CreateProfileViewModelTest {
    private CreateProfileViewModel viewModel;
    private TestPropertyChangeListener listener;

    private class TestPropertyChangeListener implements PropertyChangeListener {
        private boolean notified = false;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            notified = true;
        }

        public boolean isNotified() {
            return notified;
        }

        public void reset() {
            notified = false;
        }
    }

    @Before
    public void setUp() {
        viewModel = new CreateProfileViewModel();
        listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);
    }

    @Test
    public void testStateChange() {
        CreateProfileState newState = new CreateProfileState();
        viewModel.setState(newState);

        assertSame("State should be the same as set by setState", newState, viewModel.getState());
    }

    @Test
    public void testPropertyChangeNotification() {
        listener.reset();
        viewModel.firePropertyChanged();

        assertTrue("Listener should be notified of property change", listener.isNotified());
    }

}