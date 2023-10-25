package use_case.createEvent;

public interface CreateEventOutputBoundery {
    void prepareSuccessView(createEventOutputData user);

    void prepareFailView(String error);
}
