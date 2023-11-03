package interface_adapter.cancel;

import interface_adapter.logged_in.LoggedInState;

public class CancelState {

    private String username = "";

    public CancelState(CancelState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public CancelState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
