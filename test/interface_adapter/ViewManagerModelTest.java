package interface_adapter;

import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.*;

public class ViewManagerModelTest {

    private ViewManagerModel model;
    private String lastPropertyName;
    private String lastNewValue;

    @Before
    public void setUp() {
        model = new ViewManagerModel();
        lastPropertyName = null;
        lastNewValue = null;

        PropertyChangeListener testListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                lastPropertyName = evt.getPropertyName();
                lastNewValue = (String) evt.getNewValue();
            }
        };
        model.addPropertyChangeListener(testListener);
    }

    @Test
    public void testSetActiveView() {
        String expectedViewName = "TestView";
        model.setActiveView(expectedViewName);

        assertEquals(expectedViewName, model.getActiveView());
    }

    @Test
    public void testPropertyChangeEventFired() {
        String newViewName = "AnotherTestView";
        model.setActiveView(newViewName);
        model.firePropertyChanged();

        assertEquals("view", lastPropertyName);
        assertEquals(newViewName, lastNewValue);
    }

}