package use_case.createEvent;

import use_case.createEvent.createEventOutputData;

public interface createEventOutputBoundery {
    void prepareSuccessView(createEventOutputData user);

    void prepareFailView(String error);
}
