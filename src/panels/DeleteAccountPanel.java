package panels;

import auth.UserAuth;
import models.*;
import utils.*;
import exceptions.*;
import javax.swing.*;
import java.awt.*;
import models.User;

public class DeleteAccountPanel extends JPanel {

    public DeleteAccountPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel, User user) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel deleteAccountLabel = new JLabel("Delete account", SwingConstants.CENTER);
        deleteAccountLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back");

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        add(deleteAccountLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = row;
        add(backButton, gbc);

        gbc.gridx = 1;
        add(deleteButton, gbc);

        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "account");
        });

        deleteButton.addActionListener(e -> {

            try {
                BankData bankData = DataManager.loadData();

                bankData.getUsers().removeIf(u -> u.getLogin().equals(user.getLogin()));
                bankData.getAccounts().removeIf(acc -> acc.getOwner().getLogin().equals(user.getLogin()));

                DataManager.saveData(bankData);

                JOptionPane.showMessageDialog(frame, "Account deleted.");

                DataManager.saveData(bankData);

                cardLayout.show(mainPanel, "login");

            }  catch (Exception ex) {
                DeleteAccountException deleteEx = new DeleteAccountException("Failed to delete account", ex);
                ExceptionUtils.handle(deleteEx, this, "Failure");
            }

        });


    }
}

