package src.app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.cancel.CancelViewModel;
import interface_adapter.createEvent.CreateEventViewModel;
import interface_adapter.createProfile.CreateProfilePresenter;
import interface_adapter.createProfile.CreateProfileViewModel;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInteractor;
import use_case.CreateEvent.CreateEventOutputBoundary;
import use_case.CreateProfile.CreateProfileInputBoundary;
import use_case.CreateProfile.CreateProfileInteractor;
import use_case.CreateProfile.CreateProfileOutputBoundary;
import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInteractor;
import use_case.cancel.CancelOutputBoundary;
import view.LoggedInView;

public class LoggedInUseCaseFactory {
    private LoggedInUseCaseFactory(){
    }
    public static LoggedInView create(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, CreateEventViewModel createEventViewModel,
                                      FileUserDataAccessObject userDataAccessObject,CancelViewModel cancelViewModel, CreateProfileViewModel createProfileViewModel){

        LoggedInController loggedInController =
                createLoggedInUseCase(viewManagerModel,loggedInViewModel,createEventViewModel ,userDataAccessObject,cancelViewModel, createProfileViewModel);
        return new LoggedInView(loggedInViewModel,loggedInController);

    }

    private static LoggedInController createLoggedInUseCase(
            ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, CreateEventViewModel createEventViewModel,
            FileUserDataAccessObject userDataAccessObject, CancelViewModel cancelViewModel, CreateProfileViewModel createProfileViewModel){
        CreateEventOutputBoundary createEventOutputBoundary = new LoggedInPresenter(loggedInViewModel,createEventViewModel, cancelViewModel, viewManagerModel);
        CreateProfileOutputBoundary createProfileOutputBoundary = new CreateProfilePresenter(viewManagerModel, createProfileViewModel);
        CreateEventInputBoundary createEventInteractor = new CreateEventInteractor(createEventOutputBoundary,userDataAccessObject);
        UserFactory userFactory = new CommonUserFactory();
        CancelOutputBoundary cancelOutputBoundary = new LoggedInPresenter(loggedInViewModel,createEventViewModel, cancelViewModel, viewManagerModel);
        CancelInputBoundary cancelInputBoundary = new CancelInteractor(cancelOutputBoundary,userDataAccessObject);
        CreateProfileInputBoundary createProfileInputBoundary = new CreateProfileInteractor(createProfileOutputBoundary, userDataAccessObject);
        return new LoggedInController(createEventInteractor, cancelInputBoundary, createProfileInputBoundary);

    }

}
