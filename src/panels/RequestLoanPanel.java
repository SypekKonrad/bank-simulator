package panels;

import auth.UserAuth;
import models.*;
import utils.*;
import exceptions.*;
import javax.swing.*;
import java.awt.*;
import models.User;
import interfaces.*;

public class RequestLoanPanel extends JPanel {

    public RequestLoanPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel, User user) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel requestLoanLabel = new JLabel("Request Loan", SwingConstants.CENTER);
        requestLoanLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton requestButton = new JButton("Request");
        JButton backButton = new JButton("Back");

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField(40);

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        add(requestLoanLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = row;
        add(amountLabel, gbc);
        gbc.gridx = 1;
        add(amountField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(backButton, gbc);

        gbc.gridx = 1;
        add(requestButton, gbc);

        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "account");
        });

        requestButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText().trim());
                BankData bankData = DataManager.loadData();
                Account userAccount = null;

                for (Account acc : bankData.getAccounts()) {
                    if (acc.getOwner().getLogin().equals(user.getLogin())) {
                        userAccount = acc;
                        break;
                    }
                }

                if (userAccount == null) {
                    JOptionPane.showMessageDialog(this, "Account not found.");
                    return;
                }

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount must be greater than 0.", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Loanable loanable = (Loanable) userAccount;
                boolean success = loanable.requestLoan(amount);

                if (success) {
//                    userAccount.setBalance(userAccount.getBalance() + amount);
                    DataManager.saveData(bankData);
                    JOptionPane.showMessageDialog(this, "Loan approved.");
//                    userAccount.setBalance(amount);
                    cardLayout.show(mainPanel, "account");
                } else {
                    JOptionPane.showMessageDialog(this, "Loan request failed.", "Request Denied", JOptionPane.ERROR_MESSAGE);
                }


            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ExceptionUtils.handle(ex, this, "Failed to process loan request.");
            }
        });


    }
}

