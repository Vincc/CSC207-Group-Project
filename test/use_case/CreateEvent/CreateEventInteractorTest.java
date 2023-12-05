package use_case.CreateEvent;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import junit.framework.TestCase;

import java.io.IOException;

public class CreateEventInteractorTest extends TestCase {

    public void testExecute() {
        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./test_users.json", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String username = "user1";

        CreateEventInputData createEventInputData = new CreateEventInputData(username);

        CreateEventOutputBoundary createEventSuccessPresenter = new CreateEventOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateEventOutputData user) {
                assertEquals(user.getUsername(),username);
                assertTrue(userDataAccessObject.existsByName(username));
            }
        };
        CreateEventInputBoundary interactor = new CreateEventInteractor(createEventSuccessPresenter,userDataAccessObject);
        interactor.execute(createEventInputData);
    }
}