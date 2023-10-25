package interface_adapter.createEventPage;

import use_case.createEventPage.CreateEventPageInteractor;
import use_case.createEventPage.CreateEventPageInputBoundary;


class CreateEventPageController {
    final CreateEventPageInputBoundary createEventPageInteractor;
    public CreateEventPageController(CreateEventPageInputBoundary createEventPageInputBoundary) {
        this.createEventPageInteractor = createEventPageInputBoundary;
    }
    public void excecute(){
        createEventPageInteractor.execute();
    };
};
