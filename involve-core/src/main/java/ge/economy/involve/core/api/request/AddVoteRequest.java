package ge.economy.involve.core.api.request;

import ge.economy.involve.core.api.dto.QuestionAnswer;

import java.util.List;

/**
 * Created by nl on 7/20/2016.
 */
public class AddVoteRequest {

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
    private List<QuestionAnswer> questionAnswerList;

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

    public List<QuestionAnswer> getQuestionAnswerList() {
        return questionAnswerList;
    }

    public void setQuestionAnswerList(List<QuestionAnswer> questionAnswerList) {
        this.questionAnswerList = questionAnswerList;
    }
}
