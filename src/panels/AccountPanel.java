package panels;

import javax.swing.*;
import java.awt.*;
import models.*;

public class AccountPanel extends JPanel {
    public AccountPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel, User user) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Determine the name to display
        String displayName;
        if (user instanceof PrivateUser) {
            PrivateUser privateUser = (PrivateUser) user;
            displayName = privateUser.getFirstName() + " " + privateUser.getLastName();
        } else if (user instanceof EnterpriseUser) {
            EnterpriseUser enterpriseUser = (EnterpriseUser) user;
            displayName = enterpriseUser.getCompanyName();
        } else {
            displayName = user.getLogin(); // fallback
        }

        JLabel label1 = new JLabel("Welcome, " + displayName);
        JButton logoutButton = new JButton("Logout");

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(label1, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(logoutButton, gbc);

        logoutButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "login");
        });
    }
}