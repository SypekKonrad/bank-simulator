package panels;

import auth.UserAuth;
import models.*;
import utils.*;
import exceptions.*;
import javax.swing.*;
import java.awt.*;
import models.User;

public class ChangePasswordPanel extends JPanel {

    public ChangePasswordPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel, User user) {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel changePasswordLabel = new JLabel("Change password", SwingConstants.CENTER);
        changePasswordLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel oldPasswordLabel = new JLabel("Old password:");
        JTextField oldPasswordField = new JPasswordField(40);

        JLabel newPasswordLabel = new JLabel("New password:");
        JTextField newPasswordField = new JPasswordField(40);

        JLabel confirmNewPasswordLabel = new JLabel("Confirm new password:");
        JTextField confirmNewPasswordField = new JPasswordField(40);

        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        add(changePasswordLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = row;
        add(oldPasswordLabel, gbc);
        gbc.gridx = 1;
        add(oldPasswordField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(newPasswordLabel, gbc);
        gbc.gridx = 1;
        add(newPasswordField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(confirmNewPasswordLabel, gbc);
        gbc.gridx = 1;
        add(confirmNewPasswordField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(backButton, gbc);

        gbc.gridx = 1;
        add(submitButton, gbc);

        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "account");
        });

        submitButton.addActionListener(e -> {

            String oldPassword = oldPasswordField.getText();
            String newPassword = newPasswordField.getText();
            String confirmNewPassword = confirmNewPasswordField.getText();

            if (!Validators.allFieldsFilled(oldPassword, newPassword, confirmNewPassword)) {
                JOptionPane.showMessageDialog(frame, "All fields must be filled.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!Validators.passwordsMatch(newPassword, confirmNewPassword)) {
                JOptionPane.showMessageDialog(frame, "Passwords do not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                BankData bankData = DataManager.loadData();
                boolean passwordUpdated = false;

                for (User u : bankData.getUsers()) {
                    if (u.getLogin().equals(user.getLogin()) && u.getPassword().equals(oldPassword)) {
                        u.setPassword(newPassword);
                        passwordUpdated = true;
                        break;
                    }
                }
                if (passwordUpdated) {
                    DataManager.saveData(bankData);
                    user.setPassword(newPassword);
                    JOptionPane.showMessageDialog(this, "Password updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    cardLayout.show(mainPanel, "account");
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect old password.", "Authentication Failed", JOptionPane.ERROR_MESSAGE);
                }

                DataManager.saveData(bankData);



            } catch (Exception ex) {
                PasswordChangeException passwordEx = new PasswordChangeException("Failed to change password", ex);
                ExceptionUtils.handle(passwordEx, this, "Password update failed");
            }

        });





    }
}

