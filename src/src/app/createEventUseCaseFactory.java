package src.app;

import data_access.FileEventDataAccessObject;
import entity.CommonSportsEventFactory;
import entity.SportsEventFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.createEvent.CreateEventController;
import interface_adapter.createEvent.CreateEventViewModel;
import interface_adapter.createEvent.createEventPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.makeEvent.makeEventInputBoundary;
import use_case.makeEvent.makeEventInteractor;
import use_case.makeEvent.makeEventOutputBoundary;
import view.CreateEventView;
public class createEventUseCaseFactory {
    private createEventUseCaseFactory(){
    }

    public static CreateEventView create(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, CreateEventViewModel createEventViewModel,
                                         FileEventDataAccessObject eventDataAccessObject){
        CreateEventController createEventController = createMakeEventUseCase(viewManagerModel, createEventViewModel,loggedInViewModel, eventDataAccessObject);

        return new CreateEventView(createEventViewModel, createEventController);

    }


    private static CreateEventController createMakeEventUseCase(
            ViewManagerModel viewManagerModel, CreateEventViewModel createEventViewModel,
            LoggedInViewModel loggedInViewModel, FileEventDataAccessObject eventDataAccessObject){
        SportsEventFactory sportsEventFactory = new CommonSportsEventFactory();
        makeEventOutputBoundary createEventOutputBoundary = new createEventPresenter(viewManagerModel, loggedInViewModel);
        makeEventInputBoundary makeEventInteractor = new makeEventInteractor(eventDataAccessObject, createEventOutputBoundary, sportsEventFactory);

        return new CreateEventController(makeEventInteractor);

    }

}
