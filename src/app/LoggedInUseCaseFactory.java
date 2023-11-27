package app;

import data_access.FileEventDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.cancel.CancelViewModel;
import interface_adapter.createEvent.CreateEventViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInteractor;
import use_case.CreateEvent.CreateEventOutputBoundary;
import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInteractor;
import use_case.cancel.CancelOutputBoundary;
import use_case.joinEvent.joinEventOutputBoundary;
import use_case.joinEvent.joinEventInputBoundary;
import use_case.joinEvent.joinEventInteractor;
import use_case.joinEvent.joinEventOutputData;
import view.LoggedInView;
import use_case.joinEvent.joinEventDataAccessInterface;

public class LoggedInUseCaseFactory {
    private LoggedInUseCaseFactory(){
    }
    public static LoggedInView create(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, CreateEventViewModel createEventViewModel,
                                      FileUserDataAccessObject userDataAccessObject,FileEventDataAccessObject fileEventDataAccessObject,CancelViewModel cancelViewModel){

        LoggedInController loggedInController =
                createLoggedInUseCase(viewManagerModel,loggedInViewModel,createEventViewModel ,userDataAccessObject,cancelViewModel,fileEventDataAccessObject);
        return new LoggedInView(loggedInViewModel,loggedInController);

    }

    private static LoggedInController createLoggedInUseCase(
            ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, CreateEventViewModel createEventViewModel,
            FileUserDataAccessObject userDataAccessObject, CancelViewModel cancelViewModel, FileEventDataAccessObject fileEventDataAccessObject ){
        CreateEventOutputBoundary createEventOutputBoundary = new LoggedInPresenter(loggedInViewModel,createEventViewModel, cancelViewModel, viewManagerModel);
        CreateEventInputBoundary createEventInteractor = new CreateEventInteractor(createEventOutputBoundary,userDataAccessObject);
        UserFactory userFactory = new CommonUserFactory();
        CancelOutputBoundary cancelOutputBoundary = new LoggedInPresenter(loggedInViewModel,createEventViewModel, cancelViewModel, viewManagerModel);
        CancelInputBoundary cancelInputBoundary = new CancelInteractor(cancelOutputBoundary,userDataAccessObject);
        joinEventInputBoundary joinEventUseCaseInteractor = new joinEventInteractor(fileEventDataAccessObject);


        return new LoggedInController(createEventInteractor, joinEventUseCaseInteractor, cancelInputBoundary);

    }

}
