package interface_adapter.createEvent;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateEventPresenterTest {
    private createEventPresenter presenter;
    private TestLoggedInViewModel loggedInViewModel;
    private TestViewManagerModel viewManagerModel;

    @Before
    public void setUp() {
        loggedInViewModel = new TestLoggedInViewModel();
        viewManagerModel = new TestViewManagerModel();
        presenter = new createEventPresenter(viewManagerModel, loggedInViewModel);
    }

    @Test
    public void testPrepareMakeEventSuccessView() {
        // Initial state
        String initialViewName = viewManagerModel.getActiveView();
        LoggedInState initialState = loggedInViewModel.getState();

        // Act
        presenter.prepareMakeEventSuccessView();

        // Assert
        assertNotEquals(initialViewName, viewManagerModel.getActiveView());
    }

    @Test
    public void testPrepareMakeEventFailView() {
        presenter.prepareMakeEventFailView("Error message");
    }

    // Mock implementation of LoggedInViewModel
    private class TestLoggedInViewModel extends LoggedInViewModel {
        private LoggedInState state;

        @Override
        public LoggedInState getState() {
            return state;
        }

        @Override
        public void setState(LoggedInState state) {
            this.state = state;
        }

        @Override
        public void firePropertyChanged() {
            // Mock implementation
        }

        @Override
        public String getViewName() {
            return "TestView";
        }
    }

    // Mock implementation of ViewManagerModel
    private class TestViewManagerModel extends ViewManagerModel {
        private String activeView;

        @Override
        public void setActiveView(String viewName) {
            this.activeView = viewName;
        }

        @Override
        public String getActiveView() {
            return activeView;
        }

    }
}