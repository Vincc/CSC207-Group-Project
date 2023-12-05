package interface_adapter.createProfile;

import interface_adapter.ViewManagerModel;
import use_case.CreateProfile.CreateProfileOutputBoundary;
import use_case.CreateProfile.CreateProfileOutputData;

public class CreateProfilePresenter implements CreateProfileOutputBoundary {
    private final CreateProfileViewModel createProfileViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateProfilePresenter(ViewManagerModel viewManagerModel, CreateProfileViewModel createProfileViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createProfileViewModel = createProfileViewModel;
    }

    @Override
    public void prepareSuccessView(CreateProfileOutputData profileOutputData) {
        CreateProfileState profileState = createProfileViewModel.getState();
        profileState.setUsername(profileOutputData.getUsername());
        profileState.setUserDescription(profileOutputData.getUserDescription());
        this.createProfileViewModel.setState(profileState);

        createProfileViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(createProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
