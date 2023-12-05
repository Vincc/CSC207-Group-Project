package app;

import data_access.FileEventDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CommonSportsEventFactory;
import entity.SportsEventFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.createEvent.CreateEventViewModel;

import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInteractor;
import use_case.cancel.CancelOutputBoundary;
import view.CreateEventView;
import interface_adapter.createEvent.CreateEventController;
import use_case.makeEvent.makeEventInputBoundary;
import use_case.makeEvent.makeEventOutputBoundary;
import interface_adapter.createEvent.createEventPresenter;
import use_case.makeEvent.makeEventInteractor;

import java.awt.*;

public class createEventUseCaseFactory {
    private createEventUseCaseFactory(){
    }

    public static CreateEventView create(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, CreateEventViewModel createEventViewModel,
                                         FileEventDataAccessObject eventDataAccessObject, FileUserDataAccessObject userDataAccessObject, Window mainwindow){
        CreateEventController createEventController = createMakeEventUseCase(viewManagerModel, createEventViewModel,loggedInViewModel, eventDataAccessObject, userDataAccessObject);

        return new CreateEventView(createEventViewModel, createEventController, mainwindow);

    }


    private static CreateEventController createMakeEventUseCase(
            ViewManagerModel viewManagerModel, CreateEventViewModel createEventViewModel,
            LoggedInViewModel loggedInViewModel, FileEventDataAccessObject eventDataAccessObject, FileUserDataAccessObject userDataAccessObject){
        SportsEventFactory sportsEventFactory = new CommonSportsEventFactory();
        makeEventOutputBoundary createEventOutputBoundary = new createEventPresenter(viewManagerModel, loggedInViewModel);
        makeEventInputBoundary makeEventInteractor = new makeEventInteractor(eventDataAccessObject, userDataAccessObject, createEventOutputBoundary, sportsEventFactory);
        CancelOutputBoundary cancelOutputBoundary = new createEventPresenter(viewManagerModel, loggedInViewModel);
        CancelInputBoundary cancelInputBoundary = new CancelInteractor(cancelOutputBoundary,userDataAccessObject);

        return new CreateEventController(makeEventInteractor, cancelInputBoundary);

    }

}
