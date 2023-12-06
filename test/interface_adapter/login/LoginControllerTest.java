package interface_adapter.login;

import org.junit.Before;
import org.junit.Test;
import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInputData;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

import static org.junit.Assert.*;

public class LoginControllerTest {
    private LoginController loginController;
    private MockLoginInputBoundary mockLoginUseCaseInteractor;
    private MockCancelInputBoundary mockCancelInteractor;

    @Before
    public void setUp() {
        // Initialize the LoginController with mock input boundaries
        mockLoginUseCaseInteractor = new MockLoginInputBoundary();
        mockCancelInteractor = new MockCancelInputBoundary();
        loginController = new LoginController(mockLoginUseCaseInteractor, mockCancelInteractor);
    }

    @Test
    public void testExecuteLogin() {
        // Test the execute method for login
        String username = "testUser";
        String password = "testPassword";
        loginController.execute(username, password);

        // Verify that the mockLoginUseCaseInteractor was called with the correct input data
        LoginInputData inputData = mockLoginUseCaseInteractor.getInputData();
        assertNotNull(inputData);
        assertEquals(username, inputData.getUsername());
        assertEquals(password, inputData.getPassword());
    }

    @Test
    public void testExecuteCancel() {
        // Test the executeCancel method
        String username = "testUser";
        loginController.executeCancel(username);

        // Verify that the mockCancelInteractor was called with the correct input data
        CancelInputData inputData = mockCancelInteractor.getInputData();
        assertNotNull(inputData);
        assertEquals(username, inputData.getUsername());
    }

    // Mock implementation of LoginInputBoundary for testing
    private static class MockLoginInputBoundary implements LoginInputBoundary {
        private LoginInputData inputData;

        @Override
        public void execute(LoginInputData input) {
            inputData = input;
        }

        public LoginInputData getInputData() {
            return inputData;
        }
    }

    // Mock implementation of CancelInputBoundary for testing
    private static class MockCancelInputBoundary implements CancelInputBoundary {
        private CancelInputData inputData;

        @Override
        public void execute(CancelInputData input) {
            inputData = input;
        }

        public CancelInputData getInputData() {
            return inputData;
        }
    }

}