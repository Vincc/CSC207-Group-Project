package interface_adapter.cancel;

import interface_adapter.ViewModel;
import interface_adapter.createEvent.CreateEventState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CancelViewModel extends ViewModel {
    private CancelState state = new CancelState();

    public CancelViewModel() {super("cancelView");}


    public void setState(CancelState state) {
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

    public CancelState getState() {
        return state;
    }

}
