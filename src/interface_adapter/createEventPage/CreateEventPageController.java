package interface_adapter.createEventPage;

import use_case.createEventPage.CreateEventPageInputData;
import use_case.createEventPage.CreateEventPageInteractor;
import use_case.createEventPage.CreateEventPageInputBoundary;


public class CreateEventPageController {
    final CreateEventPageInputBoundary createEventPageInteractor;
    public CreateEventPageController(CreateEventPageInputBoundary createEventPageInputBoundary) {
        this.createEventPageInteractor = createEventPageInputBoundary;
    }
    public void execute(String username){
        CreateEventPageInputData CreateEventPageInputData = new CreateEventPageInputData(username);
        createEventPageInteractor.execute(CreateEventPageInputData);
    };
};
