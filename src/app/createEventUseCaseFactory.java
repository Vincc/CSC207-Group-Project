package app;

import data_access.FileEventDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CommonSportsEventFactory;
import entity.CommonUserFactory;
import entity.SportsEventFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.createEvent.CreateEventViewModel;

import interface_adapter.logged_in.LoggedInViewModel;
import view.CreateEventView;
import interface_adapter.createEvent.CreateEventController;
import use_case.makeEvent.makeEventInputBoundary;
import use_case.makeEvent.makeEventOutputBoundary;
import interface_adapter.createEvent.createEventPresenter;
import use_case.makeEvent.makeEventInteractor;
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
