package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;


import java.util.HashMap;
import java.util.List;

import ge.economy.involve.database.database.tables.records.PriorityRecord;
import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectField;
import org.jooq.SelectOnConditionStep;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDAO
        extends AbstractDAO {
    public HashMap<String, Object> getPriority(int start, int limit) {
        SelectOnConditionStep<Record> selectConditionStep =
                this.dslContext.select()
                        .from(Tables.PRIORITY)
                        .join(Tables.PRIORITY_ITEM).on(Tables.PRIORITY.ID.eq(Tables.PRIORITY_ITEM.PRIORITY_ID));

        selectConditionStep.where();

        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.PRIORITY.ID.desc()).limit(limit).offset(start);

        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));
        return map;
    }

    public Record getPriorityById(int id) {
        return this.dslContext.select()
                .from(Tables.PRIORITY)
                .where(Tables.PRIORITY.ID.eq(id)).fetchOne();
    }

    public PriorityRecord getPriorityObjectById(int id) {
        return (PriorityRecord) this.dslContext.fetchOne(Tables.PRIORITY, Tables.PRIORITY.ID.eq(id));
    }


    public void deletePriority(int itemId) {
        this.dslContext.deleteFrom(Tables.PRIORITY).where(Tables.PRIORITY.ID.eq(itemId)).execute();
    }


}
