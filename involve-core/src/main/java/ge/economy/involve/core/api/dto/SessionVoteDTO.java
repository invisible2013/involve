package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class SessionVoteDTO {

    private Integer id;
    private Integer sessionId;
    private Integer userId;
    private String mail;
    private Boolean agreed;
    private Integer rangeId;
    private String profession;
    private String fieldOfActivity;


    public static SessionVoteDTO translate(Record record) {
        SessionVoteDTO dto = new SessionVoteDTO();
        dto.setId(record.getValue(Tables.SESSION_VOTE.ID));
        dto.setSessionId(record.getValue(Tables.SESSION_VOTE.SESSION_ID));
        dto.setUserId(record.getValue(Tables.SESSION_VOTE.SESSION_ID));
        dto.setMail(record.getValue(Tables.SESSION_VOTE.MAIL));
        dto.setAgreed(record.getValue(Tables.SESSION_VOTE.AGREED));
        dto.setRangeId(record.getValue(Tables.SESSION_VOTE.RANGE_ID));
        dto.setProfession(record.getValue(Tables.SESSION_VOTE.PROFESSION));
        dto.setFieldOfActivity(record.getValue(Tables.SESSION_VOTE.FIELD_OF_ACTIVITY));
        return dto;
    }


    public static List<SessionVoteDTO> translateArray(List<Record> records) {
        ArrayList<SessionVoteDTO> list = new ArrayList<SessionVoteDTO>();
        for (Record record : records) {
            list.add(SessionVoteDTO.translate(record));
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getAgreed() {
        return agreed;
    }

    public void setAgreed(Boolean agreed) {
        this.agreed = agreed;
    }

    public Integer getRangeId() {
        return rangeId;
    }

    public void setRangeId(Integer rangeId) {
        this.rangeId = rangeId;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getFieldOfActivity() {
        return fieldOfActivity;
    }

    public void setFieldOfActivity(String fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }
}
