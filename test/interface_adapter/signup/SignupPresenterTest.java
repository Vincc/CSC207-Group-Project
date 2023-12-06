package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.cancel.CancelState;
import interface_adapter.cancel.CancelViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.cancel.CancelOutputData;
import use_case.signup.SignupOutputData;

import static org.junit.Assert.*;

public class SignupPresenterTest {

    private ViewManagerModel mockViewManagerModel;
    private SignupViewModel mockSignupViewModel;
    private LoginViewModel mockLoginViewModel;
    private CancelViewModel mockCancelViewModel;
    private SignupPresenter presenter;

    @Before
    public void setUp() {
        mockViewManagerModel = new ViewManagerModel() {
            String activeView;

            @Override
            public void setActiveView(String viewName) {
                this.activeView = viewName;
            }

            @Override
            public String getActiveView() {
                return this.activeView;
            }

            @Override
            public void firePropertyChanged() {
                // Implement if needed
            }
        };

        mockSignupViewModel = new SignupViewModel() {
            SignupState state = new SignupState();

            @Override
            public SignupState getState() {
                return state;
            }

            @Override
            public void firePropertyChanged() {
                // Implement if needed
            }
        };

        mockLoginViewModel = new LoginViewModel() {
            LoginState state = new LoginState();

            @Override
            public LoginState getState() {
                return state;
            }

            @Override
            public void firePropertyChanged() {
                // Implement if needed
            }
        };

        mockCancelViewModel = new CancelViewModel() {
            CancelState state = new CancelState();

            @Override
            public CancelState getState() {
                return state;
            }

            @Override
            public void firePropertyChanged() {
                // Implement if needed
            }
        };

        presenter = new SignupPresenter(mockViewManagerModel, mockSignupViewModel, mockLoginViewModel, mockCancelViewModel);
    }


    @Test
    public void testPrepareSuccessView() {
        SignupOutputData data = new SignupOutputData("testUser","2023",false);
        data.setCreationTime("2023-12-06T10:15:30");

        presenter.prepareSuccessView(data);

        assertEquals("testUser", mockLoginViewModel.getState().getUsername());
        assertEquals(mockLoginViewModel.getViewName(), mockViewManagerModel.getActiveView());
    }

    @Test
    public void testPrepareFailView() {
        String errorMessage = "Username already exists";

        presenter.prepareFailView(errorMessage);

        assertEquals(errorMessage, mockSignupViewModel.getState().getUsernameError());
    }

    @Test
    public void testPrepareSuccessViewCancel() {
        CancelOutputData cancelData = new CancelOutputData("testUser");

        presenter.prepareSuccessView(cancelData);

        assertEquals("testUser", mockLoginViewModel.getState().getUsername());
        assertEquals("log in", mockViewManagerModel.getActiveView());
    }

}