package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.SystemPropertiesRecord;
import java.util.List;
import org.jooq.Condition;
import org.jooq.SelectField;
import org.springframework.stereotype.Repository;

@Repository
public class SystemDAO
        extends AbstractDAO
{
    public List<SystemPropertiesRecord> getSystemProperties()
    {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.SYSTEM_PROPERTIES).orderBy(Tables.SYSTEM_PROPERTIES.ID).fetch().into(SystemPropertiesRecord.class);
    }

    public SystemPropertiesRecord getPropertiesById(int id)
    {
        return

                (SystemPropertiesRecord)this.dslContext.select(new SelectField[0]).from(Tables.SYSTEM_PROPERTIES).where(new Condition[] { Tables.SYSTEM_PROPERTIES.ID.eq(Integer.valueOf(id)) }).fetchOne().into(SystemPropertiesRecord.class);
    }

    public String getValueByKey(String key)
    {
        return

                (String)this.dslContext.select(new SelectField[0]).from(Tables.SYSTEM_PROPERTIES).where(new Condition[] { Tables.SYSTEM_PROPERTIES.KEY.eq(key) }).fetchOne().getValue(Tables.SYSTEM_PROPERTIES.VALUE);
    }
}
