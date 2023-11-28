package use_case.joinEvent;

public interface joinEventOutputBoundary {

    void prepareJoinEventSuccessView();
    void prepareJoinEventFailView(String error);
}
