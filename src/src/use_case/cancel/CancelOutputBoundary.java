package src.use_case.cancel;

import use_case.cancel.CancelOutputData;

public interface CancelOutputBoundary {
    void prepareSuccessView(CancelOutputData user);

}
