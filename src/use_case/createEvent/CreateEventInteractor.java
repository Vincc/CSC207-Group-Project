package use_case.CreateEvent;

import entity.User;
import use_case.login.LoginUserDataAccessInterface;

public class CreateEventInteractor implements CreateEventInputBoundary{
    final CreateEventOutputBoundary createEventPagePresenter;
    final LoginUserDataAccessInterface userDataAccessObject;
    public CreateEventInteractor(CreateEventOutputBoundary createEventOutputBoundery, LoginUserDataAccessInterface userDataAccessInterface){
        this.createEventPagePresenter =  createEventOutputBoundery;
        this.userDataAccessObject = userDataAccessInterface;
    }

    @Override
    public void execute(CreateEventInputData createEventPageInputData) {
        User user = userDataAccessObject.get(createEventPageInputData.getUsername());
        CreateEventOutputData outputUser = new CreateEventOutputData(user.getName());
        createEventPagePresenter.prepareSuccessView(outputUser);
    }
}
