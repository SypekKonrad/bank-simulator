package panels;

import auth.UserAuth;
import models.*;
import utils.*;
import exceptions.*;
import javax.swing.*;
import java.awt.*;
import models.User;

public class LoginPanel extends JPanel {

    public LoginPanel(JFrame frame, CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel loginLabel = new JLabel("Login:");
        JTextField loginField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton submitButton = new JButton("Login");
        JButton createAccountButton = new JButton("Create Account");

        // layout
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // row 1 - login
        gbc.gridx = 0; gbc.gridy = 0;
        add(loginLabel, gbc);
        gbc.gridx = 1;
        add(loginField, gbc);

        // row 2 - passwd
        gbc.gridx = 0; gbc.gridy = 1;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // row 3 - buttons
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(submitButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(createAccountButton, gbc);


        submitButton.addActionListener(e -> {
            try {
                BankData data = DataManager.loadData();

                String login = loginField.getText().trim();
                String password = new String(passwordField.getPassword());



                if (login.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter both login and password.", "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (data.getUsers().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No users registered yet");
                    return;
                }



                UserAuth userAuth = new UserAuth(data);
                User loggedInUser = userAuth.authenticate(login, password);
                Account account = null;

                for (Account a : data.getAccounts()) {
                    if (a.getOwner().getLogin().equals(loggedInUser.getLogin())) {
                        account = a;
                        break;
                    }
                }

                if (account == null) {
                    JOptionPane.showMessageDialog(frame, "Account not found for user!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                JOptionPane.showMessageDialog(frame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

                mainPanel.add(new AccountPanel(frame, cardLayout, mainPanel, loggedInUser, account), "account");
                cardLayout.show(mainPanel, "account");

            } catch (InvalidCredentialsException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);

            } catch (DataLoadException ex) {
                JOptionPane.showMessageDialog(frame, "Error loading user data: " + ex.getMessage(), "Data Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Unexpected error: " + ex.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });


        createAccountButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "register");
        });


    }
}


//                    System.out.printf("Stored login=[%s], Stored password=[%s]%n", user.getLogin().trim(), user.getPassword().trim());


//        UserAuth userAuth = new UserAuth();

//        submitButton.addActionListener(e -> {
//            String login = loginField.getText();
//            String password = new String(passwordField.getPassword());
//
//            if (userAuth.authenticate(login, password)) {
//                JOptionPane.showMessageDialog(frame, "ok");
//            } else {
//                JOptionPane.showMessageDialog(frame, "wrong login/passwd.");
//            }
//        });
