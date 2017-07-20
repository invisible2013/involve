package ge.economy.involve.database.converter;

import org.jooq.Converter;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by nino on 5/5/16.
 */
public class TimestampConverter implements Converter<Timestamp, Date> {
    @Override
    public Date from(Timestamp databaseObject) {
        if (databaseObject == null) {
            return null;
        }
        return new Date(databaseObject.getTime());
    }

    @Override
    public Timestamp to(Date userObject) {
        if (userObject == null) {
            return null;
        }
        return new Timestamp(userObject.getTime());
    }

    @Override
    public Class<Timestamp> fromType() {
        return Timestamp.class;
    }

    @Override
    public Class<Date> toType() {
        return Date.class;
    }
}