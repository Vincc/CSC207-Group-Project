package use_case.viewEvent;
import entity.User;

public class viewEventOutputData {
    private final User user;
    public viewEventOutputData(User user){
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
