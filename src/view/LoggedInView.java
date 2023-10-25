    package view;

    import interface_adapter.logged_in.LoggedInState;
    import interface_adapter.logged_in.LoggedInViewModel;
    import interface_adapter.login.LoginState;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.beans.PropertyChangeEvent;
    import java.beans.PropertyChangeListener;

    public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {

        public final String viewName = "logged in";
        private final LoggedInViewModel loggedInViewModel;

        JLabel username;

        JLabel currentEventsLabel;
        JPanel eventsPanel;

        final JButton logOut;
        final JButton createEventPage;

        /**
         * A window with a title and a JButton.
         */
        public LoggedInView(LoggedInViewModel loggedInViewModel) {
            this.loggedInViewModel = loggedInViewModel;
            this.loggedInViewModel.addPropertyChangeListener(this);

            JLabel title = new JLabel("Logged In Screen");
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel usernameInfo = new JLabel("Currently logged in: ");
            usernameInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

            currentEventsLabel = new JLabel("Current events:");
            currentEventsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


            username = new JLabel();
            username.setAlignmentX(Component.CENTER_ALIGNMENT);

            eventsPanel = new JPanel();
            eventsPanel.setLayout(new BoxLayout(eventsPanel, BoxLayout.Y_AXIS));

            JPanel buttons = new JPanel();

            logOut = new JButton(loggedInViewModel.LOGOUT_BUTTON_LABEL);
            buttons.add(logOut);
            createEventPage = new JButton(loggedInViewModel.CREATE_EVENT_BUTTON_LABEL);
            buttons.add(createEventPage);

            logOut.addActionListener(this);

            createEventPage.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(createEventPage)) {
                                LoginState currentState = loggedInViewModel.getState();

                                loginController.execute(
                                        currentState.getUsername(),
                                        currentState.getPassword()
                                );
                            }
                        }
                    }
            );
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            this.add(title);
            this.add(usernameInfo);
            this.add(username);
            this.add(currentEventsLabel);
            this.add(buttons);
        }

        /**
         * React to a button click that results in evt.
         */
        public void actionPerformed(ActionEvent evt) {
            System.out.println("Click " + evt.getActionCommand());
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            LoggedInState state = (LoggedInState) evt.getNewValue();
            username.setText(state.getUsername());
        }
    }