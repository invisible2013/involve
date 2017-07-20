package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;

import java.util.List;

import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectField;
import org.springframework.stereotype.Repository;

@Repository
public class ParameterDAO extends AbstractDAO {
    public List<Record> getReformTypes() {
        return
                this.dslContext.select().from(Tables.REFORM_TYPE).orderBy(Tables.REFORM_TYPE.NAME).fetch();
    }


    public void deleteReformFile(int itemId) {
        this.dslContext.deleteFrom(Tables.REFORM_FILE).where(Tables.REFORM_FILE.ID.eq(itemId)).execute();
    }
}
