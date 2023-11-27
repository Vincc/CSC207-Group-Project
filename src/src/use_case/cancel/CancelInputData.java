package src.use_case.cancel;

public class CancelInputData {
    final private String username;

    public CancelInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
