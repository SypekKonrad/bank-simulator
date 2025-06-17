import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.MatteBorder;
import java.awt.*;
import javax.swing.SwingUtilities;


import panels.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {

        // setup jframe
        JFrame frame = new JFrame("bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // rozmiar jframe
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);


        //todo jak zrobie user register to user auth musi brac dane z pliku

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        mainPanel.add(new LoginPanel(frame, cardLayout, mainPanel), "login");
        mainPanel.add(new RegisterPanel(frame, cardLayout, mainPanel), "register");
        mainPanel.add(new EnterpriseRegisterPanel(frame, cardLayout, mainPanel), "enterprise register");
        mainPanel.add(new PrivateRegisterPanel(frame, cardLayout, mainPanel), "private register");
//        mainPanel.add(new AccountPanel(frame, cardLayout, mainPanel, loggedInUser), "account dashboard");


        // to tu musi zostac
        frame.add(mainPanel);
        frame.setVisible(true);
    }

}


