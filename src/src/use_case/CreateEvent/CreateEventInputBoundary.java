package src.use_case.CreateEvent;

import use_case.CreateEvent.CreateEventInputData;

public interface CreateEventInputBoundary {
    void execute(CreateEventInputData createEventInputData);
}
