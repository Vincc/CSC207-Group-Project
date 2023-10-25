package interface_adapter.clear_users;

import use_case.clear_users.ClearInputBoundary;

// TODO Complete me
public class ClearController {
    final ClearInputBoundary clearUseCseInteractor;
    public ClearController(ClearInputBoundary clearUseCseInteractor){
        this.clearUseCseInteractor = clearUseCseInteractor;
    };
    public void execute(){
        clearUseCseInteractor.execute();
    }
}
