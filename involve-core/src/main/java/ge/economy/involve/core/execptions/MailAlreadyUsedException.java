package ge.economy.involve.core.execptions;

/**
 * Created by NINO on 8/14/2017.
 */
public class MailAlreadyUsedException extends Exception {

    public MailAlreadyUsedException() {
        super("ეს მაილი უკვე გამოყენებულია სხვა მომხმარებლის მიერ");
    }

    public MailAlreadyUsedException(String message) {
        super(message);
    }
}
