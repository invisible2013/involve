package ge.economy.involve.core.jsonhelper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nino on 5/5/16.
 */
public abstract class AbstractDateDeSerializeSupport extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext dc) throws IOException, org.codehaus.jackson.JsonProcessingException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(getDateFormat());
        try {
            return dateFormat.parse(jp.getText());
        } catch (ParseException ex) {
            return null;
        }
    }

    abstract String getDateFormat();
}
