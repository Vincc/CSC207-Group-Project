package view;

import interface_adapter.logged_in.LoggedInController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements PropertyChangeListener {

    private final LoggedInViewModel loggedInViewModel;
    private final LoggedInController loggedInController;
    public final String viewName = "logged in";

    private JLabel usernameLabel;
    private JButton logOutButton;
    private JButton createEventButton;

    private JButton userProfileButton;

    public LoggedInView(LoggedInViewModel loggedInViewModel, LoggedInController controller) {
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.loggedInController = controller;

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(129, 190, 248)); // Set background color
        JLabel title = new JLabel("2Gether");
        title.setFont(new Font("Arial", Font.BOLD, 24)); // Set font and size
        title.setForeground(Color.WHITE); // Set text color
        topPanel.add(title);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernameLabel = new JLabel("Currently logged in: ");
        centerPanel.add(usernameLabel);
        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        createEventButton = new JButton("Create Event");
        createEventButton.setBackground(new Color(46, 204, 113)); // Set button background color
        createEventButton.setForeground(Color.WHITE); // Set button text color
        logOutButton = new JButton("Log Out");
        logOutButton.setBackground(new Color(231, 76, 60)); // Set button background color
        logOutButton.setForeground(Color.WHITE); // Set button text color
        userProfileButton = new JButton("User Profile");
        userProfileButton.setBackground(new Color(60, 76, 231)); // Set button background color
        userProfileButton.setForeground(Color.WHITE); // Set button text color
        buttonPanel.add(userProfileButton);
        buttonPanel.add(createEventButton);
        buttonPanel.add(logOutButton);
        add(buttonPanel, BorderLayout.SOUTH);

        createEventButton.addActionListener(e -> handleCreateEvent());
        logOutButton.addActionListener(e -> handleLogOut());
        userProfileButton.addActionListener(e -> handleCreateProfile());
    }

    private void handleCreateEvent() {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInController.executeCreateEvent(loggedInState.getUsername());
    }

    private void handleLogOut() {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInController.executeCancel(loggedInState.getUsername());
    }

    private void handleCreateProfile() {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInController.executeCreateProfile(loggedInState.getUsername());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        usernameLabel.setText("Currently logged in: " + state.getUsername());
    }
}
