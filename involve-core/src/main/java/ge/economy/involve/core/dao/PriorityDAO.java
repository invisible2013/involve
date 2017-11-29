package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.InitiateRecord;
import ge.economy.involve.database.database.tables.records.InitiatedIssueRecord;
import ge.economy.involve.database.database.tables.records.PriorityRecord;
import org.jooq.Record;
import org.jooq.SelectOnConditionStep;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class PriorityDAO extends AbstractDAO {


    public HashMap<String, Object> getPriorities(int start, int limit) {
        SelectOnConditionStep<Record> selectConditionStep =
                (SelectOnConditionStep<Record>) dslContext.select()
                        .from(Tables.PRIORITY)
                        .join(Tables.USERS).on(Tables.PRIORITY.USER_ID.eq(Tables.USERS.ID));

        selectConditionStep.where();
        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.PRIORITY.ID.desc()).limit(limit).offset(start);

        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", recordSize);
        return map;
    }

    public PriorityRecord getPriorityObjectById(int id) {
        return dslContext.fetchOne(Tables.PRIORITY, Tables.PRIORITY.ID.eq(id));
    }

    public void deletePriority(int itemId) {
        dslContext.deleteFrom(Tables.PRIORITY).where(Tables.PRIORITY.ID.eq(itemId)).execute();
    }

}
