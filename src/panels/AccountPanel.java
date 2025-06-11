package panels;

import javax.swing.*;
import java.awt.*;

public class AccountPanel extends JPanel {
    public AccountPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel) {

        // defining layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // declaring buttons/labels/components etc
        JLabel label1 = new JLabel("account dashboard");
        JButton logoutButton = new JButton("logout");

        // layout
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // label1 layout
        gbc.gridx = 0; gbc.gridy = 0;
        add(label1, gbc);

        // backButton layout
        gbc.gridx = 0; gbc.gridy = 1;
        add(logoutButton, gbc);


        // action listeners
        logoutButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "login");
        });

    }
}