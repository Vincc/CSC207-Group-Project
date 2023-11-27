package src.interface_adapter.createEvent;

import interface_adapter.ViewModel;
import interface_adapter.createEvent.CreateEventState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateEventViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Event details";
    public static final String EVENT_NAME_LABEL = "Choose event name";
    public static final String EVENT_PLACE_LABEL = "Choose event place";
    public static final String EVENT_DATE_LABEL = "Choose event date";
    public static final String EVENT_TIME_LABEL = "Choose event time";
    public static final String EVENT_LEVEL_LABEL = "Choose event level";
    public static final String CHOOSE_MAX_ATTENDANCE_LABEL = "Choose number of players";
    public static final String CREATE_EVENT_LABEL = "create";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private interface_adapter.createEvent.CreateEventState state = new interface_adapter.createEvent.CreateEventState();
    public CreateEventViewModel() {super("createEventView");}


    public void setState(interface_adapter.createEvent.CreateEventState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateEventState getState() {
        return state;
    }
}