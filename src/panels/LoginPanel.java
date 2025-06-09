package panels;
import auth.UserAuth;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    public LoginPanel(JFrame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel loginLabel = new JLabel("Login:");
        JTextField loginField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton submitButton = new JButton("Login");
        JButton createAccountButton = new JButton("Create Account");

        // layout
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // row 1 - login
        gbc.gridx = 0; gbc.gridy = 0;
        add(loginLabel, gbc);
        gbc.gridx = 1;
        add(loginField, gbc);

        // row 2 - passwd
        gbc.gridx = 0; gbc.gridy = 1;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // row 3 - buttons
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(submitButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(createAccountButton, gbc);

        UserAuth userAuth = new UserAuth();

        submitButton.addActionListener(e -> {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());

            if (userAuth.authenticate(login, password)) {
                JOptionPane.showMessageDialog(frame, "ok");
            } else {
                JOptionPane.showMessageDialog(frame, "wrong login/passwd.");
            }
        });


    }
}