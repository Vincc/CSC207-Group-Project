package use_case.cancel;

import entity.User;
import use_case.CreateEvent.CreateEventOutputData;
import use_case.login.LoginUserDataAccessInterface;


public class CancelInteractor implements CancelInputBoundary{
    final CancelOutputBoundary cancelOutputBoundary;
    final LoginUserDataAccessInterface userDataAccessObject;


    public CancelInteractor(CancelOutputBoundary cancelOutputBoundary, LoginUserDataAccessInterface userDataAccessObject) {
        this.cancelOutputBoundary = cancelOutputBoundary;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(CancelInputData cancelInputData) {
        User user = userDataAccessObject.get(cancelInputData.getUsername());
        CancelOutputData outputData = new CancelOutputData(user.getName());
        cancelOutputBoundary.prepareSuccessView(outputData);


    }
}
