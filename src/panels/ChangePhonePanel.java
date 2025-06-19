package panels;

import auth.UserAuth;
import models.*;
import utils.*;
import exceptions.*;
import javax.swing.*;
import java.awt.*;
import models.User;

public class ChangePhonePanel extends JPanel {

    public ChangePhonePanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel,  User user) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel changePhoneLabel = new JLabel("Change phone number", SwingConstants.CENTER);
        changePhoneLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel currentPhoneLabel = new JLabel("Current phone number: " + user.getPhone());

        JLabel newPhoneLabel = new JLabel("New phone number:");
        JTextField newPhoneField = new JTextField(40);

        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        add(changePhoneLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        add(currentPhoneLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = row;
        add(newPhoneLabel, gbc);
        gbc.gridx = 1;
        add(newPhoneField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(backButton, gbc);

        gbc.gridx = 1;
        add(submitButton, gbc);

        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "account");
        });

        submitButton.addActionListener(e -> {

            String newPhone = newPhoneField.getText();

            if (!Validators.isNotEmpty(newPhone)) {
                JOptionPane.showMessageDialog(frame, "New Phone cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Validators.isValidPhone(newPhone)) {
                JOptionPane.showMessageDialog(frame, "Invalid phone format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                BankData bankData = DataManager.loadData();

                for (User u : bankData.getUsers()) {
                    if (u.getLogin().equals(user.getLogin())) {
                        u.setEmail(newPhone);
                        break;
                    }
                }

                DataManager.saveData(bankData);

                JOptionPane.showMessageDialog(this, "Phone number updated.", "Success", JOptionPane.INFORMATION_MESSAGE);

                user.setPhone(newPhone);

                cardLayout.show(mainPanel, "account");

            }  catch (Exception ex) {
                PhoneChangeException phoneEx = new PhoneChangeException("Failed to change phone number", ex);
                ExceptionUtils.handle(phoneEx, this, "Phone number update failed");
            }

        });



    }
}

