package ge.economy.involve.core.jsonhelper;

import ge.economy.involve.core.formaters.BaseDateFormatter;

/**
 * Created by nino on 5/5/16.
 */
public class JsonDateTimeDeserializeSupport extends AbstractDateDeSerializeSupport {

    @Override
    String getDateFormat() {
        return BaseDateFormatter.DATE_TIME_FORMAT;
    }
}
