package interface_adapter.logged_in;
import interface_adapter.ViewManagerModel;
import interface_adapter.cancel.CancelState;
import interface_adapter.cancel.CancelViewModel;
import interface_adapter.createEvent.CreateEventState;
import interface_adapter.createEvent.CreateEventViewModel;
import use_case.CreateEvent.CreateEventOutputBoundary;
import use_case.CreateEvent.CreateEventOutputData;
import use_case.cancel.CancelOutputBoundary;
import use_case.cancel.CancelOutputData;

public class LoggedInPresenter implements CreateEventOutputBoundary, CancelOutputBoundary {
    private final LoggedInViewModel loggedInViewModel;
    private final CreateEventViewModel createEventViewModel;
    private final CancelViewModel cancelViewModel;
    private ViewManagerModel viewManagerModel;

    public LoggedInPresenter(LoggedInViewModel loggedInViewModel, CreateEventViewModel createEventViewModel, CancelViewModel cancelViewModel, ViewManagerModel viewManagerModel) {
        this.loggedInViewModel = loggedInViewModel;
        this.createEventViewModel = createEventViewModel;
        this.cancelViewModel = cancelViewModel;
        this.viewManagerModel = viewManagerModel;
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
}
