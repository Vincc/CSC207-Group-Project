package view;

import interface_adapter.cancel.CancelViewModel;
import interface_adapter.createEvent.CreateEventState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CancelView extends JPanel implements ActionListener, PropertyChangeListener {
    JLabel username;

    public final String viewName = "createEventView";
    private final CancelViewModel cancelViewModel;

    public CancelView(CancelViewModel cancelViewModel) {
        this.cancelViewModel = cancelViewModel;
        JLabel title = new JLabel("thank you for using our app");
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(username);
    }
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes
        CreateEventState state = (CreateEventState) evt.getNewValue();
        username.setText(state.getUsername());
    }
}
