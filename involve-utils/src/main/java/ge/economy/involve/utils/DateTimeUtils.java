package ge.economy.involve.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by nino on 7/15/16.
 */
public class DateTimeUtils {

    public static int TWO_WEEK_IN_DAYS = 14;

    public static int daysBetween(long t1, long t2) {
        return (int) ((t2 - t1) / (1000 * 60 * 60 * 24));
    }

    public static int getPastTimePercent(Date startDate, Date endDate) {
        double allDays = daysBetween(startDate.getTime(), endDate.getTime());
        double pastDays = daysBetween(startDate.getTime(), new Date().getTime());
        int pastTimePercent = ((int) ((pastDays / allDays) * 100));
        return pastTimePercent < 0 ? 0 : (pastTimePercent > 100 ? 100 : pastTimePercent);
    }

    public static Date addDays(Date startDate, int dayNumber) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, dayNumber);
        return c.getTime();
    }
}
