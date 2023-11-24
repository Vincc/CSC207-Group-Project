package interface_adapter.login;

import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInputData;
import use_case.login.LoginInputData;
import use_case.login.LoginInputBoundary;

public class LoginController {

    final LoginInputBoundary loginUseCaseInteractor;
    final CancelInputBoundary cancelInteractor;
    public LoginController(LoginInputBoundary loginUseCaseInteractor, CancelInputBoundary cancelInteractor) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
        this.cancelInteractor = cancelInteractor;
    }


    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
    public void executeCancel(String username){
        CancelInputData cancelInputData = new CancelInputData(username);
        cancelInteractor.execute(cancelInputData);
    }
}
