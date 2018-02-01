package ge.economy.involve.core.execptions;

/**
 * Created by NINO on 8/14/2017.
 */
public class MissingParameterException extends Exception {

    public MissingParameterException() {
        super("პარამეტრის მნიშვნელობა გადმოცემული არ არის");
    }

    public MissingParameterException(String message) {
        super(message);
    }
}
