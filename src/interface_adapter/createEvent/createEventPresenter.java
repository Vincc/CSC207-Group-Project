package interface_adapter.createEvent;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;

import interface_adapter.logged_in.LoggedInViewModel;

import interface_adapter.login.LoginState;
import use_case.makeEvent.makeEventOutputBoundary;
import use_case.makeEvent.makeEventOutputData;

import javax.swing.text.View;

public class  createEventPresenter implements makeEventOutputBoundary{
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;
    public createEventPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel){
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
