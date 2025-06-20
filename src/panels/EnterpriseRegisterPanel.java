package panels;

import exceptions.DataLoadException;
import exceptions.DataSaveException;
import models.BankData;
import models.EnterpriseAccount;
import models.EnterpriseUser;
import models.User;
import utils.DataManager;
import utils.Validators;

import javax.swing.*;
import java.awt.*;

public class EnterpriseRegisterPanel extends JPanel {
    public EnterpriseRegisterPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel) {

        // defining layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // declaring buttons/labels/components etc
        // Form labels and fields
        JLabel titleLabel = new JLabel("Enterprise Account Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel companyNameLabel = new JLabel("Company Name:");
        JTextField companyNameField = new JTextField(20);

        JLabel nipLabel = new JLabel("NIP:");
        JTextField nipField = new JTextField(10);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(40);

        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField(15);

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(30);

        JLabel loginLabel = new JLabel("Login:");
        JTextField loginField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPasswordField = new JPasswordField(20);

        // buttons
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("back");

        // layout


        int row = 0;

        // add components to panel
        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        add(titleLabel, gbc);
        gbc.gridwidth = 1;

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
        add(companyNameLabel, gbc);
        gbc.gridx = 1;
        add(companyNameField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(nipLabel, gbc);
        gbc.gridx = 1;
        add(nipField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(backButton, gbc);
        gbc.gridx = 1;
        add(registerButton, gbc);

        // backButton layout
//        gbc.gridx = 0; gbc.gridy = 1;
//        add(backButton, gbc);

        // action listeners
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "register");
        });

        // data storage logic, jakas validacja
        registerButton.addActionListener(e -> {
            String login = loginField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            String email = emailField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String NIP = nipField.getText();
            String companyName = companyNameField.getText();

            if (!Validators.allFieldsFilled(NIP, companyName, email, phone, address, login, password, confirmPassword)) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Validators.isValidNIP(NIP)) {
                JOptionPane.showMessageDialog(frame, "NIP must be exactly 10 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
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

            EnterpriseUser user = new EnterpriseUser(
                    login,
                    password,
                    email,
                    phone,
                    address,
                    companyName,
                    NIP
            );

            EnterpriseAccount account = new EnterpriseAccount(user);

            try {
                BankData data = DataManager.loadData();

                for (User existingUser : data.getUsers()) {
                    if (Validators.userExists(existingUser.getLogin(), login)) {
                        JOptionPane.showMessageDialog(frame, "A user with this login already exists.", "Registration Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                data.addUser(user);
                data.addAccount(account);

                DataManager.saveData(data);

                JOptionPane.showMessageDialog(frame, "Data saved");
                cardLayout.show(mainPanel, "login");

            } catch (DataSaveException | DataLoadException ex) {
                JOptionPane.showMessageDialog(frame, "Failed to save data: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(frame, "Account registered!");
            cardLayout.show(mainPanel, "login");


        });

    }
}