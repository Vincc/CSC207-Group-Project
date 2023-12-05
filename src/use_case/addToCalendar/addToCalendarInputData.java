package use_case.addToCalendar;

public class addToCalendarInputData {
    final private String username;
    final private String eventName;

    public addToCalendarInputData(String eventName,String username) {
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
