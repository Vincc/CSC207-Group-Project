package interface_adapter.logged_in;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInputData;
import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInputData;
import use_case.joinEvent.joinEventInputBoundary;
import use_case.joinEvent.joinEventInputData;

public class LoggedInController {
    final CreateEventInputBoundary createEventUseCaseInteractor;
    final joinEventInputBoundary joinEventUseCaseInteractor;
    final CancelInputBoundary cancelIteractor;

    public LoggedInController(CreateEventInputBoundary createEventUseCaseInteractor, joinEventInputBoundary joinEventUseCaseInteractor, CancelInputBoundary cancelInputBoundary) {
        this.createEventUseCaseInteractor = createEventUseCaseInteractor;
        this.joinEventUseCaseInteractor = joinEventUseCaseInteractor;
        this.cancelIteractor = cancelInputBoundary;
    }
    public void executeCreateEvent(String username){
        CreateEventInputData createEventInputData = new CreateEventInputData(username);
        createEventUseCaseInteractor.execute(createEventInputData);
    }
    public void executeCancel(String username){
        CancelInputData cancelInputData = new CancelInputData(username);
        cancelIteractor.execute(cancelInputData);
    }

    public void addParticipants(String eventName, String username) {
        joinEventInputData joinInputData = new joinEventInputData(eventName, username);
        joinEventUseCaseInteractor.execute(joinInputData);
    }
}
