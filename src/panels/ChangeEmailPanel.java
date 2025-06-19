package panels;

import auth.UserAuth;
import models.*;
import utils.*;
import exceptions.*;
import javax.swing.*;
import java.awt.*;
import models.User;

public class ChangeEmailPanel extends JPanel {

    public ChangeEmailPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel, User user) {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel changeEmailLabel = new JLabel("Change email", SwingConstants.CENTER);
        changeEmailLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel currentEmailLabel = new JLabel("Current email: " + user.getEmail());

        JLabel newEmailLabel = new JLabel("New email:");
        JTextField newEmailField = new JTextField(40);

        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        add(changeEmailLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        add(currentEmailLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = row;
        add(newEmailLabel, gbc);
        gbc.gridx = 1;
        add(newEmailField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(backButton, gbc);

        gbc.gridx = 1;
        add(submitButton, gbc);

        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "account");
        });

        submitButton.addActionListener(e -> {

            String newEmail = newEmailField.getText().trim();

            if (newEmail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Email cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!newEmail.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
                JOptionPane.showMessageDialog(this, "Invalid email format.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                BankData bankData = DataManager.loadData();

                for (User u : bankData.getUsers()) {
                    if (u.getLogin().equals(user.getLogin())) {
                        u.setEmail(newEmail);
                        break;
                    }
                }

                DataManager.saveData(bankData);

                JOptionPane.showMessageDialog(this, "Email updated.", "Success", JOptionPane.INFORMATION_MESSAGE);

                user.setEmail(newEmail);

                cardLayout.show(mainPanel, "account");

            }  catch (Exception ex) {
                EmailChangeException emailEx = new EmailChangeException("Failed to change email", ex);
                ExceptionUtils.handle(emailEx, this, "Email update failed");
            }

        });

    }
}

