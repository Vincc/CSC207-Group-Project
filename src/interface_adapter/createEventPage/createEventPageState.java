package interface_adapter.createEventPage;

import interface_adapter.logged_in.LoggedInState;

public class createEventPageState {

    private String username = "";

    public createEventPageState(createEventPageState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public createEventPageState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }


}
