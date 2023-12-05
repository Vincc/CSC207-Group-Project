package interface_adapter.createEvent;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;

import interface_adapter.logged_in.LoggedInViewModel;

import use_case.makeEvent.makeEventOutputBoundary;

public class CreateEventPresenter implements makeEventOutputBoundary{
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;
    public CreateEventPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel){
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }
    @Override
    public void prepareMakeEventSuccessView() {
        LoggedInState loggedInState = loggedInViewModel.getState();
        this.loggedInViewModel.setState(loggedInState);

        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareMakeEventFailView(String error) {

    }


}
