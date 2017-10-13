package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.*;
import ge.economy.involve.core.api.request.AddInitiateRequest;
import ge.economy.involve.core.api.request.AddSessionVoteRequest;
import ge.economy.involve.core.api.request.AddVoteRequest;
import ge.economy.involve.core.dao.InitiateDAO;
import ge.economy.involve.core.dao.ReformDAO;
import ge.economy.involve.core.dao.VoteDAO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.SessionPollVote;
import ge.economy.involve.database.database.tables.records.InitiateRecord;
import ge.economy.involve.database.database.tables.records.InitiatedIssueRecord;
import ge.economy.involve.database.database.tables.records.SessionPollVoteRecord;
import ge.economy.involve.database.database.tables.records.SessionVoteRecord;
import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class VoteService {
    @Autowired
    private VoteDAO voteDAO;
    @Autowired
    private ReformDAO reformDAO;
    @Autowired
    private DSLContext dslContext;

    private Logger logger = Logger.getLogger(VoteService.class);

    public ParamPojo saveSessionPollVote(AddVoteRequest request) {

        ParamPojo pojo = new ParamPojo();
        int index = 0;
        for (QuestionAnswer q : request.getQuestionAnswerList()) {
            SessionPollVoteRecord record = (SessionPollVoteRecord) dslContext.newRecord(Tables.SESSION_POLL_VOTE);

            record.setUserId(request.getUserId());
            record.setReformId(request.getReformId());
            record.setSessionId(request.getSessionId());
            record.setQuestionId(q.getQuestionId());
            record.setAnswerId(q.getAnswerId());
            record.setAnswerNote(q.getAnswerNote());
            record.setSessionVoteId(request.getSessionVoteId());
            record.setIpAddress(request.getIpAddress());
            record.setClientUid(request.getClientUID());
            record.setCreateDate(new Date());
            record.store();
            if (index == 0 && request.getSessionVoteId() == 0) {
                pojo.setSessionVoteId(record.getId());
                index++;
            }
            if (request.getSessionVoteId() == 0) {
                record.setSessionVoteId(pojo.getSessionVoteId());
                record.update();
            }
        }
        return pojo;
    }


    public ParamPojo saveSessionVote(AddSessionVoteRequest request) {
        boolean newRecord = false;
        SessionVoteRecord record = null;
        if (request.getId() != null) {
            record = voteDAO.getSessionVoteObjectById(request.getId());
        }

        if (record == null) {
            record = (SessionVoteRecord) dslContext.newRecord(Tables.SESSION_VOTE);
            newRecord = true;
        }

        record.setUserId(request.getUserId());
        record.setSessionId(request.getSessionId());
        record.setAgreed(request.getAgreed());
        record.setUserId(request.getUserId());
        if (newRecord) {
            record.setCreateDate(new Date());
            record.store();
        } else {
            record.update();
        }
        ParamPojo pojo = new ParamPojo();
        pojo.setSessionVoteId(record.getId());
        if (request.getSessionVoteId() > 0) {
            voteDAO.updateSessionPollVote(request.getSessionVoteId(), record.getId());
        }
        float allCount = reformDAO.getSessionAllVoteCount(request.getSessionId());
        float yesCount = reformDAO.getSessionVoting(request.getSessionId(), true);
        pojo.setYesPercent((int) (yesCount / allCount * 100));
        pojo.setNoPercent(100 - pojo.getYesPercent());
        return pojo;
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
