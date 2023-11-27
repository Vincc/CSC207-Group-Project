package src.use_case.CreateEvent;

import use_case.CreateEvent.CreateEventOutputData;

public interface CreateEventOutputBoundary {


    void prepareSuccessView(CreateEventOutputData user);
}
