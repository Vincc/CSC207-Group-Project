package use_case.login;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import junit.framework.TestCase;

import java.io.IOException;

public class LoginInteractorTest extends TestCase {

    String username = "user1";
    String password = "password1";

    LoginInputData loginInputData = new LoginInputData(username,password);

    LoginOutputBoundary presenter = new LoginOutputBoundary() {
        @Override
        public void prepareSuccessView(LoginOutputData user) {
            assertEquals(user.getUsername(),username);
        }

        @Override
        public void prepareFailView(String error) {

        }
    };

    public void testExecute() {
        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./test_users.json", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LoginInputBoundary interactor = new LoginInteractor(userDataAccessObject,presenter);
        interactor.execute(loginInputData);
    }


}