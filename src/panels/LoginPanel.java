package panels;
//import auth.UserAuth;
import models.*;
import utils.*;
import exceptions.*;
import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    public LoginPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel) {
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

//        UserAuth userAuth = new UserAuth();

//        submitButton.addActionListener(e -> {
//            String login = loginField.getText();
//            String password = new String(passwordField.getPassword());
//
//            if (userAuth.authenticate(login, password)) {
//                JOptionPane.showMessageDialog(frame, "ok");
//            } else {
//                JOptionPane.showMessageDialog(frame, "wrong login/passwd.");
//            }
//        });
        submitButton.addActionListener(e -> {
            try {
                BankData data = DataManager.loadData();
                String login = loginField.getText();
                String password = new String(passwordField.getPassword());

//                System.out.printf("Stored login=[%s], Stored password=[%s]%n", user.getLogin(), user.getPassword());

                boolean authenticated = false;

                if (data.getUsers().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No users registered yet");
                    return;
                }

                for (User user : data.getUsers()) {
//                    System.out.printf("Stored login=[%s], Stored password=[%s]%n", user.getLogin().trim(), user.getPassword().trim());
//                    System.out.printf("Input login=[%s], Input password=[%s]%n", login, password);
                    if (user.getLogin().trim().equalsIgnoreCase(login) &&
                            user.getPassword().equals(password)) {
                        authenticated = true;
                        break;
                    }
                }

                if (authenticated) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                    cardLayout.show(mainPanel, "account dashboard");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid login credentials");
                }

            } catch (DataLoadException error) {
                JOptionPane.showMessageDialog(frame, "Error loading data: " + error.getMessage());
                error.printStackTrace();
            }
        });


        createAccountButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "register");
        });


    }
}

