package interface_adapter.createProfile;

import use_case.CreateProfile.CreateProfileOutputBoundary;
import use_case.CreateProfile.CreateProfileOutputData;

public class CreateProfileState {
    private String username = "";
    private String userDescription = "";
    public CreateProfileState() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    //TODO
}
