package use_case.cancel;

import use_case.CreateEvent.CreateEventInputData;

public interface CancelInputBoundary {
    void execute(CancelInputData cancelInputData);

}
