package use_case.CreateEvent;

import use_case.cancel.CancelOutputData;
import use_case.login.LoginOutputData;

public interface CreateEventOutputBoundary {


    void prepareSuccessView(CreateEventOutputData user);
}
