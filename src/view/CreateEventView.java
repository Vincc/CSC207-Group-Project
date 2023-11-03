package view;

import interface_adapter.createEvent.CreateEventViewModel;
import interface_adapter.createEvent.CreateEventState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateEventView extends JPanel implements ActionListener, PropertyChangeListener {

    JLabel username;


    public final String viewName = "createEventView";
    private final CreateEventViewModel createEventViewModel;
    final JTextField eventPlaceInputField = new JTextField(15);
    final JTextField eventTimeInputField = new JTextField(15);
    final JTextField eventNameInputField = new JTextField(15);
    final JTextField eventAttendanceInputField = new JTextField(15);

    final JButton create;
    final JButton cancel;
    final JComboBox<String> eventLevelComboBox; // JComboBox for event level

    public CreateEventView(CreateEventViewModel createEventViewModel) {
        this.createEventViewModel = createEventViewModel;
        this.createEventViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Create your event, my friend");
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel eventNameInfo = new LabelTextPanel(
                new JLabel("Event Name"), eventNameInputField);
        LabelTextPanel eventTimeInfo = new LabelTextPanel(
                new JLabel("Event Time"), eventTimeInputField);
        LabelTextPanel eventPlaceInfo = new LabelTextPanel(
                new JLabel("Event Place"), eventPlaceInputField);
        LabelTextPanel eventAttendanceInfo = new LabelTextPanel(
                new JLabel("Max Attendance"), eventAttendanceInputField);

        eventLevelComboBox = new JComboBox<>();
        eventLevelComboBox.addItem("Choose Level of Play");
        eventLevelComboBox.addItem("Pro");
        eventLevelComboBox.addItem("Mid");
        eventLevelComboBox.addItem("Beginner");

        JPanel buttons = new JPanel();
        create = new JButton(createEventViewModel.CREATE_EVENT_LABEL);
        buttons.add(create);
        cancel = new JButton(createEventViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(username);
        this.add(eventTimeInfo);
        this.add(eventNameInfo);
        this.add(eventPlaceInfo);
        this.add(eventAttendanceInfo);
        this.add(eventLevelComboBox);
        this.add(buttons);

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
