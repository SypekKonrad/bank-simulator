package panels;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    public RegisterPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel) {

        // Set up main layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Components
        JLabel label1 = new JLabel("Choose Account Type", SwingConstants.CENTER);
        JButton enterpriseButton = new JButton("Enterprise Account");
        JButton privateButton = new JButton("Private Account");
        JButton backButton = new JButton("Cancel");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(label1, gbc);

        gbc.gridwidth = 1;

        JPanel buttonGroupPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonGroupPanel.add(enterpriseButton);
        buttonGroupPanel.add(privateButton);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(buttonGroupPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(backButton, gbc);

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "login"));

        enterpriseButton.addActionListener(e -> cardLayout.show(mainPanel, "enterprise register"));

        privateButton.addActionListener(e -> cardLayout.show(mainPanel, "private register"));
    }
}
