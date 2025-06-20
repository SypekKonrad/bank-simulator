package panels;

import javax.swing.*;
import java.awt.*;
import models.*;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

public class AccountPanel extends JPanel {

    public AccountPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel, User user, Account account) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);

        String displayName;
        if (user instanceof PrivateUser) {
            PrivateUser privateUser = (PrivateUser) user;
            displayName = privateUser.getFirstName() + " " + privateUser.getLastName();
        } else if (user instanceof EnterpriseUser) {
            EnterpriseUser enterpriseUser = (EnterpriseUser) user;
            displayName = enterpriseUser.getCompanyName();
        } else {
            displayName = user.getLogin();
        }

        // Navbar
        JPanel navbar = new JPanel();
        navbar.setLayout(new BoxLayout(navbar, BoxLayout.X_AXIS));
        navbar.setBackground(new Color(30, 144, 255));
        navbar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton logoutButton = createNavButton("Logout");
        JButton changeEmailButton = createNavButton("Change Email");
        JButton changePasswordButton = createNavButton("Change Password");
        JButton changePhoneButton = createNavButton("Change Phone Number");
        JButton changeAddressButton = createNavButton("Update Address");
        JButton deleteAccountButton = createNavButton("Delete Account");
        JButton copyButton = new JButton("Copy");
        JButton requestLoanButton = new JButton("Request loan");
        JButton repayLoanButton = new JButton("Repay loan");
        JButton moneyTransferButton = new JButton("Money transfer");

        navbar.add(logoutButton);
        navbar.add(Box.createRigidArea(new Dimension(10, 0)));
        navbar.add(changeEmailButton);
        navbar.add(Box.createRigidArea(new Dimension(10, 0)));
        navbar.add(changePasswordButton);
        navbar.add(Box.createRigidArea(new Dimension(10, 0)));
        navbar.add(changePhoneButton);
        navbar.add(Box.createRigidArea(new Dimension(10, 0)));
        navbar.add(changeAddressButton);
        navbar.add(Box.createRigidArea(new Dimension(10, 0)));
        navbar.add(deleteAccountButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        add(navbar, gbc);

        JLabel displayNameLabel = new JLabel("Welcome, " + displayName);
        displayNameLabel.setFont(displayNameLabel.getFont().deriveFont(Font.BOLD, 18f));
        displayNameLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(displayNameLabel, gbc);

        JLabel displayAccNumbLabel = new JLabel("Account Number: " + account.getAccountNumber());
        JLabel displayBalanceLabel = new JLabel("Balance: " + account.getBalance());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setOpaque(false);

        JPanel accNumberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        accNumberPanel.setOpaque(false);
        accNumberPanel.add(displayAccNumbLabel);
        accNumberPanel.add(copyButton);

        JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        balancePanel.setOpaque(false);
        balancePanel.add(displayBalanceLabel);

        leftPanel.add(accNumberPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        leftPanel.add(balancePanel);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(requestLoanButton);
        rightPanel.add(repayLoanButton);
        rightPanel.add(moneyTransferButton);

        JPanel containerPanel = new JPanel(new GridBagLayout());
        containerPanel.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.LINE_START;
        containerPanel.add(leftPanel, c);

        c.gridx = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.LINE_END;
        containerPanel.add(rightPanel, c);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(containerPanel, gbc);

        logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "login"));

        changeEmailButton.addActionListener(e -> {
            mainPanel.add(new ChangeEmailPanel(frame, cardLayout, mainPanel, user), "change email");
            cardLayout.show(mainPanel, "change email");
        });

        changePasswordButton.addActionListener(e -> {
            mainPanel.add(new ChangePasswordPanel(frame, cardLayout, mainPanel, user), "change password");
            cardLayout.show(mainPanel, "change password");
        });

        changePhoneButton.addActionListener(e -> {
            mainPanel.add(new ChangePhonePanel(frame, cardLayout, mainPanel, user), "change phone");
            cardLayout.show(mainPanel, "change phone");
        });

        changeAddressButton.addActionListener(e -> {
            mainPanel.add(new ChangeAddressPanel(frame, cardLayout, mainPanel, user), "change address");
            cardLayout.show(mainPanel, "change address");
        });

        deleteAccountButton.addActionListener(e -> {
            mainPanel.add(new DeleteAccountPanel(frame, cardLayout, mainPanel, user), "delete account");
            cardLayout.show(mainPanel, "delete account");
        });

        copyButton.addActionListener(e -> {
            String accountNumber = account.getAccountNumber();
            StringSelection selection = new StringSelection(accountNumber);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
            JOptionPane.showMessageDialog(null, "Copied");
        });

        requestLoanButton.addActionListener(e -> {
            mainPanel.add(new RequestLoanPanel(frame, cardLayout, mainPanel, user), "request loan");
            cardLayout.show(mainPanel, "request loan");
        });

        repayLoanButton.addActionListener(e -> {
            mainPanel.add(new RepayLoanPanel(frame, cardLayout, mainPanel, user), "repay loan");
            cardLayout.show(mainPanel, "repay loan");
        });

        moneyTransferButton.addActionListener(e -> {
            mainPanel.add(new DeleteAccountPanel(frame, cardLayout, mainPanel, user), "money transfer");
            cardLayout.show(mainPanel, "money transfer");
        });


    }

    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;
    }
}
