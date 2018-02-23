package ge.economy.involve.core.dao;

import ge.economy.involve.core.api.dto.PriorityVotePojo;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.InitiateRecord;
import ge.economy.involve.database.database.tables.records.InitiatedIssueRecord;
import ge.economy.involve.database.database.tables.records.PriorityRecord;
import ge.economy.involve.database.database.tables.records.PriorityVoteRecord;
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

    public List<PriorityVotePojo> getPriorityVoteResult(int priorityId) {
        return dslContext.select(Tables.PRIORITY_VOTE.ANSWER_ID, Tables.PRIORITY_VOTE.ID.count().as("answerCount")).
                from(Tables.PRIORITY_VOTE).
                where(Tables.PRIORITY_VOTE.PRIORITY_ID.eq(priorityId)).
                groupBy(Tables.PRIORITY_VOTE.ANSWER_ID).
                orderBy(Tables.PRIORITY_VOTE.ANSWER_ID.desc()).
                fetch().into(PriorityVotePojo.class);
    }

    public List<Record> getPriorityVotes(int priorityId) {
        return dslContext.select().
                from(Tables.PRIORITY_VOTE).
                leftJoin(Tables.USERS).on(Tables.PRIORITY_VOTE.USER_ID.eq(Tables.USERS.ID)).
                where(Tables.PRIORITY_VOTE.PRIORITY_ID.eq(priorityId)).
                fetch();
    }

}
