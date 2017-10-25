package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.*;
import ge.economy.involve.core.api.request.AddInitiateRequest;
import ge.economy.involve.core.api.request.AddReformVoteRequest;
import ge.economy.involve.core.api.request.AddVoteRequest;
import ge.economy.involve.core.dao.InitiateDAO;
import ge.economy.involve.core.dao.ReformDAO;
import ge.economy.involve.core.dao.VoteDAO;
import ge.economy.involve.core.execptions.ReformHasVoteAlreadyException;
import ge.economy.involve.core.execptions.SessionAlreadyHasVoteException;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.SessionPollVote;
import ge.economy.involve.database.database.tables.records.InitiateRecord;
import ge.economy.involve.database.database.tables.records.InitiatedIssueRecord;
import ge.economy.involve.database.database.tables.records.ReformVoteRecord;
import ge.economy.involve.database.database.tables.records.SessionPollVoteRecord;
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

    public ParamPojo saveSessionPollVote(AddVoteRequest request) throws SessionAlreadyHasVoteException {
        boolean hastPollVote = getSessionPollVoteByClientGuid(request.getSessionId(), request.getClientUID());
        if (hastPollVote) {
            throw new SessionAlreadyHasVoteException("თქვენ სესსის კითხვარი უკვე შევსებული გაქვთ");
        }
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
            record.setIpAddress(request.getIpAddress());
            record.setClientUid(request.getClientUID());
            record.setCreateDate(new Date());
            record.store();
            if (index == 0 && request.getSessionVoteId() == 0) {
                pojo.setSessionVoteId(record.getId());
                index++;
            }
        }
        return pojo;
    }


    public ParamPojo saveReformVote(AddReformVoteRequest request) throws ReformHasVoteAlreadyException {
        boolean hasVote = getReformVoteByClientGuid(request.getReformId(), request.getClientUID());
        if (hasVote) {
            throw new ReformHasVoteAlreadyException("თქვენ ამ რეფორმაზე ხმა უკვე მიცემული გაქვთ");
        }
        boolean newRecord = false;
        ReformVoteRecord record = null;
        if (request.getId() != null) {
            record = voteDAO.getReformVoteObjectById(request.getId());
        }

        if (record == null) {
            record = (ReformVoteRecord) dslContext.newRecord(Tables.REFORM_VOTE);
            newRecord = true;
        }

        record.setUserId(request.getUserId());
        record.setReformId(request.getReformId());
        record.setAgreed(request.getAgreed());
        record.setUserId(request.getUserId());
        record.setClientGuid(request.getClientUID());
        if (newRecord) {
            record.setCreateDate(new Date());
            record.store();
        } else {
            record.update();
        }
        ParamPojo pojo = new ParamPojo();
        pojo.setSessionVoteId(record.getId());
        float allCount = reformDAO.getReformAllVoteCount(request.getReformId());
        float yesCount = reformDAO.getReformVoting(request.getReformId(), true);
        pojo.setYesPercent((int) (yesCount / allCount * 100));
        pojo.setNoPercent(100 - pojo.getYesPercent());
        return pojo;
    }

    public boolean getReformVoteByClientGuid(int reformId, String clientGuid) {
        return (reformDAO.getReformVoteByClient(reformId, clientGuid) > 0) ? true : false;
    }

    public boolean getSessionPollVoteByClientGuid(int sessionId, String clientGuid) {
        return (reformDAO.getSessionPollVoteByClient(sessionId, clientGuid) > 0) ? true : false;
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
