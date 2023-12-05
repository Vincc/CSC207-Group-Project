package use_case.EditProfile;

import data_access.FileUserDataAccessObject;
import entity.User;
import use_case.CreateProfile.CreateProfileOutputBoundary;
import use_case.CreateProfile.CreateProfileOutputData;

public class EditProfileInteractor implements  EditProfileInputBoundary{
    final CreateProfileOutputBoundary createProfilePresenter;
    final FileUserDataAccessObject userDataAccessObject;

    public EditProfileInteractor(CreateProfileOutputBoundary createProfilePresenter, FileUserDataAccessObject userDataAccessObject) {
        this.createProfilePresenter = createProfilePresenter;
        this.userDataAccessObject = userDataAccessObject;
    }
    public void execute(EditProfileInputData editProfileInputData) {
        User user = userDataAccessObject.get(editProfileInputData.getUsername());
        user.setUserDescription(editProfileInputData.getUserDescription());
        userDataAccessObject.save(user);
        CreateProfileOutputData outputData = new CreateProfileOutputData(user.getName(), user.getUserDescription());
        createProfilePresenter.prepareSuccessView(outputData);
    }
}