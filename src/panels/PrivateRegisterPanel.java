package panels;
import models.PrivateAccount;
import models.PrivateUser;
import utils.Validators;

import javax.swing.*;
import java.awt.*;

public class PrivateRegisterPanel extends JPanel {
    public PrivateRegisterPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel) {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Form labels and fields
        JLabel titleLabel = new JLabel("Private Account Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel firstNameLabel = new JLabel("First Name:");
        JTextField firstNameField = new JTextField(20);

        JLabel lastNameLabel = new JLabel("Last Name:");
        JTextField lastNameField = new JTextField(20);

        JLabel dobLabel = new JLabel("Date of Birth:");
        JTextField dobField = new JTextField(10);

        JLabel peselLabel = new JLabel("PESEL:");
        JTextField peselField = new JTextField(11);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(40);

        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField(15);

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(30);

        JLabel loginLabel = new JLabel("Login:");
        JTextField loginField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField(20);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");

        int row = 0;

        // Add components to panel
        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        add(titleLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = row;
        add(firstNameLabel, gbc);
        gbc.gridx = 1;
        add(firstNameField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(lastNameLabel, gbc);
        gbc.gridx = 1;
        add(lastNameField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(dobLabel, gbc);
        gbc.gridx = 1;
        add(dobField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(peselLabel, gbc);
        gbc.gridx = 1;
        add(peselField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(emailLabel, gbc);
        gbc.gridx = 1;
        add(emailField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(phoneLabel, gbc);
        gbc.gridx = 1;
        add(phoneField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(addressLabel, gbc);
        gbc.gridx = 1;
        add(addressField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(loginLabel, gbc);
        gbc.gridx = 1;
        add(loginField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        add(confirmPasswordField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(backButton, gbc);
        gbc.gridx = 1;
        add(registerButton, gbc);

        // action listeners
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "register");
        });

        // data storage logic, jakas validacja
        registerButton.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String dateOfBirth = dobField.getText();
            String pesel = peselField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (!Validators.allFieldsFilled(firstName, lastName, dateOfBirth, pesel, email, phone, address, login, password, confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Validators.isValidPESEL(pesel)) {
                JOptionPane.showMessageDialog(frame, "PESEL must be exactly 11 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Validators.isValidEmail(email)) {
                JOptionPane.showMessageDialog(frame, "Invalid email format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Validators.isValidPhone(phone)) {
                JOptionPane.showMessageDialog(frame, "Invalid phone number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Validators.passwordsMatch(password, confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            PrivateUser user = new PrivateUser(
                    firstName, lastName, dateOfBirth, pesel, email,
                    phone, address, login, password
            );

            PrivateAccount account = new PrivateAccount(user);

            JOptionPane.showMessageDialog(frame, "Account registered!");
            cardLayout.show(mainPanel, "login");


        });
    }
}
