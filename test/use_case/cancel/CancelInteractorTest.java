package use_case.cancel;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import junit.framework.TestCase;

import java.io.IOException;

public class CancelInteractorTest extends TestCase {

    public void testExecute() {
        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./test_users.json", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String username = "user1";
        CancelInputData cancelInputData = new CancelInputData(username);
        CancelOutputBoundary SucsessPresenter = new CancelOutputBoundary() {
            @Override
            public void prepareSuccessView(CancelOutputData user) {

                assertEquals("user1" , user.getUsername());
                assertTrue(userDataAccessObject.existsByName("user1"));
            }
        };

        CancelInputBoundary iteractor = new CancelInteractor(SucsessPresenter,userDataAccessObject);
        iteractor.execute(cancelInputData);
    }
}