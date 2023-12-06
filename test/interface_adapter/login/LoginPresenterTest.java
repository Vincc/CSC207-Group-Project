package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.cancel.CancelViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.cancel.CancelOutputData;
import use_case.login.LoginOutputData;

import static org.junit.Assert.*;

public class LoginPresenterTest {
    private LoginPresenter loginPresenter;
    private ViewManagerModel viewManagerModel;
    private LoggedInViewModel loggedInViewModel;
    private LoginViewModel loginViewModel;
    private CancelViewModel cancelViewModel;

    @Before
    public void setUp() {
        // Initialize your mock objects here
        viewManagerModel = new ViewManagerModel(); // Replace with actual implementation
        loggedInViewModel = new LoggedInViewModel(); // Replace with actual implementation
        loginViewModel = new LoginViewModel(); // Replace with actual implementation
        cancelViewModel = new CancelViewModel(); // Replace with actual implementation

        // Initialize the LoginPresenter with mock objects
        loginPresenter = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel, cancelViewModel);
    }

    @Test
    public void testPrepareSuccessView() {
        // Create a mock LoginOutputData
        LoginOutputData mockLoginOutputData = new LoginOutputData("testUser",false);

        // Call the method to test
        loginPresenter.prepareSuccessView(mockLoginOutputData);

        // Verify that the view was updated correctly
        assertEquals("testUser", loggedInViewModel.getState().getUsername());
        assertEquals(loggedInViewModel.getViewName(), viewManagerModel.getActiveView());
    }

    @Test
    public void testPrepareFailView() {
        // Call the method to test
        loginPresenter.prepareFailView("Error message");

        // Verify that the view was updated correctly
        assertEquals("Error message", loginViewModel.getState().getUsernameError());
    }


}