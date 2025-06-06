import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.MatteBorder;
import java.awt.*;
import javax.swing.SwingUtilities;

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

        // render guzików i innych kompomentów

        // to tu musi zostac
        //        frame.add(footerPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

}


