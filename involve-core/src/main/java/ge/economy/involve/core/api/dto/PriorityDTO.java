package ge.economy.involve.core.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.economy.involve.core.jsonhelper.JsonDateSerializeSupport;
import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class PriorityDTO {

    private Integer id;
    private Integer userId;
    private Integer answerId;
    private Integer answerCount;
    private String name;
    private String userName;
    private String description;
    private List<PriorityVotePojo> priorityVoteResult;
    private List<PriorityVoteDTO> priorityVotes;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date createDate;


    public static PriorityDTO translate(Record record) {
        PriorityDTO dto = new PriorityDTO();
        dto.setId(record.getValue(Tables.PRIORITY.ID));
        dto.setName(record.getValue(Tables.PRIORITY.NAME));
        dto.setUserId(record.getValue(Tables.PRIORITY.USER_ID));
        dto.setUserName(record.getValue(Tables.USERS.ORG_NAME) == null ? record.getValue(Tables.USERS.FIRST_NAME) + " " + record.getValue(Tables.USERS.LAST_NAME) : record.getValue(Tables.USERS.ORG_NAME));
        dto.setDescription(record.getValue(Tables.PRIORITY.DESCRIPTION));
        dto.setCreateDate(record.getValue(Tables.PRIORITY.CREATE_DATE));
        return dto;
    }


    public static List<PriorityDTO> translateArray(List<Record> records) {
        ArrayList<PriorityDTO> list = new ArrayList<PriorityDTO>();
        for (Record record : records) {
            list.add(PriorityDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public List<PriorityVotePojo> getPriorityVoteResult() {
        return priorityVoteResult;
    }

    public void setPriorityVoteResult(List<PriorityVotePojo> priorityVoteResult) {
        this.priorityVoteResult = priorityVoteResult;
    }

    public List<PriorityVoteDTO> getPriorityVotes() {
        return priorityVotes;
    }

    public void setPriorityVotes(List<PriorityVoteDTO> priorityVotes) {
        this.priorityVotes = priorityVotes;
    }

    public Integer getAnswerCount() {
        return answerCount;
    }

    public void setAnswerCount(Integer answerCount) {
        this.answerCount = answerCount;
    }
}
