package panels;

import auth.UserAuth;
import models.*;
import utils.*;
import exceptions.*;
import javax.swing.*;
import java.awt.*;
import models.User;

public class ChangeAddressPanel extends JPanel {

    public ChangeAddressPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel, User user) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel changeAdressLabel = new JLabel("Change adress", SwingConstants.CENTER);
        changeAdressLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel currentAdressLabel = new JLabel("Current adress: " + user.getAddress());

        JLabel newAdressLabel = new JLabel("New adress:");
        JTextField newAdressField = new JTextField(40);

        JButton submitButton = new JButton("Submit");
        JButton backButton = new JButton("Back");

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        add(changeAdressLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        add(currentAdressLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = row;
        add(newAdressLabel, gbc);
        gbc.gridx = 1;
        add(newAdressField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(backButton, gbc);

        gbc.gridx = 1;
        add(submitButton, gbc);

        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "account");
        });

        submitButton.addActionListener(e -> {

            String newAdress = newAdressField.getText();

            if (!Validators.isNotEmpty(newAdress)) {
                JOptionPane.showMessageDialog(frame, "New Phone cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                BankData bankData = DataManager.loadData();

                for (User u : bankData.getUsers()) {
                    if (u.getLogin().equals(user.getLogin())) {
                        u.setAddress(newAdress);
                        break;
                    }
                }

                DataManager.saveData(bankData);

                JOptionPane.showMessageDialog(this, "Adress updated.", "Success", JOptionPane.INFORMATION_MESSAGE);

                user.setAddress(newAdress);

                cardLayout.show(mainPanel, "account");

            }  catch (Exception ex) {
                AdressChangeException adressEx = new AdressChangeException("Failed to change adress", ex);
                ExceptionUtils.handle(adressEx, this, "Adress update failed");
            }

        });






    }
}

