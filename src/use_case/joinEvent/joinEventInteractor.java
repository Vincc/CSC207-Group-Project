package use_case.joinEvent;

public class joinEventInteractor implements joinEventInputBoundary {
    final private joinEventDataAccessInterface joinEventDataAccessInterface;


    public joinEventInteractor(use_case.joinEvent.joinEventDataAccessInterface joinEventDataAccessInterface) {
        this.joinEventDataAccessInterface = joinEventDataAccessInterface;
    }


    @Override
    public void execute(joinEventInputData joinEventInputData) {
        joinEventDataAccessInterface.addParticipant(joinEventInputData.getEventName(), joinEventInputData.getUsername());
    }
}
