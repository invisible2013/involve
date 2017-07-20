package ge.economy.involve.core.jsonhelper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nino on 5/5/16.
 */
public abstract class AbstractDateSerializeSupport extends JsonSerializer<Date> {

    @Override
    public void serialize(Date t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(getDateFormat());
        jg.writeString(t != null ? dateFormat.format(t) : "");
    }

    abstract String getDateFormat();

}
