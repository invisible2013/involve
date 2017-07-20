package ge.economy.involve.utils;

/**
 * Created by nino on 7/15/16.
 */
public class DateTimeUtils {

    public static int TWO_WEEK_IN_DAYS = 14;

    public static int daysBetween(long t1, long t2) {
        return (int) ((t2 - t1) / (1000 * 60 * 60 * 24));
    }
}
