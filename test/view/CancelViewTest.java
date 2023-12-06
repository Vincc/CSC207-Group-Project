package view;

import interface_adapter.cancel.CancelViewModel;
import interface_adapter.createEvent.CreateEventState;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CancelViewTest {

    private CancelView cancelView;
    private CancelViewModelStub cancelViewModelStub;

    private class CancelViewModelStub extends CancelViewModel {
        // Implement necessary methods or provide stub behavior
        @Override
        public void firePropertyChanged() {
            // Stub implementation or leave empty if not relevant for testing
        }
    }

    @Before
    public void setUp() {
        cancelViewModelStub = new CancelViewModelStub();
        cancelView = new CancelView(cancelViewModelStub);
    }

    @Test
    public void testInitialization() {
        assertNotNull("CancelView should have a username label", cancelView.username);
        assertTrue("Username label should be an instance of JLabel", cancelView.username instanceof JLabel);
        // Add any other initialization checks you require
    }

    @Test
    public void testPropertyChange() {
        CreateEventState mockState = new CreateEventState(); // Use real or stub instance
        mockState.setUsername("testUser");

        PropertyChangeEvent event = new PropertyChangeEvent(this, "stateChange", null, mockState);
        cancelView.propertyChange(event);

        assertEquals("Username label should be updated", "testUser", cancelView.username.getText());
    }

    @Test
    public void testActionPerformed() {
        // Simulate an action event
        ActionEvent event = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, "TestCommand");

        // Redirect standard output to capture the console output
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Trigger the actionPerformed method
        cancelView.actionPerformed(event);

        // Check the output
        String expectedOutput = "Click TestCommand\n"; // Ensure this matches the actual output format
        assertEquals("Output should match the expected action command", expectedOutput, outContent.toString());

        // Reset the standard output
        System.setOut(System.out);
    }

}