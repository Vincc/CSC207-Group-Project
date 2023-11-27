package src.interface_adapter.createProfile;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateProfileViewModel extends ViewModel {

    private CreateProfileState state = new CreateProfileState();

    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public CreateProfileViewModel() {super("createProfileView");}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }


    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateProfileState getState() {return state;}

    public void setState(CreateProfileState state) {this.state = state;}
}
