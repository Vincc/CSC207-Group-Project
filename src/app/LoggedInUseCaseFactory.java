package app;

import data_access.FileEventDataAccessObject;
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
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInteractor;
import use_case.CreateEvent.CreateEventOutputBoundary;
import use_case.CreateProfile.CreateProfileInputBoundary;
import use_case.CreateProfile.CreateProfileInteractor;
import use_case.CreateProfile.CreateProfileOutputBoundary;
import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInteractor;
import use_case.cancel.CancelOutputBoundary;
import use_case.joinEvent.joinEventInputBoundary;
import use_case.joinEvent.joinEventInteractor;
import use_case.joinEvent.joinEventOutputBoundary;
import use_case.logOut.LogOutInputBoundary;
import use_case.logOut.LogOutInteractor;
import use_case.logOut.LogoOutOutputBoundary;
import use_case.login.LoginOutputBoundary;
import view.LoggedInView;

public class LoggedInUseCaseFactory {
    private LoggedInUseCaseFactory(){
    }
    public static LoggedInView create(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, CreateEventViewModel createEventViewModel,
                                      FileUserDataAccessObject userDataAccessObject,FileEventDataAccessObject fileEventDataAccessObject,CancelViewModel cancelViewModel, CreateProfileViewModel createProfileViewModel,SignupViewModel signupViewModel){

        LoggedInController loggedInController =
                createLoggedInUseCase(viewManagerModel,loggedInViewModel,createEventViewModel ,userDataAccessObject,cancelViewModel,fileEventDataAccessObject, createProfileViewModel,signupViewModel);
        return new LoggedInView(loggedInViewModel,loggedInController);


        }

    private static LoggedInController createLoggedInUseCase(
            ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, CreateEventViewModel createEventViewModel,

            FileUserDataAccessObject userDataAccessObject, CancelViewModel cancelViewModel, FileEventDataAccessObject fileEventDataAccessObject, CreateProfileViewModel createProfileViewModel, SignupViewModel signupViewModel){
        CreateEventOutputBoundary createEventOutputBoundary = new LoggedInPresenter(loggedInViewModel,createEventViewModel, cancelViewModel,signupViewModel,viewManagerModel);
        CreateProfileOutputBoundary createProfileOutputBoundary = new CreateProfilePresenter(viewManagerModel, createProfileViewModel, loggedInViewModel);
        CreateEventInputBoundary createEventInteractor = new CreateEventInteractor(createEventOutputBoundary,userDataAccessObject);
        UserFactory userFactory = new CommonUserFactory();
        CancelOutputBoundary cancelOutputBoundary = new LoggedInPresenter(loggedInViewModel,createEventViewModel, cancelViewModel, signupViewModel,viewManagerModel);
        CancelInputBoundary cancelInputBoundary = new CancelInteractor(cancelOutputBoundary,userDataAccessObject);

        joinEventOutputBoundary joinEventOutputBoundary = new LoggedInPresenter(loggedInViewModel,createEventViewModel, cancelViewModel, signupViewModel,viewManagerModel);
        joinEventInputBoundary joinEventUseCaseInteractor = new joinEventInteractor(fileEventDataAccessObject, joinEventOutputBoundary);

        CreateProfileInputBoundary createProfileInputBoundary = new CreateProfileInteractor(createProfileOutputBoundary, userDataAccessObject);
        LogoOutOutputBoundary logoOutOutputBoundary = new LoggedInPresenter(loggedInViewModel,createEventViewModel, cancelViewModel,signupViewModel ,viewManagerModel);
        LogOutInputBoundary logOutUseCaseInteractor = new LogOutInteractor(logoOutOutputBoundary);



        return new LoggedInController(createEventInteractor, joinEventUseCaseInteractor, cancelInputBoundary, createProfileInputBoundary,logOutUseCaseInteractor);


    }

}
