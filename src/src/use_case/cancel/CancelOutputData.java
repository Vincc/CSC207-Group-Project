package src.use_case.cancel;

public class CancelOutputData {
    private final String username;

    public CancelOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
