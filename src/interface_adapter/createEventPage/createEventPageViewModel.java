package interface_adapter.createEventPage;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignupState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class createEventPageViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Event details";
        public static final String EVENT_NAME_LABEL = "Choose event name";
    public static final String EVENT_PLACE_LABEL = "Choose event place";
    public static final String EVENT_TIME_LABEL = "Choose event time";
    public static final String EVENT_LEVEL_LABEL = "Choose event level";
    public static final String CHOOSE_MAX_ATTENDANCE_LABEL = "Choose number of players";
    public static final String CREATE_EVENT_LABEL = "create";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private createEventPageState state = new createEventPageState();
    public createEventPageViewModel() {super("createEventView");}


    public void setState(createEventPageState state) {
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

    public createEventPageState getState() {
        return state;
    }
}