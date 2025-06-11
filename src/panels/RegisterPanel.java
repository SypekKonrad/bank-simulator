package panels;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    public RegisterPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel) {

        // defining layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // declaring buttons/labels/components etc
        JLabel label1 = new JLabel("choose enterprise/private");
        JButton enterpriseButton = new JButton("Enterprise Account");
        JButton privateButton = new JButton("Private Account");
        JButton backButton = new JButton("cancel");

        // layout
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // label1 layout
        gbc.gridx = 0; gbc.gridy = 0;
        add(label1, gbc);

        // enterpriseButton layout
        gbc.gridx = -1; gbc.gridy = 1;
        add(enterpriseButton, gbc);

        // privateButton layout
        gbc.gridx = 1; gbc.gridy = 1;
        add(privateButton, gbc);

        // backButton layout
        gbc.gridx = 0; gbc.gridy = 3;
        add(backButton, gbc);

        // action listeners
        backButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "login");
        });

        enterpriseButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "enterprise register");
        });

        privateButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "private register");
        });
    }
}