package utils;

import javax.swing.*;
import java.awt.*;

public class ExceptionUtils {


    public static void handle(Exception ex, Component parent, String contextMessage) {
        ex.printStackTrace(); // simple console log, or use logger
        JOptionPane.showMessageDialog(
                parent,
                contextMessage + ": " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}
