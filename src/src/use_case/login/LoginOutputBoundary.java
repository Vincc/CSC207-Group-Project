package src.use_case.login;

import use_case.login.LoginOutputData;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData user);

    void prepareFailView(String error);
}