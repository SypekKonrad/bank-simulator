package panels;

import auth.UserAuth;
import models.*;
import utils.*;
import exceptions.*;
import javax.swing.*;
import java.awt.*;
import models.User;
import interfaces.*;

public class RepayLoanPanel extends JPanel {

    public RepayLoanPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel, User user) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel repayLoanLabel = new JLabel("Repay Loan", SwingConstants.CENTER);
        repayLoanLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton repayButton = new JButton("Repay");
        JButton backButton = new JButton("Back");

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField(40);

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row++; gbc.gridwidth = 2;
        add(repayLoanLabel, gbc);
        gbc.gridwidth = 1;

        gbc.gridx = 0; gbc.gridy = row;
        add(amountLabel, gbc);
        gbc.gridx = 1;
        add(amountField, gbc); row++;

        gbc.gridx = 0; gbc.gridy = row;
        add(backButton, gbc);

        gbc.gridx = 1;
        add(repayButton, gbc);

        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "account");
        });

        repayButton.addActionListener(e -> {
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

                loanable.repayLoan(amount);

                DataManager.saveData(bankData);

                JOptionPane.showMessageDialog(this, "Loan repaid.");

                cardLayout.show(mainPanel, "account");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ExceptionUtils.handle(ex, this, "Failed to process loan payment.");
            }
        });


    }
}

