package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class SessionPollVoteDTO {

    private Integer id;
    private Integer reformId;
    private Integer sessionId;
    private Integer questionId;
    private Integer answerId;
    private String answerNote;
    private Integer sessionVoteId;
    private Integer userId;
    private String ipAddress;
    private String clientUID;


    public static SessionPollVoteDTO translate(Record record) {
        SessionPollVoteDTO dto = new SessionPollVoteDTO();
        dto.setId(record.getValue(Tables.SESSION_POLL_VOTE.ID));
        dto.setSessionId(record.getValue(Tables.SESSION_POLL_VOTE.SESSION_ID));
        dto.setReformId(record.getValue(Tables.SESSION_POLL_VOTE.REFORM_ID));
        dto.setQuestionId(record.getValue(Tables.SESSION_POLL_VOTE.QUESTION_ID));
        dto.setAnswerId(record.getValue(Tables.SESSION_POLL_VOTE.ANSWER_ID));
        dto.setAnswerNote(record.getValue(Tables.SESSION_POLL_VOTE.ANSWER_NOTE));
        dto.setSessionVoteId(record.getValue(Tables.SESSION_POLL_VOTE.SESSION_VOTE_ID));
        dto.setUserId(record.getValue(Tables.SESSION_POLL_VOTE.USER_ID));
        dto.setIpAddress(record.getValue(Tables.SESSION_POLL_VOTE.IP_ADDRESS));
        dto.setClientUID(record.getValue(Tables.SESSION_POLL_VOTE.CLIENT_UID));
        return dto;
    }


    public static List<SessionPollVoteDTO> translateArray(List<Record> records) {
        ArrayList<SessionPollVoteDTO> list = new ArrayList<SessionPollVoteDTO>();
        for (Record record : records) {
            list.add(SessionPollVoteDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReformId() {
        return reformId;
    }

    public void setReformId(Integer reformId) {
        this.reformId = reformId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerNote() {
        return answerNote;
    }

    public void setAnswerNote(String answerNote) {
        this.answerNote = answerNote;
    }

    public Integer getSessionVoteId() {
        return sessionVoteId;
    }

    public void setSessionVoteId(Integer sessionVoteId) {
        this.sessionVoteId = sessionVoteId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getClientUID() {
        return clientUID;
    }

    public void setClientUID(String clientUID) {
        this.clientUID = clientUID;
    }
}
