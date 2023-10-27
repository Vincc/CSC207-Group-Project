package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.createEventPage.createEventPageViewModel;
import interface_adapter.createEventPage.CreateEventPageController;
import interface_adapter.createEventPage.CreateEventPagePresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.createEventPage.CreateEventPageInputBoundary;
import use_case.createEventPage.CreateEventPageInteractor;
import use_case.createEventPage.CreateEventPageOutputBoundery;
import view.LoggedInView;
import view.createEventView;
//import use_case.login.LoginUserDataAccessInterface;


public class LoggedInUseCaseFactory {
    private LoggedInUseCaseFactory() {}

    public static LoggedInView create(
            ViewManagerModel viewManagerModel,
            createEventPageViewModel createEventPageViewModel,
            LoggedInViewModel loggedInViewModel) {

        CreateEventPageController CreateEventPageController = createLoginUseCase(viewManagerModel, createEventPageViewModel, loggedInViewModel);
        return new LoggedInView(loggedInViewModel, CreateEventPageController);

    }

    private static CreateEventPageController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            createEventPageViewModel createEventPageViewModel,
            LoggedInViewModel loggedInViewModel) {

        // Notice how we pass this method's parameters to the Presenter.
        CreateEventPageOutputBoundery CreateEventPageOutputBoundery = new CreateEventPagePresenter
                (viewManagerModel,loggedInViewModel,createEventPageViewModel);

        UserFactory userFactory = new CommonUserFactory();

        CreateEventPageInputBoundary createEventPageInteractor = new CreateEventPageInteractor(CreateEventPageOutputBoundery);

        return new CreateEventPageController(createEventPageInteractor);
    }
}
