package use_case.CreateProfile;

public class CreateProfileInputData {
    final private String username;
    final private String userDescription;

    public CreateProfileInputData(String username) {
        this.username = username;
        this.userDescription = "";
    }

    public CreateProfileInputData(String username, String UserDescription) {
        this.username = username;
        this.userDescription = UserDescription;
    }

    public String getUsername() {
        return username;
    }
    public String getUserDescription() {
        return userDescription;
    }
}
