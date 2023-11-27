package src.use_case.login;

import use_case.login.LoginInputData;

public interface LoginInputBoundary {
    void execute(LoginInputData loginInputData);
}
