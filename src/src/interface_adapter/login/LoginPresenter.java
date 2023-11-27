package src.interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.cancel.CancelState;
import interface_adapter.cancel.CancelViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.cancel.CancelOutputBoundary;
import use_case.cancel.CancelOutputData;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

public class LoginPresenter implements LoginOutputBoundary, CancelOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final CancelViewModel cancelViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel, CancelViewModel cancelViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.cancelViewModel = cancelViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }

    public void prepareSuccessView(CancelOutputData user) {
        CancelState cancelState = cancelViewModel.getState();
        cancelState.setUsername(user.getUsername());
        this.cancelViewModel.setState(cancelState);
        this.cancelViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(cancelViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
