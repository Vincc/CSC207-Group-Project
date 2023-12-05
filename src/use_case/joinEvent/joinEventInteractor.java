package use_case.joinEvent;


public class joinEventInteractor implements joinEventInputBoundary {
    final  joinEventDataAccessInterface joinEventDataAccessInterface;
    final joinEventUserDataAccessInterface joinEventUserDataAccessInterface;
    final joinEventOutputBoundary joinEventPresenter;


    public joinEventInteractor(joinEventDataAccessInterface joinEventDataAccessInterface, joinEventUserDataAccessInterface joinEventUserDataAccessInterface, joinEventOutputBoundary joinEventPresenter) {
        this.joinEventDataAccessInterface = joinEventDataAccessInterface;
        this.joinEventUserDataAccessInterface = joinEventUserDataAccessInterface;
        this.joinEventPresenter = joinEventPresenter;
    }


    @Override
    public void execute(joinEventInputData joinEventInputData) {
        joinEventDataAccessInterface.addParticipant(joinEventInputData.getEventName(), joinEventInputData.getUsername());
        joinEventUserDataAccessInterface.addEvent(joinEventInputData.getEventName(), joinEventInputData.getUsername());

        joinEventPresenter.prepareJoinEventSuccessView();
    }
}
