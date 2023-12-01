package view;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.optionalusertools.TimeChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.zinternaltools.TimeChangeEvent;
import com.opencagedata.jopencage.JOpenCageGeocoder;
import com.opencagedata.jopencage.model.JOpenCageForwardRequest;
import com.opencagedata.jopencage.model.JOpenCageResponse;
import com.opencagedata.jopencage.model.JOpenCageResult;
import interface_adapter.createEvent.CreateEventViewModel;
import interface_adapter.createEvent.CreateEventState;
import interface_adapter.createEvent.CreateEventController;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import interface_adapter.AutoSuggestor;

public class CreateEventView extends JPanel implements ActionListener, PropertyChangeListener {

    JLabel username;
    Window container;

    public final String viewName = "createEventView";
    private final CreateEventViewModel createEventViewModel;
    private final CreateEventController createEventController;
    final JTextField eventPlaceInputField = new JTextField(15);
    final DatePicker eventDateInputField = new DatePicker();

    final TimePicker eventTimeInputField = new TimePicker();
    final DatePicker eventEndDateInputField = new DatePicker();

    final TimePicker eventEndTimeInputField = new TimePicker();
    final JTextField eventNameInputField = new JTextField(15);
    final JTextField eventAttendanceInputField = new JTextField(15);
    final JButton create;
    final JButton cancel;
    final JComboBox<String> eventLevelComboBox; // JComboBox for event level
    public CreateEventView(CreateEventViewModel createEventViewModel, CreateEventController createEventController, Window mainWindow) {
        this.createEventController = createEventController;
        this.createEventViewModel = createEventViewModel;
        this.createEventViewModel.addPropertyChangeListener(this);
        this.container = mainWindow;

        JLabel title = new JLabel("Create your event, my friend");
        CreateEventState currentState = createEventViewModel.getState();
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
                                    currentState.getUsername(),
                                    currentState.getDiscription(),
                                    currentState.getPlace(),
                                    currentState.getDate(),
                                    currentState.getEndDate(),
                                    currentState.getTime(),
                                    currentState.getEndTime(),
                                    currentState.getSporttype(),
                                    currentState.getLvl(),
                                    currentState.getMaxplayers()
                            );
                        }
                    }
                }
        );
        eventDateInputField.addDateChangeListener(
                new DateChangeListener() {
                    @Override
                    public void dateChanged(DateChangeEvent dateChangeEvent) {
                        CreateEventState currentState = createEventViewModel.getState();
                        LocalDate date = eventDateInputField.getDate();
                        currentState.setDate(date);
                        createEventViewModel.setState(currentState);
                    }
                });
        eventEndDateInputField.addDateChangeListener(
                new DateChangeListener() {
                    @Override
                    public void dateChanged(DateChangeEvent dateChangeEvent) {
                        CreateEventState currentState = createEventViewModel.getState();
                        LocalDate date = eventEndDateInputField.getDate();
                        currentState.setEndDate(date);
                        createEventViewModel.setState(currentState);
                    }
                });
        eventTimeInputField.addTimeChangeListener(
                new TimeChangeListener() {
                    @Override
                    public void timeChanged(TimeChangeEvent timeChangeEvent) {
                        CreateEventState currentState = createEventViewModel.getState();
                        LocalTime time = eventTimeInputField.getTime();
                        currentState.setTime(time);
                        createEventViewModel.setState(currentState);
                    }
                });
        eventEndTimeInputField.addTimeChangeListener(
                new TimeChangeListener() {
                    @Override
                    public void timeChanged(TimeChangeEvent timeChangeEvent) {
                        CreateEventState currentState = createEventViewModel.getState();
                        LocalTime time = eventEndTimeInputField.getTime();
                        currentState.setEndTime(time);
                        createEventViewModel.setState(currentState);
                    }
                });
        eventNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateEventState currentState = createEventViewModel.getState();
                        String text = eventNameInputField.getText() + e.getKeyChar();
                        currentState.setDiscription(text);
                        createEventViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        eventPlaceInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateEventState currentState = createEventViewModel.getState();
                        String text = eventPlaceInputField.getText() + e.getKeyChar();
                        AutoSuggestor autoSuggestor = new AutoSuggestor(eventPlaceInputField, container, null, Color.WHITE.brighter(), Color.BLUE, Color.RED, 1.0f);
                        ArrayList<String> words = new ArrayList<>();
                        if (text.length() > 5) {
                            JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("60cdd0cbd0ff48ad84bcdd75f39d7c01");
                            JOpenCageForwardRequest request = new JOpenCageForwardRequest(text);
                            request.setRestrictToCountryCode("ca");
                            // request.setBounds(-79.0, 43.0, -80.0, 44.0);

                            JOpenCageResponse response = jOpenCageGeocoder.forward(request);
                            while (words.size() < 5) {
                                for (JOpenCageResult i : response.getResults()) {
                                    words.add(i.getFormatted());
                                }
                            }
                            autoSuggestor.setDictionary(words);
                            autoSuggestor.wordTyped(text);
                        }
                        currentState.setPlace(text);
                        createEventViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        eventAttendanceInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateEventState currentState = createEventViewModel.getState();
                        String text = eventAttendanceInputField.getText() + e.getKeyChar();
                        currentState.setMaxplayers(Integer.parseInt(text));
                        createEventViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        eventLevelComboBox.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CreateEventState currentState = createEventViewModel.getState();
                        String lvl = (String) eventLevelComboBox.getSelectedItem();
                        currentState.setLvl(lvl);
                        createEventViewModel.setState(currentState);
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(username);

        this.add(eventDateInputField);
        this.add(eventEndDateInputField);
        this.add(eventTimeInputField);
        this.add(eventEndTimeInputField);
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
