package use_case.EditProfile;

public class EditProfileInputData {
    final private String username;
    final private String userDescription;

    public EditProfileInputData(String username, String userDescription) {
        this.username = username;
        this.userDescription = userDescription;
    }

    public String getUsername() {
        return username;
    }
    public String getUserDescription() {
        return userDescription;
    }
}
