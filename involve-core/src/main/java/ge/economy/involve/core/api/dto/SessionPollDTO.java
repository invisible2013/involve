package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class SessionPollDTO {

    private Integer id;
    private Integer sessionId;
    private Integer orderByNumber;
    private String name;
    private List<PollAnswerDTO> answers;


    public static SessionPollDTO translate(Record record) {
        SessionPollDTO dto = new SessionPollDTO();
        dto.setId(record.getValue(Tables.SESSION_POLL.ID));
        dto.setSessionId(record.getValue(Tables.SESSION_POLL.SESSION_ID));
        dto.setName(record.getValue(Tables.SESSION_POLL.NAME));
        dto.setOrderByNumber(record.getValue(Tables.SESSION_POLL.ORDER_BY_NUMBER));
        return dto;
    }


    public static List<SessionPollDTO> translateArray(List<Record> records) {
        ArrayList<SessionPollDTO> list = new ArrayList<SessionPollDTO>();
        for (Record record : records) {
            list.add(SessionPollDTO.translate(record));
        }
        return list;
    }

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
