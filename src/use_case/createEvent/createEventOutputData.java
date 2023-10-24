package use_case.createEvent;

public class createEventOutputData {
    private final String eventName;
    private Boolean usecaseFailed;

    public createEventOutputData(String eventName, Boolean usecaseFailed) {
        this.eventName = eventName;
        this.usecaseFailed = usecaseFailed;
    }

    public String getEventName() {
        return eventName;
    }
}
