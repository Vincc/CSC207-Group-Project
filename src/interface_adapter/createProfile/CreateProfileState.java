package interface_adapter.createProfile;

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
