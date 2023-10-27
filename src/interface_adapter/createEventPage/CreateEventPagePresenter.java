package interface_adapter.createEventPage;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import use_case.createEventPage.CreateEventPageOutputBoundery;

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
    public void prepareSuccessView() {
        System.out.println("Das");

        this.createEventPageViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(createEventPageViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}
