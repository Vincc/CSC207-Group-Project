package interface_adapter.createEventPage;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import use_case.createEventPage.CreateEventPageOutputBoundery;
import use_case.createEventPage.CreateEventPageOutputData;

public class CreateEventPagePresenter implements CreateEventPageOutputBoundery{

    private final createEventPageViewModel createEventPageViewModel;
    private ViewManagerModel viewManagerModel;

    public CreateEventPagePresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          createEventPageViewModel createEventPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createEventPageViewModel = createEventPageViewModel;
    }
    @Override
    public void prepareSuccessView(CreateEventPageOutputData user) {
        createEventPageState createEventPageState = createEventPageViewModel.getState();
        createEventPageState.setUsername(user.getUsername());
        this.createEventPageViewModel.setState(createEventPageState);
        this.createEventPageViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(createEventPageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }


}
