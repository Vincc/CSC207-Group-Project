package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.cancel.CancelState;
import interface_adapter.cancel.CancelViewModel;
import interface_adapter.createEvent.CreateEventState;
import interface_adapter.createEvent.CreateEventViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.CreateEvent.CreateEventOutputData;
import use_case.cancel.CancelOutputData;

import static org.junit.Assert.*;

public class LoggedInPresenterTest {

    private LoggedInPresenter loggedInPresenter;
    private MockCreateEventViewModel mockCreateEventViewModel;
    private MockCancelViewModel mockCancelViewModel;
    private MockViewManagerModel mockViewManagerModel;

    @Before
    public void setUp() {
        mockCreateEventViewModel = new MockCreateEventViewModel();
        mockCancelViewModel = new MockCancelViewModel();
        mockViewManagerModel = new MockViewManagerModel();
        loggedInPresenter = new LoggedInPresenter(null, mockCreateEventViewModel, mockCancelViewModel, mockViewManagerModel);
    }

    @Test
    public void testPrepareSuccessViewForCreateEvent() {
        CreateEventOutputData createEventData = new CreateEventOutputData("User1");

        loggedInPresenter.prepareSuccessView(createEventData);

        assertEquals("User1", mockCreateEventViewModel.getState().getUsername());
        assertEquals("CreateEventView", mockViewManagerModel.activeView);
    }

    @Test
    public void testPrepareSuccessViewForCancel() {
        CancelOutputData cancelData = new CancelOutputData("User2");

        loggedInPresenter.prepareSuccessView(cancelData);

        assertEquals("User2", mockCancelViewModel.getState().getUsername());
        assertEquals("CancelView", mockViewManagerModel.activeView);
    }

    // Mock classes
    private static class MockCreateEventViewModel extends CreateEventViewModel {
        private CreateEventState state = new CreateEventState(); // Initialize with a new state

        @Override
        public CreateEventState getState() {
            return state;
        }

        @Override
        public void setState(CreateEventState state) {
            this.state = state;
        }

        @Override
        public String getViewName() {
            return "CreateEventView";
        }
    }

    private static class MockCancelViewModel extends CancelViewModel {
        private CancelState state = new CancelState(); // Initialize with a new state

        @Override
        public CancelState getState() {
            return state;
        }

        @Override
        public void setState(CancelState state) {
            this.state = state;
        }

        @Override
        public String getViewName() {
            return "CancelView";
        }
    }

    private static class MockViewManagerModel extends ViewManagerModel {
        String activeView;

        @Override
        public void setActiveView(String viewName) {
            this.activeView = viewName;
        }
    }


}