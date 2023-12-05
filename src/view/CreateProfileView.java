package view;

import interface_adapter.createProfile.CreateProfileController;
import interface_adapter.createProfile.CreateProfileState;
import interface_adapter.createProfile.CreateProfileViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class CreateProfileView extends JPanel implements ActionListener, PropertyChangeListener {
    JLabel username;
    public final String viewName = "createProfileView";
    private final CreateProfileViewModel createProfileViewModel;
    private final CreateProfileController createProfileController;

    private final JTextField profileInputField = new JTextField(15);
    private JButton cancelButton;
    private JButton editButton;

    public CreateProfileView(CreateProfileViewModel createProfileViewModel,
                             CreateProfileController createProfileController) {
        this.createProfileViewModel = createProfileViewModel;
        this.createProfileController = createProfileController;
        this.createProfileViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(141, 217, 72));

        JLabel title = new JLabel("User Profile");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(219, 15, 52));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        username = new JLabel();
        username.setFont(new Font("Arial", Font.PLAIN, 16));
        username.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel profileInputtext = new LabelTextPanel(
                new JLabel("user description"), profileInputField);
        profileInputField.setBorder(new LineBorder(Color.GRAY, 1));
        profileInputtext.setBorder(new EmptyBorder(10, 0, 20, 0));

        cancelButton = new JButton("Back");
        editButton = new JButton("edit");
        cancelButton.setBackground(new Color(100, 149, 237));
        editButton.setBackground(new Color(50, 205, 50));
        cancelButton.setForeground(Color.WHITE);
        editButton.setForeground(Color.WHITE);
        cancelButton.setMinimumSize(new Dimension(100, 40));
        editButton.setMinimumSize(new Dimension(100, 40));
        cancelButton.addActionListener(e -> handleCancel());
        editButton.addActionListener(e -> handleCreateProfile());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(new Color(141, 217, 72));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        buttonPanel.add(cancelButton, gbc);
        buttonPanel.add(editButton, gbc);

        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(username);
        this.add(Box.createVerticalGlue());
        this.add(profileInputtext);
        this.add(buttonPanel);

    }
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes
        CreateProfileState state = (CreateProfileState) evt.getNewValue();
        username.setText(state.getUsername());
        profileInputField.setText(state.getUserDescription());
    }

    private void handleCancel() {
        createProfileController.executeCancel();
    }

    private void handleCreateProfile() {
        CreateProfileState createProfileState= createProfileViewModel.getState();
        createProfileState.setUserDescription(profileInputField.getText());
        createProfileController.executeEditProfile(
                createProfileState.getUsername(),
                createProfileState.getUserDescription());
    }




}
