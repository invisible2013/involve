package ge.economy.involve.utils.email;

/**
 * @author nino
 */
public class EmailNotSentException extends Exception {

    public EmailNotSentException(String message) {
        super(message);
    }

    public EmailNotSentException(String message, Throwable cause) {
        super(message, cause);
    }

}