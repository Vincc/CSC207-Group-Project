package use_case.EditProfile;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import junit.framework.TestCase;
import use_case.CreateProfile.CreateProfileOutputBoundary;
import use_case.CreateProfile.CreateProfileOutputData;

import java.io.IOException;

public class EditProfileInteractorTest extends TestCase {


    String username = "user1";
    String description = "good loyal user";

    EditProfileInputData editProfileInputData = new EditProfileInputData(username,description);



    public void testExecute() {
        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./test_users.json", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        CreateProfileOutputBoundary presenter = new CreateProfileOutputBoundary() {
            @Override
            public void prepareSuccessView(CreateProfileOutputData profileOutputData) {
                assertEquals(profileOutputData.getUserDescription(),description);
                assertEquals(profileOutputData.getUsername(),username);
                assertTrue(userDataAccessObject.existsByName(username));
            }

            @Override
            public void prepareSuccessView() {
            }
        };
        EditProfileInputBoundary interactor = new EditProfileInteractor(presenter,userDataAccessObject);
        interactor.execute(editProfileInputData);

    }
}