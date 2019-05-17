package ge.economy.involve.core.api.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ge.economy.involve.core.api.dto.PollAnswerDTO;
import ge.economy.involve.core.api.dto.ReformDetailDTO;
import ge.economy.involve.core.jsonhelper.JsonDateDeSerializeSupport;

import java.util.Date;
import java.util.List;

/**
 * Created by nl on 7/20/2016.
 */
public class AddSessionPollRequest {

    private Integer id;
    private Integer sessionId;
    private Integer orderByNumber;
    private String name;
    private List<PollAnswerDTO> answers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PollAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<PollAnswerDTO> answers) {
        this.answers = answers;
    }

    public Integer getOrderByNumber() {
        return orderByNumber;
    }

    public void setOrderByNumber(Integer orderByNumber) {
        this.orderByNumber = orderByNumber;
    }
}
