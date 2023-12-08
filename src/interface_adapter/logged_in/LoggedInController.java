package interface_adapter.logged_in;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInputData;
import use_case.CreateProfile.CreateProfileInputBoundary;
import use_case.CreateProfile.CreateProfileInputData;
import use_case.addToCalendar.addToCalendarInputData;
import use_case.addToCalendar.addToCalendarInputBoundary;
import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInputData;
import use_case.joinEvent.joinEventInputBoundary;
import use_case.joinEvent.joinEventInputData;
import use_case.logOut.LogOutInputBoundary;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class LoggedInController {
    final CreateEventInputBoundary createEventUseCaseInteractor;
    final joinEventInputBoundary joinEventUseCaseInteractor;
    final CancelInputBoundary cancelIteractor;
    final CreateProfileInputBoundary createProfileInteractor;
    final LogOutInputBoundary logOutinteractor;
    final addToCalendarInputBoundary addToCalendarInteractor;

    public LoggedInController(CreateEventInputBoundary createEventUseCaseInteractor, joinEventInputBoundary joinEventUseCaseInteractor, CancelInputBoundary cancelInputBoundary, CreateProfileInputBoundary createProfileInputBoundary , LogOutInputBoundary logOutInputBoundary, addToCalendarInputBoundary addToCalendarInteractor) {
        this.createEventUseCaseInteractor = createEventUseCaseInteractor;
        this.joinEventUseCaseInteractor = joinEventUseCaseInteractor;
        this.cancelIteractor = cancelInputBoundary;
        this.createProfileInteractor = createProfileInputBoundary;
        this.logOutinteractor = logOutInputBoundary;
        this.addToCalendarInteractor = addToCalendarInteractor;
    }
    public void executeCreateEvent(String username){
        CreateEventInputData createEventInputData = new CreateEventInputData(username);
        createEventUseCaseInteractor.execute(createEventInputData);
    }
    public void executeCancel(String username){
        CancelInputData cancelInputData = new CancelInputData(username);
        cancelIteractor.execute(cancelInputData);
    }


    public void executeCreateProfile(String username) {
        CreateProfileInputData createProfileInputData = new CreateProfileInputData(username);
        createProfileInteractor.execute(createProfileInputData);
    }
    public void addParticipants(String eventName, String username) {
        joinEventInputData joinInputData = new joinEventInputData(eventName, username);
        joinEventUseCaseInteractor.execute(joinInputData);
    }

    public void addToCalendar(String eventName, String username) throws GeneralSecurityException, IOException {
        addToCalendarInputData addInputData = new addToCalendarInputData(eventName, username);
        addToCalendarInteractor.execute(addInputData);
    }


    public void excuteLogOut(){
        logOutinteractor.execute();
    }
}
