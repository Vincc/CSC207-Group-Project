package view;

import interface_adapter.createEvent.CreateEventViewModel;
import interface_adapter.createEvent.CreateEventState;
import interface_adapter.login.LoginState;
import interface_adapter.createEvent.CreateEventController;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import javax.swing.*;
import javax.swing.text.LabelView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateEventView extends JPanel implements ActionListener, PropertyChangeListener {

    JLabel username;


    public final String viewName = "createEventView";
    private final CreateEventViewModel createEventViewModel;
    private final CreateEventController createEventController;
    final JTextField eventPlaceInputField = new JTextField(15);
    final DatePicker eventDateInputField = new DatePicker();
    final TimePicker eventTimeInputField = new TimePicker();
    final JTextField eventNameInputField = new JTextField(15);
    final JTextField eventAttendanceInputField = new JTextField(15);
    final JButton create;
    final JButton cancel;
    final JComboBox<String> eventLevelComboBox; // JComboBox for event level
    public CreateEventView(CreateEventViewModel createEventViewModel, CreateEventController createEventController) {
        this.createEventController = createEventController;
        this.createEventViewModel = createEventViewModel;
        this.createEventViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Create your event, my friend");
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel eventNameInfo = new LabelTextPanel(
                new JLabel("Event Name"), eventNameInputField);


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

        create.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(create)) {
                            CreateEventState currentState = createEventViewModel.getState();

                            createEventController.executeMakeEvent(
                                    currentState.getDiscription(),
                                    currentState.getPlace(),
                                    currentState.getDate(),
                                    currentState.getTime(),
                                    currentState.getSporttype(),
                                    currentState.getLvl(),
                                    currentState.getMaxplayers()
                            );
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(username);
        this.add(eventDateInputField);
        this.add(eventTimeInputField);
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
