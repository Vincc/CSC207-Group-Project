package src.use_case.cancel;

import data_access.FileUserDataAccessObject;
import entity.User;
import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInputData;
import use_case.cancel.CancelOutputBoundary;
import use_case.cancel.CancelOutputData;


public class CancelInteractor implements CancelInputBoundary {
    final use_case.cancel.CancelOutputBoundary cancelOutputBoundary;
    final FileUserDataAccessObject userDataAccessObject;


    public CancelInteractor(CancelOutputBoundary cancelOutputBoundary, FileUserDataAccessObject userDataAccessObject) {
        this.cancelOutputBoundary = cancelOutputBoundary;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(CancelInputData cancelInputData) {
        User user = userDataAccessObject.get(cancelInputData.getUsername());
        use_case.cancel.CancelOutputData outputData = new CancelOutputData(user.getName());
        cancelOutputBoundary.prepareSuccessView(outputData);


    }
}
