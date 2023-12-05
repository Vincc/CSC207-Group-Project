package app;

import data_access.FileUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.createProfile.CreateProfileController;
import interface_adapter.createProfile.CreateProfilePresenter;
import interface_adapter.createProfile.CreateProfileViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.CreateProfile.CreateProfileInputBoundary;
import use_case.CreateProfile.CreateProfileInteractor;
import use_case.EditProfile.EditProfileInputBoundary;
import use_case.EditProfile.EditProfileInteractor;
import use_case.EditProfile.EditProfileOutputBoundary;
import view.CreateProfileView;

import javax.swing.*;
import java.io.IOException;

public class CreateProfileUseCaseFactory {
    private CreateProfileUseCaseFactory() {}

    public static CreateProfileView create(
            ViewManagerModel viewManagerModel,
            CreateProfileViewModel createProfileViewModel,
            LoggedInViewModel loggedInViewModel,
            FileUserDataAccessObject userDataAccessObject
    )   {
        try {
            CreateProfileController createProfileController =
                    createProfileUseCase(viewManagerModel, createProfileViewModel, loggedInViewModel, userDataAccessObject);
            return new CreateProfileView(createProfileViewModel, createProfileController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }
        return null;
    }

    private static CreateProfileController createProfileUseCase(ViewManagerModel viewManagerModel,
                                                                CreateProfileViewModel createProfileViewModel,
                                                                LoggedInViewModel loggedInViewModel,
                                                                FileUserDataAccessObject userDataAccessObject) throws IOException {
        CreateProfilePresenter createProfilePresenter = new CreateProfilePresenter(viewManagerModel, createProfileViewModel, loggedInViewModel);
        CreateProfileInputBoundary createProfileInputBoundary = new CreateProfileInteractor(createProfilePresenter, userDataAccessObject);
        EditProfileInputBoundary editProfileInputBoundary = new EditProfileInteractor(createProfilePresenter, userDataAccessObject);
        return new CreateProfileController(createProfileInputBoundary, editProfileInputBoundary);
    }

}
