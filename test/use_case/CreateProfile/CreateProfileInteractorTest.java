package use_case.CreateProfile;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateProfileInteractorTest {
    @Test
    public void testExecute() {
        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./test_users.json", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String username = "user1";
        String user_description = "good loyal user";
        CreateProfileInputData createProfileInputData = new CreateProfileInputData(username, user_description);

        CreateProfileOutputBoundary CreateProfileSuccessPresenter = new CreateProfileOutputBoundary() {

            @Override
            public void prepareSuccessView(CreateProfileOutputData user) {
                // assertEquals(user.getUserDescription(), user_description);
                assertEquals(user.getUsername(), username);
                assertTrue(userDataAccessObject.existsByName(username));
            }

            @Override
            public void prepareSuccessView() {

            }

        };
        CreateProfileInputBoundary interactor = new CreateProfileInteractor(CreateProfileSuccessPresenter, userDataAccessObject);
        interactor.execute(createProfileInputData);
}}
