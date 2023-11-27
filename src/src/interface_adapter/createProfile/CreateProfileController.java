package src.interface_adapter.createProfile;

import use_case.CreateProfile.CreateProfileInputBoundary;
import use_case.CreateProfile.CreateProfileInputData;

public class CreateProfileController {
    final CreateProfileInputBoundary inputBoundary;

    public CreateProfileController(CreateProfileInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void executeCreateProfile(String Username, String UserDescription) {
        CreateProfileInputData createProfileInputData = new CreateProfileInputData(Username, UserDescription);
        inputBoundary.execute(createProfileInputData);
    }
}
