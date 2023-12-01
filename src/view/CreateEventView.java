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
    final JTextField eventPlaceInputField = new JTextField(20);
    final DatePicker eventDateInputField = new DatePicker();


    final TimePicker eventTimeInputField = new TimePicker();
    final DatePicker eventEndDateInputField = new DatePicker();

    final TimePicker eventEndTimeInputField = new TimePicker();
    final JTextField eventNameInputField = new JTextField(20);
    final JTextField eventAttendanceInputField = new JTextField(20);
    final JButton create;
    final JButton cancel;
    final JComboBox<String> eventLevelComboBox; // JComboBox for event level
    public CreateEventView(CreateEventViewModel createEventViewModel, CreateEventController createEventController, Window mainWindow) {
        this.createEventController = createEventController;
        this.createEventViewModel = createEventViewModel;
        this.createEventViewModel.addPropertyChangeListener(this);
        this.container = mainWindow;

        JLabel title = new JLabel("Create your event, my friend:");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(41, 128, 185));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        CreateEventState currentState = createEventViewModel.getState();
        username = new JLabel();


        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        username.setFont(new Font("Arial", Font.BOLD, 20));
        username.setForeground(new Color(200, 200, 200));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel eventNameLabel = new JLabel("Event Name");
        eventNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        LabelTextPanel eventNameInfo = new LabelTextPanel(eventNameLabel, eventNameInputField);



        JLabel eventPlaceLabel = new JLabel("Event Place");
        eventPlaceLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel eventAttendanceLabel = new JLabel("Max Attendance");
        eventAttendanceLabel.setFont(new Font("Arial", Font.BOLD, 18));

        LabelTextPanel eventPlaceInfo = new LabelTextPanel(eventPlaceLabel, eventPlaceInputField);
        LabelTextPanel eventAttendanceInfo = new LabelTextPanel(eventAttendanceLabel, eventAttendanceInputField);

        eventLevelComboBox = new JComboBox<>();
        eventLevelComboBox.setFont(new Font("Arial", Font.BOLD, 18));
        eventLevelComboBox.addItem("Choose Level of Play");
        eventLevelComboBox.addItem("Pro");
        eventLevelComboBox.addItem("Mid");
        eventLevelComboBox.addItem("Beginner");

        JPanel buttons = new JPanel();
        create = new JButton(createEventViewModel.CREATE_EVENT_LABEL);
        create.setBackground(new Color(39, 174, 96));
        create.setForeground(Color.WHITE);
        create.setFont(new Font("Arial", Font.PLAIN, 18));
        buttons.add(create);
        cancel = new JButton(createEventViewModel.CANCEL_BUTTON_LABEL);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Arial", Font.PLAIN, 18));
        buttons.add(cancel);

        JLabel eventDateLabel = new JLabel("Select Event Start Date");
        eventDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventDateLabel.setFont(new Font("Arial", Font.BOLD, 18));
        eventDateLabel.setForeground(Color.BLACK);

        JLabel eventEndDateLabel = new JLabel("Select Event End Date");
        eventEndDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventEndDateLabel.setFont(new Font("Arial", Font.BOLD, 18));
        eventEndDateLabel.setForeground(Color.BLACK);

        JLabel eventTimeLabel = new JLabel("Select Event Start Time");
        eventTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventTimeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        eventTimeLabel.setForeground(Color.BLACK);

        JLabel eventEndTimeLabel = new JLabel("Select Event End Time");
        eventEndTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventEndTimeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        eventEndTimeLabel.setForeground(Color.BLACK);


        create.addActionListener(
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
        this.add(eventNameInfo);
        this.add(eventPlaceInfo);
        this.add(eventAttendanceInfo);
        this.add(eventLevelComboBox);
        this.add(eventDateLabel);
        this.add(eventDateInputField);
        this.add(eventEndDateLabel);
        this.add(eventEndDateInputField);
        this.add(eventTimeLabel);
        this.add(eventTimeInputField);
        this.add(eventEndTimeLabel);
        this.add(eventEndTimeInputField);
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
