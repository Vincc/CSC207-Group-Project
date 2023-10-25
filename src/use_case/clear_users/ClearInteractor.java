package use_case.clear_users;

// TODO Complete me

import entity.UserFactory;

public class ClearInteractor implements ClearInputBoundary {
    final ClearUserDataAccessInterface clearDatAccessObject;
    final ClearOutputBoundary userPresenter;
    final UserFactory userFactory;

    public ClearInteractor(ClearUserDataAccessInterface clearDatAccessObject, ClearOutputBoundary userPresenter, UserFactory userFactory) {
        this.clearDatAccessObject = clearDatAccessObject;
        this.userPresenter = userPresenter;
        this.userFactory = userFactory;
    }

    public void execute(){

    }
}
