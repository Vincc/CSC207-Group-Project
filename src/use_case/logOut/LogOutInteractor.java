package use_case.logOut;

import data_access.FileUserDataAccessObject;
import entity.User;


public class LogOutInteractor implements LogOutInputBoundary {
    final LogoOutOutputBoundary logoOutOutputBoundary;


    public LogOutInteractor(LogoOutOutputBoundary logoOutOutputBoundary) {
        this.logoOutOutputBoundary = logoOutOutputBoundary;

    }

    @Override
    public void execute() {
        logoOutOutputBoundary.prepareSuccessView();

    }
}
