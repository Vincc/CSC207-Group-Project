package interface_adapter.logged_in;
import interface_adapter.ViewManagerModel;
import interface_adapter.cancel.CancelState;
import interface_adapter.cancel.CancelViewModel;
import interface_adapter.createEvent.CreateEventState;
import interface_adapter.createEvent.CreateEventViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.CreateEvent.CreateEventOutputBoundary;
import use_case.CreateEvent.CreateEventOutputData;
import use_case.cancel.CancelOutputBoundary;
import use_case.cancel.CancelOutputData;
import use_case.joinEvent.joinEventOutputBoundary;
import use_case.logOut.LogOutInputBoundary;
import use_case.logOut.LogoOutOutputBoundary;

public class LoggedInPresenter implements CreateEventOutputBoundary, CancelOutputBoundary, joinEventOutputBoundary, LogoOutOutputBoundary {
    private final LoggedInViewModel loggedInViewModel;
    private final CreateEventViewModel createEventViewModel;
    private final CancelViewModel cancelViewModel;

    private final SignupViewModel signupViewModel;
    private ViewManagerModel viewManagerModel;

    public LoggedInPresenter(LoggedInViewModel loggedInViewModel, CreateEventViewModel createEventViewModel, CancelViewModel cancelViewModel,SignupViewModel signupViewModel, ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.createEventViewModel = createEventViewModel;
        this.cancelViewModel = cancelViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
    }
    @Override
    public void prepareSuccessView(CreateEventOutputData user){
        CreateEventState createEventState = createEventViewModel.getState();
        createEventState.setUsername(user.getUsername());
        this.createEventViewModel.setState(createEventState);
        this.createEventViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(createEventViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }


    @Override
    public void prepareSuccessView(CancelOutputData user) {
        CancelState cancelState = cancelViewModel.getState();
        cancelState.setUsername(user.getUsername());
        this.cancelViewModel.setState(cancelState);
        this.cancelViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(cancelViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareJoinEventSuccessView() {
        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView() {
        this.viewManagerModel.setActiveView(signupViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}
