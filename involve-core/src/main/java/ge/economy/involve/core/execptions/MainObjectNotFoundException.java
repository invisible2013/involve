package ge.economy.involve.core.execptions;

/**
 * Created by NINO on 8/14/2017.
 */
public class MainObjectNotFoundException extends Exception {

    public MainObjectNotFoundException() {
        super("ობიექტი არ იძებნება");
    }

    public MainObjectNotFoundException(String message) {
        super(message);
    }
}
