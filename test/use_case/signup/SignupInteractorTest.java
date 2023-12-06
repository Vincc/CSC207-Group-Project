package use_case.signup;

import data_access.FileUserDataAccessObject;
import entity.CommonUser;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class SignupInteractorTest {
    String username = "user1";

    String password = "password1";

    String repeat_password = "password1";

    LocalDateTime localDateTime = LocalDateTime.now();
    @Test
    public void testExecute() {
        FileUserDataAccessObject userDataAccessObject;
        SignupUserDataAccessInterface signupUserDataAccessInterface;
        try {
            signupUserDataAccessInterface = new FileUserDataAccessObject("./test_users.json", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        LocalDateTime ltd = LocalDateTime.now();

        SignupInputData signupInputData = new SignupInputData(username, password, repeat_password);
        UserFactory userFactory = new CommonUserFactory() {
            public User create(String name, String pw, LocalDateTime ltd) {
                assertNotEquals(name, username);
                assertEquals(pw, password);
                assertEquals(ltd, localDateTime);
                return new CommonUser(name, pw, ltd);
            }
        };
        SignupOutputBoundary SignupSuccessPresenter = new SignupOutputBoundary() {

            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertEquals(user.getUsername(), username);
            }

            @Override
            public void prepareFailView(String error) {

            }
        };
        SignupInputBoundary interactor = new SignupInteractor(signupUserDataAccessInterface, SignupSuccessPresenter, userFactory);
        interactor.execute(signupInputData);
}}
