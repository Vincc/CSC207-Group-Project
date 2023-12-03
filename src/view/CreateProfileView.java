package view;

import interface_adapter.createProfile.CreateProfileController;
import interface_adapter.createProfile.CreateProfileState;
import interface_adapter.createProfile.CreateProfileViewModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
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
        LabelTextPanel profileInputtext = new LabelTextPanel(
                new JLabel("user description"), profileInputField );
        JLabel title = new JLabel("User Profile");
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton = new JButton("Back");
        editButton = new JButton("edit");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 2)));
        this.add(username);
        this.add(Box.createVerticalGlue());
        this.add(cancelButton);
        this.add(profileInputtext);
        this.add(editButton);
        editButton.addActionListener(e -> handleCreateProfile());
        cancelButton.addActionListener(e -> handleCancel());

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
