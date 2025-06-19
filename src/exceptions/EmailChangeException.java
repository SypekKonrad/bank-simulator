package exceptions;

public class EmailChangeException extends Exception {
    public EmailChangeException(String message) {
        super(message);
    }

    public EmailChangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
