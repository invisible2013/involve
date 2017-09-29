package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.InitiateDTO;
import ge.economy.involve.core.api.dto.InitiatedIssueDTO;
import ge.economy.involve.core.api.request.AddInitiateRequest;
import ge.economy.involve.core.api.request.AddVoteRequest;
import ge.economy.involve.core.dao.InitiateDAO;
import ge.economy.involve.core.dao.VoteDAO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.InitiateRecord;
import ge.economy.involve.database.database.tables.records.InitiatedIssueRecord;
import ge.economy.involve.database.database.tables.records.SessionPollVoteRecord;
import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteDAO voteDAO;
    @Autowired
    private DSLContext dslContext;

    private Logger logger = Logger.getLogger(VoteService.class);

    public InitiateDTO saveSessionPollVote(AddVoteRequest request) {
        boolean newRecord = false;
        SessionPollVoteRecord record = null;
        if (request.getId() != null) {
            record = voteDAO.getPollVoteObjectById(request.getId());
        }

        if (record == null) {
            record = (SessionPollVoteRecord) dslContext.newRecord(Tables.SESSION_POLL_VOTE);
            newRecord = true;
        }

        record.setUserId(request.getUserId());
        record.setReformId(request.getReformId());
        record.setSessionId(request.getSessionId());
        record.setQuestionId(request.getQuestionId());
        record.setAnswerId(request.getAnswerId());
        record.setAnswerNote(request.getAnswerNote());
        record.setSessionVoteId(request.getSessionVoteId());
        record.setIpAddress(request.getIpAddress());
        record.setClientUid(request.getClientUID());
        if (newRecord) {
            record.setCreateDate(new Date());
            record.store();
        } else {
            record.update();
        }

        return null;
    }

    public HashMap<String, Object> getVotes(int start, int limit) {
        new HashMap();
        HashMap<String, Object> resultMap = new HashMap();
        HashMap<String, Object> map = voteDAO.getVotes(start, limit);
        List<InitiateDTO> items = InitiateDTO.translateArray((List) map.get("list"));
        resultMap.put("list", items);
        resultMap.put("size", map.get("size"));
        return resultMap;
    }

    public void deleteSessionPollVote(int itemId) {
        voteDAO.deleteSessionPollVote(itemId);
    }


}
