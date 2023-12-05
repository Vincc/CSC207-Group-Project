package interface_adapter.createProfile;

import interface_adapter.ViewManagerModel;
import interface_adapter.createEvent.CreateEventState;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.CreateProfile.CreateProfileOutputBoundary;
import use_case.CreateProfile.CreateProfileOutputData;

import java.time.LocalTime;

public class CreateProfilePresenter implements CreateProfileOutputBoundary {
    private final CreateProfileViewModel createProfileViewModel;
    private ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public CreateProfilePresenter(ViewManagerModel viewManagerModel, CreateProfileViewModel createProfileViewModel,
                                  LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createProfileViewModel = createProfileViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(CreateProfileOutputData profileOutputData) {
        CreateProfileState createProfileState = createProfileViewModel.getState();
        this.createProfileViewModel.setState(createProfileState);
        this.createProfileViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(createProfileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
    public void prepareSuccessView() {
        LoggedInState loggedInState = loggedInViewModel.getState();
        this.loggedInViewModel.setState(loggedInState);

        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}