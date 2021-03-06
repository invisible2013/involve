package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.InitiatedIssueRecord;
import ge.economy.involve.database.database.tables.records.ReformVoteRecord;
import ge.economy.involve.database.database.tables.records.SessionPollVoteRecord;
import org.jooq.Record;
import org.jooq.SelectOnConditionStep;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class VoteDAO extends AbstractDAO {


    public HashMap<String, Object> getVotes(int start, int limit) {
        SelectOnConditionStep<Record> selectConditionStep =
                (SelectOnConditionStep<Record>) dslContext.select()
                        .from(Tables.SESSION_POLL_VOTE);

        selectConditionStep.where();
        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.SESSION_POLL_VOTE.ID.desc()).limit(limit).offset(start);

        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", recordSize);
        return map;
    }

    public SessionPollVoteRecord getPollVoteObjectById(int id) {
        return dslContext.fetchOne(Tables.SESSION_POLL_VOTE, Tables.SESSION_POLL_VOTE.ID.eq(id));
    }

    public ReformVoteRecord getReformVoteObjectById(int id) {
        return dslContext.fetchOne(Tables.REFORM_VOTE, Tables.REFORM_VOTE.ID.eq(id));
    }

    public void deleteSessionPollVote(int itemId) {
        dslContext.deleteFrom(Tables.SESSION_POLL_VOTE).where(Tables.SESSION_POLL_VOTE.ID.eq(itemId)).execute();
    }


}
