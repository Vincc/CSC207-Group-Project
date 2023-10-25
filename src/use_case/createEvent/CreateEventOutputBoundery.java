package use_case.createEvent;

public interface CreateEventOutputBoundery {
    void prepareSuccessView(CreateEventOutputData user);

    void prepareFailView(String error);
}
