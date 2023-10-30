package use_case.createEventPage;

public class CreateEventPageOutputData {
    private final String username;
    private boolean useCaseFailed;

    public CreateEventPageOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

}
