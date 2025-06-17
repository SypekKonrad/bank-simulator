package panels;

import javax.swing.*;
import java.awt.*;
import models.*;

public class AccountPanel extends JPanel {

    public AccountPanel(CardLayout cardLayout, JPanel mainPanel, User user) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);

        // Determine displayName based on user type
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

        // --- Navbar panel ---
        JPanel navbar = new JPanel();
        navbar.setLayout(new BoxLayout(navbar, BoxLayout.X_AXIS));
        navbar.setBackground(new Color(30, 144, 255));  // DodgerBlue color
        navbar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create buttons
        JButton logoutButton = createNavButton("Logout");
        JButton changeEmailButton = createNavButton("Change Email");
        JButton changePasswordButton = createNavButton("Change Password");
        JButton changePhoneButton = createNavButton("Change Phone Number");
        JButton changeAddressButton = createNavButton("Update Address");
        JButton changeNameButton = createNavButton("Update Personal Data");
        JButton deleteAccountButton = createNavButton("Delete Account");

        // Add buttons with spacing
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
        navbar.add(changeNameButton);
        navbar.add(Box.createRigidArea(new Dimension(10, 0)));
        navbar.add(deleteAccountButton);

        // Place navbar at top, full width
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;  // span full width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.PAGE_START;
        add(navbar, gbc);

        // --- Welcome label below navbar ---
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

        // --- Account info labels ---
        JLabel displayAccNumbLabel = new JLabel("Account Number:");
        JLabel accNumberValueLabel = new JLabel("123456789"); // TODO: replace with real data

        JLabel displayBalanceLabel = new JLabel("Balance:");
        JLabel balanceValueLabel = new JLabel("$1,234.56"); // TODO: replace with real data

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(displayAccNumbLabel, gbc);

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        add(accNumberValueLabel, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(displayBalanceLabel, gbc);

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1;
        add(balanceValueLabel, gbc);

        // --- Action Listeners ---
        logoutButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "login");
        });

        // You can add other listeners for other buttons similarly
    }

    private JButton createNavButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 130, 180));  // SteelBlue
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;
    }
}
