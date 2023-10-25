package use_case.createEvent;

public class CreateEventOutputData {
    private final String eventName;
    private Boolean usecaseFailed;

    public CreateEventOutputData(String eventName, Boolean usecaseFailed) {
        this.eventName = eventName;
        this.usecaseFailed = usecaseFailed;
    }

    public String getEventName() {
        return eventName;
    }
}
