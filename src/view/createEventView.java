package view;

import interface_adapter.createEventPage.createEventPageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class createEventView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "createEventView";
    private final createEventPageViewModel createEventPageViewModel;

    final JTextField eventPlaceInputField = new JTextField(15);
    final JTextField eventTimeInputField = new JTextField(15);
    final JTextField eventNameInputField = new JTextField(15);
    final JTextField eventAttendanceInputField = new JTextField(15);

    final JButton create;
    final JButton cancel;
    final JComboBox<String> eventLevelComboBox; // JComboBox for event level

    public createEventView(createEventPageViewModel createEventPageViewModel) {
        this.createEventPageViewModel = createEventPageViewModel;
        this.createEventPageViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Create your event, my friend");
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
        create = new JButton(createEventPageViewModel.CREATE_EVENT_LABEL);
        buttons.add(create);
        cancel = new JButton(createEventPageViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
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
    }
}
