package panels;

import auth.UserAuth;
import models.*;
import utils.*;
import exceptions.*;
import javax.swing.*;
import java.awt.*;
import models.User;
import interfaces.*;

public class MoneyTransferPanel extends JPanel {

    public MoneyTransferPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel, User user) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel mainLabel = new JLabel("Send a money transfer", SwingConstants.CENTER);
        mainLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton sendButton = new JButton("Send");
        JButton backButton = new JButton("Back");

        JLabel accNumbLabel = new JLabel("Recipient account number: ");
        JTextField accNumbField = new JTextField(26);

        JLabel amountLabel = new JLabel("Amount: ");
        JTextField amountField = new JTextField(40);

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        add(mainLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = row;
        add(accNumbLabel, gbc);
        gbc.gridx = 1;
        add(accNumbField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(amountLabel, gbc);
        gbc.gridx = 1;
        add(amountField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(backButton, gbc);

        gbc.gridx = 1;
        add(sendButton, gbc);

        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "account");
        });

        sendButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText().trim());
                String recipientAccountNumber = accNumbField.getText().trim();

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount must be greater than 0.", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (recipientAccountNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter recipient account number.", "Missing Info", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                BankData bankData = DataManager.loadData();

                Account sender = null;
                for (Account acc : bankData.getAccounts()) {
                    if (acc.getOwner().getLogin().equals(user.getLogin())) {
                        sender = acc;
                        break;
                    }
                }

                if (sender == null) {
                    JOptionPane.showMessageDialog(this, "Sender account not found.");
                    return;
                }

                Account recipient = null;
                for (Account acc : bankData.getAccounts()) {
                    if (acc.getAccountNumber().equals(recipientAccountNumber)) {
                        recipient = acc;
                        break;
                    }
                }

                if (recipient == null) {
                    JOptionPane.showMessageDialog(this, "Recipient account not found.", "Invalid Recipient", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Transferable transferable = (Transferable) sender;
                boolean success = transferable.transferTo(recipient, amount);

                if (success) {
                    DataManager.saveData(bankData);
                    JOptionPane.showMessageDialog(this, "Transfer successful.");
                    cardLayout.show(mainPanel, "account");
                } else {
                    JOptionPane.showMessageDialog(this, "Transfer failed. Check balance or amount.", "Transfer Failed", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ExceptionUtils.handle(ex, this, "Failed to process transfer.");
            }
        });


    }
}

