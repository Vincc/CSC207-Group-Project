package use_case.CreateProfile;

public class CreateProfileOutputData {
    private final String username;
    private final String userDescription;

    public CreateProfileOutputData(String username, String userDescription) {
        this.username = username;
        this.userDescription = userDescription;
    }

    public String getUsername() {
        return username;
    }
    public String getUserDescription() {return userDescription; }
}
