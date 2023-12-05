package view;

import interface_adapter.createProfile.CreateProfileState;
import interface_adapter.createProfile.CreateProfileViewModel;

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

    private JButton cancelButton;

    public CreateProfileView(CreateProfileViewModel createProfileViewModel) {
        this.createProfileViewModel = createProfileViewModel;

        this.createProfileViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("User Profile");
        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton = new JButton("Cancel");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, 2)));
        this.add(username);
        this.add(Box.createVerticalGlue());
        this.add(cancelButton);

    }
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes
        CreateProfileState state = (CreateProfileState) evt.getNewValue();
        username.setText(state.getUsername());
    }



}
