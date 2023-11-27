package src.use_case.CreateEvent;

public class CreateEventOutputData {
    private final String username;

    public CreateEventOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
