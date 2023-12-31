package app;

import data_access.FileEventDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CommonSportsEventFactory;
import entity.CommonUserFactory;
import interface_adapter.cancel.CancelViewModel;
import interface_adapter.createEvent.CreateEventViewModel;
import interface_adapter.createProfile.CreateProfileViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import use_case.login.LoginUserDataAccessInterface;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        CreateEventViewModel createEventViewModel = new CreateEventViewModel();
        CancelViewModel cancelViewModel = new CancelViewModel();
        CreateProfileViewModel createProfileViewModel = new CreateProfileViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.json", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileEventDataAccessObject eventDataAccessObject;
        try {
            eventDataAccessObject = new FileEventDataAccessObject("./events.json", new CommonUserFactory(), new CommonSportsEventFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, cancelViewModel);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, cancelViewModel);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = LoggedInUseCaseFactory.create(viewManagerModel,loggedInViewModel, createEventViewModel,userDataAccessObject,eventDataAccessObject,cancelViewModel, createProfileViewModel,signupViewModel);

        views.add(loggedInView, loggedInView.viewName);

        CreateProfileView createProfileView = CreateProfileUseCaseFactory.create(viewManagerModel,createProfileViewModel, loggedInViewModel, userDataAccessObject);
        views.add(createProfileView, createProfileView.viewName);

        CancelView cancelView = new CancelView(cancelViewModel);
        views.add(cancelView,cancelView.viewName);

        CreateEventView createEventView = createEventUseCaseFactory.create(viewManagerModel, loggedInViewModel, createEventViewModel, eventDataAccessObject, userDataAccessObject, application);
        views.add(createEventView, createEventView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}