package interface_adapter.signup;

import org.junit.Before;
import org.junit.Test;
import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInputData;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

import static org.junit.Assert.*;

public class SignupControllerTest {

    private SignupInputBoundary mockSignupInteractor;
    private CancelInputBoundary mockCancelInteractor;
    private SignupController controller;
    private boolean signupExecuted;
    private boolean cancelExecuted;

    @Before
    public void setUp() {
        // Reset flags
        signupExecuted = false;
        cancelExecuted = false;

        // Manual mock for SignupInputBoundary
        mockSignupInteractor = new SignupInputBoundary() {
            @Override
            public void execute(SignupInputData inputData) {
                signupExecuted = true;
                // Verify that inputData contains the expected values
                assertEquals("testUser", inputData.getUsername());
                assertEquals("password123", inputData.getRepeatPassword());
            }
        };

        // Manual mock for CancelInputBoundary
        mockCancelInteractor = new CancelInputBoundary() {
            @Override
            public void execute(CancelInputData cancelData) {
                cancelExecuted = true;
                // Verify that cancelData contains the expected value
                assertEquals("testUser", cancelData.getUsername());
            }
        };

        controller = new SignupController(mockSignupInteractor, mockCancelInteractor);
    }

    @Test
    public void testExecute() {
        controller.execute("testUser", "password123", "password123");
        assertTrue(signupExecuted);
    }

    @Test
    public void testExecuteCancel() {
        controller.executeCancel("testUser");
        assertTrue(cancelExecuted);
    }

}