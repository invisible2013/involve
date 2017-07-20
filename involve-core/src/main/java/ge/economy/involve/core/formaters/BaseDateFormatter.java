package ge.economy.involve.core.formaters;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nino on 5/14/16.
 */
public class BaseDateFormatter {

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";

    public String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(date);
    }

    public String dateTimeToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
        return dateFormat.format(date);
    }
}
