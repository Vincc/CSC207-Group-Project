package interface_adapter.logged_in;

import org.junit.Before;
import org.junit.Test;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInputData;
import use_case.CreateProfile.CreateProfileInputBoundary;
import use_case.CreateProfile.CreateProfileInputData;
import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInputData;
import use_case.joinEvent.joinEventInputBoundary;
import use_case.joinEvent.joinEventInputData;
import use_case.logOut.LogOutInputBoundary;
import use_case.logOut.LogoutInputData;

import static org.junit.Assert.*;

public class LoggedInControllerTest {

    private LoggedInController loggedInController;
    private CreateEventInputBoundary createEventInputBoundaryMock;
    private joinEventInputBoundary joinEventInputBoundaryMock;
    private CancelInputBoundary cancelInputBoundaryMock;
    private CreateProfileInputBoundary createProfileInputBoundaryMock;

    private LogOutInputBoundary logOutInputBoundaryMock;

    private boolean isCreateEventCalled;
    private boolean isJoinEventCalled;
    private boolean isCancelCalled;
    private boolean isCreateProfileCalled;

    private boolean isLogOutCalled;



    @Before
    public void setUp() {
        // Reset flags
        isCreateEventCalled = false;
        isJoinEventCalled = false;
        isCancelCalled = false;
        isCreateProfileCalled = false;
        isLogOutCalled =false;

        // Initialize the mock objects
        createEventInputBoundaryMock = new CreateEventInputBoundary() {
            @Override
            public void execute(CreateEventInputData createEventInputData) {
                isCreateEventCalled = true;
            }
        };

        joinEventInputBoundaryMock = new joinEventInputBoundary() {
            @Override
            public void execute(joinEventInputData joinInputData) {
                isJoinEventCalled = true;
            }
        };

        cancelInputBoundaryMock = new CancelInputBoundary() {
            @Override
            public void execute(CancelInputData cancelInputData) {
                isCancelCalled = true;
            }
        };

        createProfileInputBoundaryMock = new CreateProfileInputBoundary() {
            @Override
            public void execute(CreateProfileInputData createProfileInputData) {
                isCreateProfileCalled = true;
            }

            public void execute(){}
        };

        logOutInputBoundaryMock = new LogOutInputBoundary() {
            @Override
            public void execute() {isCreateProfileCalled = true;}
        };

        // Initialize the LoggedInController with the mock objects
        loggedInController = new LoggedInController(createEventInputBoundaryMock,
                joinEventInputBoundaryMock,
                cancelInputBoundaryMock,
                createProfileInputBoundaryMock,
                logOutInputBoundaryMock
                );
    }

    @Test
    public void testExecuteCreateEvent() {
        String username = "testUser";
        loggedInController.executeCreateEvent(username);
        assertTrue("CreateEvent method should be called", isCreateEventCalled);
    }

    @Test
    public void testExecuteCancel() {
        String username = "testUser";
        loggedInController.executeCancel(username);
        assertTrue("Cancel method should be called", isCancelCalled);
    }

    @Test
    public void testExecuteCreateProfile() {
        String username = "testUser";
        loggedInController.executeCreateProfile(username);
        assertTrue("CreateProfile method should be called", isCreateProfileCalled);
    }

    @Test
    public void testAddParticipants() {
        String eventName = "testEvent";
        String username = "testUser";
        loggedInController.addParticipants(eventName, username);
        assertTrue("JoinEvent method should be called", isJoinEventCalled);
    }
}