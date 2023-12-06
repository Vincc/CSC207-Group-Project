package use_case.joinEvent;

public class joinEventOutputData {
    private final String username;

    public joinEventOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
