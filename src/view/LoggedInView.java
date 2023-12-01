package view;

import interface_adapter.createEvent.CreateEventState;
import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final LoggedInViewModel loggedInViewModel;
    private final LoggedInController loggedInController;
    public final String viewName = "logged in";

    private JLabel usernameLabel;
    private JButton logOutButton;
    private JButton joinEventButton;
    private JButton createEventButton;
    private JList<String> eventsList;


    private JButton userProfileButton;


    public LoggedInView(LoggedInViewModel loggedInViewModel, LoggedInController controller) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.loggedInController = controller;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(41, 128, 185));
        JLabel title = new JLabel("2Gather");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        topPanel.add(title);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernameLabel = new JLabel("Currently logged in: ");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(usernameLabel);
        add(centerPanel, BorderLayout.WEST);

        eventsList = new JList<>();
        eventsList.setCellRenderer(new EventListCellRenderer());
        JScrollPane scrollPane = new JScrollPane(eventsList);
        scrollPane.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        createEventButton = new JButton("Create Event");
        createEventButton.setBackground(new Color(39, 174, 96));
        createEventButton.setForeground(Color.WHITE);
        logOutButton = new JButton("Log Out");
        logOutButton.setBackground(new Color(192, 57, 43));
        logOutButton.setForeground(Color.WHITE);


        JPanel joinEventPanel = new JPanel();

        String[] comboBoxItems = extract_event_name();
        JComboBox<String> comboBox = new JComboBox<>(comboBoxItems);
        joinEventButton = new JButton("Join Event");
        joinEventButton.setBackground(Color.blue);
        joinEventButton.setForeground(Color.WHITE);

        joinEventPanel.add(comboBox);
        joinEventPanel.add(joinEventButton);
        add(joinEventPanel,BorderLayout.EAST);

        userProfileButton = new JButton("User Profile");
        userProfileButton.setBackground(new Color(60, 76, 231)); // Set button background color
        userProfileButton.setForeground(Color.WHITE); // Set button text color
        buttonPanel.add(userProfileButton);

        buttonPanel.add(createEventButton);
        buttonPanel.add(logOutButton);
        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(createEventButton);
        buttonPanel.add(logOutButton);

        createEventButton.addActionListener(e -> handleCreateEvent());
        logOutButton.addActionListener(e -> handleLogOut());

        joinEventButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(joinEventButton)) {
                            LoggedInState currentState = loggedInViewModel.getState();
                            String eventSelected = (String) comboBox.getSelectedItem();
                            loggedInController.addParticipants(eventSelected,currentState.getUsername());
                            updateEventsList();
                        }
                    }
                }
        );

        userProfileButton.addActionListener(e -> handleCreateProfile());


        updateEventsList();
        updateUsernameLabel();
    }

    private void handleCreateEvent() {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInController.executeCreateEvent(loggedInState.getUsername());
        updateEventsList();
    }

    private void handleLogOut() {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInController.executeCancel(loggedInState.getUsername());
        updateEventsList();
    }

    private void handleCreateProfile() {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInController.executeCreateProfile(loggedInState.getUsername());
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

    private String[] extract_event_name(){
       ArrayList<String> toRet = new ArrayList<>();
       toRet.add("Select Event");
        try {
            List<String> events = loadEventsFromJsonFile("events.json");
            for (String i:events){
                String[] infoForName = i.split("event name");
                String[] finelInfoForName = infoForName[1].split(",");
                String toList  = finelInfoForName[0].substring(3,finelInfoForName[0].length()-1 );
                toRet.add(toList);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toRet.toArray(new String[0]);
    }

    private List<String> loadEventsFromJsonFile(String fileName) throws IOException {
        List<String> events = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
            String jsonArrayString = jsonContent.toString().trim();
            jsonArrayString = jsonArrayString.substring(1, jsonArrayString.length() - 1);

            String[] eventStrings = jsonArrayString.split("},\\{");

            for (String eventString : eventStrings) {
                eventString = "{" + eventString + "}";
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

    private class EventListCellRenderer extends JPanel implements ListCellRenderer<String> {
        private JLabel eventNameLabel;
        private JLabel descriptionLabel;
        private JLabel organizerLabel;
        private JLabel detailsLabel;

        public EventListCellRenderer() {
            setLayout(new BorderLayout());
            setOpaque(true);
            setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(0, 80));
            eventNameLabel = new JLabel();
            descriptionLabel = new JLabel();
            organizerLabel = new JLabel();
            detailsLabel = new JLabel();

            JPanel eventInfoPanel = new JPanel(new BorderLayout());
            eventInfoPanel.add(eventNameLabel, BorderLayout.NORTH);
            eventInfoPanel.add(descriptionLabel, BorderLayout.CENTER);
            eventInfoPanel.add(organizerLabel, BorderLayout.SOUTH);
            eventInfoPanel.add(detailsLabel, BorderLayout.EAST);
            add(eventInfoPanel, BorderLayout.CENTER);
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
                String maxAttendance = extract_string(eventInfo[7]);
                String curAttendance = extract_cur_attendance(value);


                eventNameLabel.setText("<html><div style='text-align: center;'><b style='font-size: 20px; color: #3498db;'>" + eventName +
                        "</b></div></html>");

                descriptionLabel.setText("<html><div style='text-align: center; color: #34495e;'>" + eventDescription + "</div></html>");

                organizerLabel.setText("<html><div style='text-align: center;'><i style='color: #e74c3c;'>Organizer: " + organizer + "</i></div></html>");

                detailsLabel.setText("<html><div style='color: #2c3e50;'><b>Time:</b> " + eventTime + " | <b>Date:</b> " + eventDate +
                        " | <b>Level:</b> " + levelOfPlay + " | <b>Location:</b> " + eventLocation +
                        " | <b>Max Attendance:</b> " + maxAttendance + " | <b>Current attendance:</b> " + curAttendance + "</div></html>");

                if (isSelected) {
                    setBackground(new Color(236, 240, 241));
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

        private String extract_cur_attendance(String value){
            String[] infoForAtt = value.split("attendance");
            String[] finelInfoForAtt = infoForAtt[2].split("event end time");
            String attList  = finelInfoForAtt[0].substring(1,finelInfoForAtt[0].length() -2 );
            String[] splitedString = attList.split(":");
            if (splitedString[1].indexOf(',') == -1){
                return String.valueOf(1);
            }else{
                String[] userList = splitedString[1].split(",");
                int size = userList.length;
                return String.valueOf(size);
            }

        }


        private String extract_time(String start) {
            String[] splitedString = start.split(":");
            return splitedString[1].trim() + ":" + splitedString[2].trim().replaceAll("\"", "");
        }


    }
}
