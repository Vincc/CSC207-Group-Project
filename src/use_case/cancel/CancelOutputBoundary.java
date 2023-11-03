package use_case.cancel;

import use_case.CreateEvent.CreateEventOutputData;

public interface CancelOutputBoundary {
    void prepareSuccessView(CancelOutputData user);

}
