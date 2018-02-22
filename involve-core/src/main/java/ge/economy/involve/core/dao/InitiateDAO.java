package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.*;
import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectOnConditionStep;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class InitiateDAO extends AbstractDAO {


    public HashMap<String, Object> getInitiate(int start, int limit) {
        SelectOnConditionStep<Record> selectConditionStep =
                dslContext.select()
                        .from(Tables.INITIATE)
                        .join(Tables.SPHERE)
                        .on(Tables.INITIATE.SPHERE_ID.eq(Tables.SPHERE.ID))
                        .leftJoin(Tables.USERS)
                        .on(Tables.INITIATE.USER_ID.eq(Tables.USERS.ID));

        selectConditionStep.where();
        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.INITIATE.ID.desc()).limit(limit).offset(start);

        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", recordSize);
        return map;
    }

    public InitiateRecord getInitiateObjectById(int id) {
        return dslContext.fetchOne(Tables.INITIATE, Tables.INITIATE.ID.eq(id));
    }

    public Record getInitiateById(int id) {
        return dslContext.select()
                .from(Tables.INITIATE)
                .join(Tables.SPHERE).on(Tables.INITIATE.SPHERE_ID.eq(Tables.SPHERE.ID))
                .join(Tables.USERS).on(Tables.INITIATE.USER_ID.eq(Tables.USERS.ID))
                .where(Tables.INITIATE.ID.eq(id)).fetchOne();
    }

    public void deleteInitiate(int itemId) {
        dslContext.deleteFrom(Tables.INITIATE).where(Tables.INITIATE.ID.eq(itemId)).execute();
    }


    public HashMap<String, Object> getIssue(int start, int limit) {
        SelectOnConditionStep<Record> selectConditionStep =
                dslContext.select()
                        .from(Tables.INITIATED_ISSUE)
                        .join(Tables.USERS).on(Tables.INITIATED_ISSUE.CREATOR_ID.eq(Tables.USERS.ID));

        selectConditionStep.where();
        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.INITIATED_ISSUE.ID.desc()).limit(limit).offset(start);

        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", recordSize);
        return map;
    }

    public int getInitiatedIssueVoteCount(int initiatedIssueId) {
        return dslContext.selectCount().
                from(Tables.INITIATIVE_VOTE).
                where(Tables.INITIATIVE_VOTE.INITIATED_ISSUE_ID.eq(initiatedIssueId)).
                and(Tables.INITIATIVE_VOTE.AGREED.eq(true)).
                fetchOne().into(Integer.class);
    }

    public InitiatedIssueRecord getInitiatedIssueObjectById(int id) {
        return dslContext.fetchOne(Tables.INITIATED_ISSUE, Tables.INITIATED_ISSUE.ID.eq(id));
    }

    public InitiativeVoteRecord getInitiativeVoteObjectById(int id) {
        return dslContext.fetchOne(Tables.INITIATIVE_VOTE, Tables.INITIATIVE_VOTE.ID.eq(id));
    }

    public PriorityVoteRecord getPriorityVoteObjectById(int id) {
        return dslContext.fetchOne(Tables.PRIORITY_VOTE, Tables.PRIORITY_VOTE.ID.eq(id));
    }

    public void deleteIssue(int itemId) {
        dslContext.deleteFrom(Tables.INITIATED_ISSUE).where(Tables.INITIATED_ISSUE.ID.eq(itemId)).execute();
    }


    public List<Record> getSpheres() {
        return dslContext.select()
                .from(Tables.SPHERE)
                .orderBy(Tables.SPHERE.ID).fetch();
    }
}
