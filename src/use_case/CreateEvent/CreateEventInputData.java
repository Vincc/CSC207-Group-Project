package use_case.CreateEvent;

public class CreateEventInputData {
    final private String username;

    public CreateEventInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
