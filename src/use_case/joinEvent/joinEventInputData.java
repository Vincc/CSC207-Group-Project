package use_case.joinEvent;

public class joinEventInputData {
    final private String username;
    final private String eventName;

    public joinEventInputData(String username, String eventName) {
        this.username = username;
        this.eventName = eventName;
    }

    public String getUsername() {
        return username;
    }

    public String getEventName() {
        return eventName;
    }
}
