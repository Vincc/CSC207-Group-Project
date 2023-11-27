package src.app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.cancel.CancelViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInteractor;
import use_case.cancel.CancelOutputBoundary;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            FileUserDataAccessObject userDataAccessObject,
            CancelViewModel cancelViewModel) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, cancelViewModel);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            FileUserDataAccessObject userDataAccessObject,
            CancelViewModel cancelViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel, cancelViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);
        CancelOutputBoundary cancelOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel, cancelViewModel);
        CancelInputBoundary cancelInteractor = new CancelInteractor(cancelOutputBoundary, userDataAccessObject);
        return new LoginController(loginInteractor, cancelInteractor);
    }
}
