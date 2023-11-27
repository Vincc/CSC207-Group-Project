package src.interface_adapter.signup;

import use_case.cancel.CancelInputBoundary;
import use_case.cancel.CancelInputData;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class SignupController {

    final SignupInputBoundary userSignupUseCaseInteractor;
    final CancelInputBoundary cancelInteractor;
    public SignupController(SignupInputBoundary userSignupUseCaseInteractor, CancelInputBoundary cancelInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
        this.cancelInteractor = cancelInteractor;
    }

    public void execute(String username, String password1, String password2) {
        SignupInputData signupInputData = new SignupInputData(
                username, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
    public void executeCancel(String username){
        CancelInputData cancelInputData = new CancelInputData(username);
        cancelInteractor.execute(cancelInputData);
    }
}
