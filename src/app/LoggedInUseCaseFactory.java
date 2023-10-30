package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.createEventPage.createEventPageViewModel;
import interface_adapter.createEventPage.CreateEventPageController;
import interface_adapter.createEventPage.CreateEventPagePresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginPresenter;
import use_case.createEvent.CreateEventOutputBoundery;
import use_case.createEventPage.CreateEventPageInputBoundary;
import use_case.createEventPage.CreateEventPageInteractor;
import use_case.createEventPage.CreateEventPageOutputBoundery;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;
import view.LoggedInView;
import view.createEventView;
//import use_case.login.LoginUserDataAccessInterface;


public class LoggedInUseCaseFactory {
    private LoggedInUseCaseFactory() {}

    public static LoggedInView create(
            ViewManagerModel viewManagerModel,
            createEventPageViewModel createEventPageViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject) {

        CreateEventPageController CreateEventPageController =
                createLoginUseCase(viewManagerModel, createEventPageViewModel, loggedInViewModel,userDataAccessObject);
        return new LoggedInView(loggedInViewModel, CreateEventPageController);

    }

    private static CreateEventPageController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            createEventPageViewModel createEventPageViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject) {

        // Notice how we pass this method's parameters to the Presenter.
        CreateEventPageOutputBoundery CreateEventPageOutputBoundery = new CreateEventPagePresenter
                (viewManagerModel,loggedInViewModel,createEventPageViewModel);

        UserFactory userFactory = new CommonUserFactory();

        CreateEventPageInputBoundary createEventPageInteractor = new CreateEventPageInteractor(CreateEventPageOutputBoundery,userDataAccessObject);

        return new CreateEventPageController(createEventPageInteractor);
    }
}
