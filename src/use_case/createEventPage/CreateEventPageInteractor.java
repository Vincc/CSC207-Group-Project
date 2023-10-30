package use_case.createEventPage;


import entity.User;
import use_case.login.LoginUserDataAccessInterface;

public class CreateEventPageInteractor implements CreateEventPageInputBoundary {
    final CreateEventPageOutputBoundery createEventPagePresenter;
    final LoginUserDataAccessInterface userDataAccessObject;
    public CreateEventPageInteractor(CreateEventPageOutputBoundery createEventPageOutputBoundery, LoginUserDataAccessInterface userDataAccessInterface){
        this.createEventPagePresenter =  createEventPageOutputBoundery;
        this.userDataAccessObject = userDataAccessInterface;
    }

    @Override
    public void execute(CreateEventPageInputData createEventPageInputData) {
        User user = userDataAccessObject.get(createEventPageInputData.getUsername());
        CreateEventPageOutputData outputUser = new CreateEventPageOutputData(user.getName(),false);
        createEventPagePresenter.prepareSuccessView(outputUser);
    }
}
