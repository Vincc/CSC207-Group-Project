package interface_adapter.logged_in;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInputData;
import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInputData;

public class LoggedInController {
    final CreateEventInputBoundary createEventUseCaseInteractor;
    final CancelInputBoundary cancelIteractor;

    public LoggedInController(CreateEventInputBoundary createEventUseCaseInteractor, CancelInputBoundary cancelInputBoundary) {
        this.createEventUseCaseInteractor = createEventUseCaseInteractor;
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
}
