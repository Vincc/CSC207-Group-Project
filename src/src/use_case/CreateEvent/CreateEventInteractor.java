package src.use_case.CreateEvent;

import entity.User;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInputData;
import use_case.CreateEvent.CreateEventOutputBoundary;
import use_case.CreateEvent.CreateEventOutputData;
import use_case.login.LoginUserDataAccessInterface;

public class CreateEventInteractor implements CreateEventInputBoundary {
    final use_case.CreateEvent.CreateEventOutputBoundary createEventPagePresenter;
    final LoginUserDataAccessInterface userDataAccessObject;
    public CreateEventInteractor(CreateEventOutputBoundary createEventOutputBoundery, LoginUserDataAccessInterface userDataAccessInterface){
        this.createEventPagePresenter =  createEventOutputBoundery;
        this.userDataAccessObject = userDataAccessInterface;
    }

    @Override
    public void execute(CreateEventInputData createEventPageInputData) {
        User user = userDataAccessObject.get(createEventPageInputData.getUsername());
        use_case.CreateEvent.CreateEventOutputData outputUser = new CreateEventOutputData(user.getName());
        createEventPagePresenter.prepareSuccessView(outputUser);
    }
}
