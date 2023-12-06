package app;

import data_access.FileEventDataAccessObject;
import data_access.FileUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.createEvent.CreateEventViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import org.junit.Before;
import org.junit.Test;
import view.CreateEventView;

import java.awt.*;

import static org.junit.Assert.*;

public class createEventUseCaseFactoryTest {

    private ViewManagerModel viewManagerModel;
    private LoggedInViewModel loggedInViewModel;
    private CreateEventViewModel createEventViewModel;
    private FileEventDataAccessObject eventDataAccessObject;
    private FileUserDataAccessObject userDataAccessObject;
    private Window mainWindow;

    @Before
    public void setUp() throws Exception {
        viewManagerModel = new ViewManagerModel();
        loggedInViewModel = new LoggedInViewModel();
        createEventViewModel = new CreateEventViewModel();

        mainWindow = new Frame();
    }

    @Test
    public void testCreateMethod() {
        CreateEventView createEventView = createEventUseCaseFactory.create(
                viewManagerModel, loggedInViewModel, createEventViewModel,
                eventDataAccessObject, userDataAccessObject, mainWindow);

        assertNotNull("create method should return a non-null CreateEventView", createEventView);
    }

}