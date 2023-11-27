package view;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;

public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final LoggedInViewModel loggedInViewModel;
    private final LoggedInController loggedInController;
    public final String viewName = "logged in";

    private JLabel usernameLabel;
    private JButton logOutButton;
    private JButton createEventButton;
    private JList<String> eventsList;

    public LoggedInView(LoggedInViewModel loggedInViewModel, LoggedInController controller) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.loggedInController = controller;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding to the main panel

        // Top panel with the app title
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(41, 128, 185)); // Dark blue color
        JLabel title = new JLabel("2Gether");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        topPanel.add(title);
        add(topPanel, BorderLayout.NORTH);

        // Center panel with user information
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernameLabel = new JLabel("Currently logged in: ");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(usernameLabel);
        add(centerPanel, BorderLayout.WEST); // Change to BorderLayout.WEST

        // Event list with scroll pane
        eventsList = new JList<>();
        eventsList.setCellRenderer(new EventListCellRenderer());
        JScrollPane scrollPane = new JScrollPane(eventsList);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY)); // Add border to the scroll pane
        add(scrollPane, BorderLayout.CENTER); // Change to BorderLayout.CENTER

        // Bottom panel with buttons
        JPanel buttonPanel = new JPanel();
        createEventButton = new JButton("Create Event");
        createEventButton.setBackground(new Color(39, 174, 96)); // Green color
        createEventButton.setForeground(Color.WHITE);
        logOutButton = new JButton("Log Out");
        logOutButton.setBackground(new Color(192, 57, 43)); // Red color
        logOutButton.setForeground(Color.WHITE);
        buttonPanel.add(createEventButton);
        buttonPanel.add(logOutButton);
        add(buttonPanel, BorderLayout.SOUTH);

        createEventButton.addActionListener(e -> handleCreateEvent());
        logOutButton.addActionListener(e -> handleLogOut());

        // Load and display existing events
        updateEventsList();

        // Set the initial usernameLabel value
        updateUsernameLabel();
    }

    private void handleCreateEvent() {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInController.executeCreateEvent(loggedInState.getUsername());

        // Update events list after creating an event
        updateEventsList();
    }

    private void handleLogOut() {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInController.executeCancel(loggedInState.getUsername());

        // Update events list after logging out
        updateEventsList();
    }

    private void updateUsernameLabel() {
        LoggedInState state = loggedInViewModel.getState();
        usernameLabel.setText("Currently logged in: " + state.getUsername());
    }

    private void updateEventsList() {
        try {
            List<String> events = loadEventsFromJsonFile("events.json");
            eventsList.setListData(events.toArray(new String[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> loadEventsFromJsonFile(String fileName) throws IOException {
        List<String> events = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            // Assuming the file contains an array of events
            String jsonArrayString = jsonContent.toString().trim();
            jsonArrayString = jsonArrayString.substring(1, jsonArrayString.length() - 1); // Remove enclosing square brackets

            String[] eventStrings = jsonArrayString.split("},\\{");

            for (String eventString : eventStrings) {
                eventString = "{" + eventString + "}"; // Re-add curly braces for each event
                events.add(eventString);
            }
        }

        return events;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateUsernameLabel();
            updateEventsList();
        }
    }

    private static class EventListCellRenderer extends JPanel implements ListCellRenderer<String> {
        private JLabel eventNameLabel;
        private JLabel descriptionLabel;
        private JLabel organizerLabel;
        private JLabel detailsLabel;

        public EventListCellRenderer() {
            setLayout(new BorderLayout());
            setOpaque(true);
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY)); // Add border between items
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(0, 80)); // Set a fixed height for each item
            eventNameLabel = new JLabel();
            descriptionLabel = new JLabel();
            organizerLabel = new JLabel();
            detailsLabel = new JLabel();
            add(eventNameLabel, BorderLayout.NORTH);
            add(descriptionLabel, BorderLayout.CENTER);
            add(organizerLabel, BorderLayout.SOUTH);
            add(detailsLabel, BorderLayout.EAST);
        }

        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {

            // Format event information
            String[] eventInfo = value.split(",");
            if (eventInfo.length >= 7) {
                String eventName = extract_string(eventInfo[0]);
                String eventDescription = extract_string(eventInfo[1]);
                String organizer = extract_string(eventInfo[3]);
                String eventTime = extract_time(eventInfo[4]);
                String eventDate = extract_string(eventInfo[5]);
                String levelOfPlay = extract_string(eventInfo[2]);
                String eventLocation = extract_string(eventInfo[8]);
                String maxAttendance =  extract_string(eventInfo[7]);
                String curAttendance = extract_string(eventInfo[9]);

                eventNameLabel.setText("<html><div style='text-align: center;'><b style='font-size: 20px; color: #3498db;'>" + eventName +
                        "</b></div></html>");

                descriptionLabel.setText("<html><div style='text-align: center; color: #34495e;'>" + eventDescription + "</div></html>");

                organizerLabel.setText("<html><div style='text-align: center;'><i style='color: #e74c3c;'>Organizer: " + organizer + "</i></div></html>");

                detailsLabel.setText("<html><div style='color: #2c3e50;'><b>Time:</b> " + eventTime + " | <b>Date:</b> " + eventDate +
                        " | <b>Level:</b> " + levelOfPlay + " | <b>Location:</b> " + eventLocation +
                        " | <b>Max Attendance:</b> " + maxAttendance + " | <b>Current attendance:</b> " + curAttendance + "</div></html>");

                // Set different background color for selected and non-selected items
                if (isSelected) {
                    setBackground(new Color(236, 240, 241)); // Light gray for selected items
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(Color.WHITE);
                    setForeground(list.getForeground());
                }
            }

            return this;
        }

        private String extract_string(String start) {
            String[] splitedString = start.split(":");
            return splitedString[1].trim().replaceAll("\"", "");
        }
        private String extract_attendance_length(String start) {
            String[] splitedString = start.split(":");
            String attendanceList = splitedString[9].trim().replaceAll("\"", "");

            // Check if attendance list is empty
            if (attendanceList.equals("[]")) {
                return "0";
            }

            // Remove brackets from the attendance list
            attendanceList = attendanceList.substring(1, attendanceList.length() - 1);

            // Split the attendance list by commas and count the elements
            String[] attendees = attendanceList.split(",");
            return String.valueOf(attendees.length);
        }


        private String extract_time(String start) {
            String[] splitedString = start.split(":");
            return splitedString[1].trim() + ":" + splitedString[2].trim().replaceAll("\"", "");
        }
    }

}
