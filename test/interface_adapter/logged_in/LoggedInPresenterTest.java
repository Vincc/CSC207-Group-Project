package interface_adapter.logged_in;

import interface_adapter.ViewManagerModel;
import interface_adapter.cancel.CancelState;
import interface_adapter.cancel.CancelViewModel;
import interface_adapter.createEvent.CreateEventState;
import interface_adapter.createEvent.CreateEventViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.CreateEvent.CreateEventOutputData;
import use_case.cancel.CancelOutputData;
import use_case.joinEvent.joinEventOutputData;

import static org.junit.Assert.*;

public class LoggedInPresenterTest {

    private LoggedInPresenter loggedInPresenter;
    private MockCreateEventViewModel mockCreateEventViewModel;
    private MockCancelViewModel mockCancelViewModel;
    private MockViewManagerModel mockViewManagerModel;

    private MockSignUpViewModel mockSignUpViewModel;

    private MockLoggedInViewModel mockLoggedInViewModel;

    @Before
    public void setUp() {
        mockCreateEventViewModel = new MockCreateEventViewModel();
        mockCancelViewModel = new MockCancelViewModel();
        mockViewManagerModel = new MockViewManagerModel();
        mockLoggedInViewModel = new MockLoggedInViewModel();
        mockSignUpViewModel = new MockSignUpViewModel();
        loggedInPresenter = new LoggedInPresenter(mockLoggedInViewModel, mockCreateEventViewModel, mockCancelViewModel, mockSignUpViewModel,mockViewManagerModel);
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

    @Test
    public void testPrepareJoinEventSuccessView() {
        joinEventOutputData joinEventOutputData = new joinEventOutputData("User1");
        loggedInPresenter.prepareJoinEventSuccessView(joinEventOutputData);

        assertEquals("LoggedInlView",mockViewManagerModel.activeView);

    }


    @Test
    public void testPrepareSuccessView(){
        loggedInPresenter.prepareSuccessView();

        assertEquals("SignUplView",mockViewManagerModel.activeView);
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

    private static class MockSignUpViewModel extends SignupViewModel {
        String activeView;

        private SignupState state = new SignupState(); // Initialize with a new state

        @Override
        public SignupState getState() {
            return state;
        }

        @Override
        public void setState(SignupState state) {
            this.state = state;
        }

        @Override
        public String getViewName() {
            return "SignUplView";
        }
    }

    private static class MockLoggedInViewModel extends LoggedInViewModel {
        String activeView;

        private LoggedInState state = new LoggedInState(); // Initialize with a new state

        @Override
        public LoggedInState getState() {
            return state;
        }

        @Override
        public void setState(LoggedInState state) {
            this.state = state;
        }

        @Override
        public String getViewName() {
            return "LoggedInlView";
        }
    }


}