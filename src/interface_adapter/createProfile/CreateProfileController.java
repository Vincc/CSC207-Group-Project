package interface_adapter.createProfile;

import use_case.CreateProfile.CreateProfileInputBoundary;
import use_case.CreateProfile.CreateProfileInputData;
import use_case.login.LoginInputBoundary;
import use_case.EditProfile.EditProfileInputBoundary;
import use_case.EditProfile.EditProfileInputData;
import use_case.EditProfile.EditProfileOutputBoundary;

public class CreateProfileController {
    final CreateProfileInputBoundary createProfileInputBoundary;
    final EditProfileInputBoundary editProfileInputBoundary;


    public CreateProfileController(CreateProfileInputBoundary createProfileInputBoundary, EditProfileInputBoundary editProfileInputBoundary) {
        this.createProfileInputBoundary = createProfileInputBoundary;
        this.editProfileInputBoundary = editProfileInputBoundary;
    }
    public void executeCreateProfile(String Username, String UserDescription) {
        CreateProfileInputData createProfileInputData = new CreateProfileInputData(Username, UserDescription);
        createProfileInputBoundary.execute(createProfileInputData);
    }

    public void executeEditProfile(String Username, String UserDescription) {
        EditProfileInputData editProfileInputData = new EditProfileInputData(Username, UserDescription);
        editProfileInputBoundary.execute(editProfileInputData);

    }
    public void executeCancel() {
        createProfileInputBoundary.execute();
    }

}