package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javafx.scene.control.Tab;
import org.jooq.*;
import org.springframework.stereotype.Repository;

@Repository
public class ReformDAO extends AbstractDAO {
    public HashMap<String, Object> getReforms(int start, int limit) {
        SelectOnConditionStep<Record> selectConditionStep = this.dslContext.select()
                .from(Tables.REFORM)
                .join(Tables.REFORM_TYPE).on(Tables.REFORM.REFORM_TYPE_ID.eq(Tables.REFORM_TYPE.ID));
        selectConditionStep.where(new Condition[0]);
        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.REFORM.ORDER_BY_NUMBER.asc(), Tables.REFORM.ID.desc()).limit(limit).offset(start);
        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));
        return map;
    }

    public Record getReformById(int id) {
        return dslContext.select()
                .from(Tables.REFORM)
                .join(Tables.REFORM_TYPE)
                .on(Tables.REFORM.REFORM_TYPE_ID.eq(Tables.REFORM_TYPE.ID))
                .where(Tables.REFORM.ID.eq(id)).fetchOne();
    }

    public List<Record> getReformDetails(int reformId) {
        return dslContext.select()
                .from(Tables.REFORM_DETAIL)
                .where(Tables.REFORM_DETAIL.REFORM_ID.eq(reformId)).fetch();
    }

    public List<Record> getReformFiles(int reformId) {
        return dslContext.select()
                .from(Tables.REFORM_FILE)
                .where(Tables.REFORM_FILE.REFORM_ID.eq(reformId)).fetch();
    }

    public List<Record> getSessionFiles(int sessionId) {
        return dslContext.select()
                .from(Tables.SESSION_FILE)
                .where(Tables.SESSION_FILE.SESSION_ID.eq(sessionId)).fetch();
    }

    public HashMap<String, Object> getSessions(int start, int limit) {
        SelectOnConditionStep<Record> selectConditionStep = (SelectOnConditionStep<Record>) dslContext.select()
                .from(Tables.SESSION);
        selectConditionStep.where(new Condition[0]);
        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.SESSION.ID.desc()).limit(limit).offset(start);
        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));
        return map;
    }

    public HashMap<String, Object> getActiveSessions(int start, int limit, String orderBy) {
        Date now = new Date();
        SelectOnConditionStep<Record> selectConditionStep = (SelectOnConditionStep<Record>) dslContext.select()
                .from(Tables.SESSION);
        selectConditionStep.where(Tables.SESSION.END_DATE.greaterOrEqual(now));
        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        if (orderBy.equals("asc")) {
            selectConditionStep.orderBy(Tables.SESSION.ID.asc()).limit(limit).offset(start);
        } else {
            selectConditionStep.orderBy(Tables.SESSION.ID.desc()).limit(limit).offset(start);
        }
        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));
        return map;
    }

    public HashMap<String, Object> getCloseSessions(int start, int limit) {
        Date now = new Date();
        SelectOnConditionStep<Record> selectConditionStep = (SelectOnConditionStep<Record>) dslContext.select()
                .from(Tables.SESSION);
        selectConditionStep.where(Tables.SESSION.END_DATE.lessThan(now));
        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.SESSION.ID.desc()).limit(limit).offset(start);
        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));
        return map;
    }

    public List<Record> getReformVotes(int reformId) {
        return dslContext.select()
                .from(Tables.REFORM_VOTE)
                .where(Tables.REFORM_VOTE.REFORM_ID.eq(reformId)).fetch();
    }

    public HashMap<String, Object> getReformVotes(int reformId, int start, int limit) {
        SelectOnConditionStep<Record> selectConditionStep = this.dslContext.select()
                .from(Tables.REFORM_VOTE)
                .join(Tables.REFORM).on(Tables.REFORM.ID.eq(Tables.REFORM_VOTE.REFORM_ID))
                .leftJoin(Tables.USERS).on(Tables.USERS.ID.eq(Tables.REFORM_VOTE.USER_ID));
        selectConditionStep.where(Tables.REFORM_VOTE.REFORM_ID.eq(reformId));
        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.REFORM_VOTE.ID.desc()).limit(limit).offset(start);
        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));
        return map;
    }

    public HashMap<String, Object> getSessionVotes(int sessionId, int start, int limit) {
        SelectOnConditionStep<Record> selectConditionStep = (SelectOnConditionStep<Record>)
                dslContext.select()
                        .from(Tables.SESSION_POLL_VOTE)
                        .join(Tables.SESSION_POLL).on(Tables.SESSION_POLL_VOTE.QUESTION_ID.eq(Tables.SESSION_POLL.ID))
                        .join(Tables.POLL_ANSWER).on(Tables.SESSION_POLL_VOTE.ANSWER_ID.eq(Tables.POLL_ANSWER.ID))
                        .leftJoin(Tables.USERS).on(Tables.SESSION_POLL_VOTE.USER_ID.eq(Tables.USERS.ID))
                        .where(Tables.SESSION_POLL_VOTE.SESSION_ID.eq(sessionId));

        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.SESSION_POLL_VOTE.ID.desc()).limit(limit).offset(start);
        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));
        return map;
    }

    public List<Record> getSessionPollVote(int sessionId) {
        return dslContext.select()
                .from(Tables.SESSION_POLL_VOTE)
                .where(Tables.SESSION_POLL_VOTE.SESSION_ID.eq(sessionId)).fetch();
    }

    public List<Record> getReformSessions(int reformId) {
        return dslContext.select()
                .from(Tables.SESSION)
                .where(Tables.SESSION.REFORM_ID.eq(reformId)).fetch();
    }

    public Record getSessionById(int id) {
        return dslContext.select()
                .from(Tables.SESSION)
                .where(Tables.SESSION.ID.eq(id)).fetchOne();
    }

    public List<Record> getSessionPolls(int sessionId) {
        return dslContext.select()
                .from(Tables.SESSION_POLL)
                .where(Tables.SESSION_POLL.SESSION_ID.eq(sessionId))
                .orderBy(Tables.SESSION_POLL.ORDER_BY_NUMBER.asc(), Tables.SESSION_POLL.ID.asc())
                .fetch();
    }

    public List<Record> getPollAnswers(int pollId) {
        return dslContext.select()
                .from(Tables.POLL_ANSWER)
                .where(Tables.POLL_ANSWER.POLL_ID.eq(pollId)).fetch();
    }

    public Integer getReformAllVoteCount(int reformId) {
        return dslContext.
                selectCount()
                .from(Tables.REFORM_VOTE)
                .where(Tables.REFORM_VOTE.REFORM_ID.eq(reformId))
                .fetchOne().into(Integer.class);
    }

    public Integer getReformVoting(int reformId, boolean agreed) {
        return dslContext.
                selectCount()
                .from(Tables.REFORM_VOTE)
                .where(Tables.REFORM_VOTE.REFORM_ID.eq(reformId))
                .and(Tables.REFORM_VOTE.AGREED.eq(agreed))
                .fetchOne().into(Integer.class);
    }

    public Integer getReformVoteByClient(int reformId, String clientUid) {
        return dslContext.
                selectCount()
                .from(Tables.REFORM_VOTE)
                .where(Tables.REFORM_VOTE.REFORM_ID.eq(reformId))
                .and(Tables.REFORM_VOTE.CLIENT_UID.eq(clientUid))
                .fetchOne().into(Integer.class);
    }

    public Integer getSessionPollVoteByClient(int sessionId, String clientGuid) {
        return dslContext.
                selectCount()
                .from(Tables.SESSION_POLL_VOTE)
                .where(Tables.SESSION_POLL_VOTE.SESSION_ID.eq(sessionId))
                .and(Tables.SESSION_POLL_VOTE.CLIENT_UID.eq(clientGuid))
                .fetchOne().into(Integer.class);
    }


    public List<Record> getReformTypes() {
        return dslContext.select()
                .from(Tables.REFORM_TYPE)
                .fetch();
    }

    public ReformRecord getReformObjectById(int id) {
        return (ReformRecord) dslContext.fetchOne(Tables.REFORM, Tables.REFORM.ID.eq(id));
    }

    public SessionRecord getSessionObjectById(int id) {
        return (SessionRecord) dslContext.fetchOne(Tables.SESSION, Tables.SESSION.ID.eq(id));
    }

    public SessionPollRecord getSessionPollObjectById(int id) {
        return (SessionPollRecord) dslContext.fetchOne(Tables.SESSION_POLL, Tables.SESSION_POLL.ID.eq(id));
    }


    public ReformFileRecord getReformFileObjectById(int id) {
        return (ReformFileRecord) dslContext.fetchOne(Tables.REFORM_FILE, Tables.REFORM_FILE.ID.eq(id));
    }

    public SessionFileRecord getSessionFileObjectById(int id) {
        return (SessionFileRecord) dslContext.fetchOne(Tables.SESSION_FILE, Tables.SESSION_FILE.ID.eq(id));
    }

    public void deleteReformDetails(int reformId) {
        dslContext.deleteFrom(Tables.REFORM_DETAIL).where(Tables.REFORM_DETAIL.REFORM_ID.eq(reformId)).execute();
    }

    public void deleteReform(int itemId) {
        dslContext.deleteFrom(Tables.REFORM).where(Tables.REFORM.ID.eq(itemId)).execute();
    }

    public void deleteSession(int itemId) {
        dslContext.deleteFrom(Tables.SESSION).where(Tables.SESSION.ID.eq(itemId)).execute();
    }

    public void deleteSessionPoll(int itemId) {
        dslContext.deleteFrom(Tables.SESSION_POLL).where(Tables.SESSION_POLL.ID.eq(itemId)).execute();
    }

    public void deleteSessionPollAnswers(int itemId) {
        dslContext.deleteFrom(Tables.POLL_ANSWER).where(Tables.POLL_ANSWER.POLL_ID.eq(itemId)).execute();
    }

    public void deleteReformFile(int itemId) {
        dslContext.deleteFrom(Tables.REFORM_FILE).where(Tables.REFORM_FILE.ID.eq(itemId)).execute();
    }

    public void deleteSessionFile(int itemId) {
        dslContext.deleteFrom(Tables.SESSION_FILE).where(Tables.SESSION_FILE.ID.eq(itemId)).execute();
    }

 /*   public HashMap<String, Object> searchSportsmans(String name, String firstLetter, int sportTypeId, int genderId, int regionId, int cityId, int start, int limit) {
        SelectOnConditionStep<Record> selectConditionStep = this.dslContext.select(new SelectField[0]).from(Tables.SPORTSMAN).join(Tables.REGION).on(new Condition[]{Tables.SPORTSMAN.REGION_ID.eq(Tables.REGION.ID)}).join(Tables.CITY).on(new Condition[]{Tables.SPORTSMAN.CITY_ID.eq(Tables.CITY.ID)}).join(Tables.SPORT_TYPE).on(new Condition[]{Tables.SPORTSMAN.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID)}).join(Tables.GENDER).on(new Condition[]{Tables.SPORTSMAN.GENDER_ID.eq(Tables.GENDER.ID)}).join(Tables.SPORTSMAN_RANK).on(new Condition[]{Tables.SPORTSMAN.RANK_ID.eq(Tables.SPORTSMAN_RANK.ID)});

        selectConditionStep.where(new Condition[0]);
        if (name != null) {
            String[] names = name.split(" ");
            if (names.length <= 1) {
                selectConditionStep.and(Tables.SPORTSMAN.FIRST_NAME.like("%" + name + "%").or(Tables.SPORTSMAN.LAST_NAME.like("%" + name + "%")));
            } else {
                selectConditionStep.and(Tables.SPORTSMAN.FIRST_NAME.like("%" + names[0] + "%").or(Tables.SPORTSMAN.LAST_NAME.like("%" + names[1] + "%")));
            }
        }
        if (firstLetter != null && firstLetter.length() > 0) {
            selectConditionStep.and(Tables.SPORTSMAN.LAST_NAME.like(firstLetter + "%"));
        }
        if (sportTypeId != 0) {
            selectConditionStep.and(Tables.SPORTSMAN.SPORT_TYPE_ID.eq(Integer.valueOf(sportTypeId)));
        }
        if (genderId != 0) {
            selectConditionStep.and(Tables.SPORTSMAN.GENDER_ID.eq(Integer.valueOf(genderId)));
        }
        if (regionId != 0) {
            selectConditionStep.and(Tables.SPORTSMAN.REGION_ID.eq(Integer.valueOf(regionId)));
        }
        if (cityId != 0) {
            selectConditionStep.and(Tables.SPORTSMAN.CITY_ID.eq(Integer.valueOf(cityId)));
        }
        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.SPORTSMAN.ID.desc()).limit(limit).offset(start);

        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));
        return map;
    }*/


}
