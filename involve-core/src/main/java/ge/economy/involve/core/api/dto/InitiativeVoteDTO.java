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
public class InitiativeVoteDTO {

    private Integer id;
    private Integer userId;
    private Integer groupId;
    private Integer sphereId;
    private String sphereName;
    private String name;
    private String description;
    private String userName;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date createDate;
    private Integer voteCount;


    public static InitiativeVoteDTO translate(Record record) {
        InitiativeVoteDTO dto = new InitiativeVoteDTO();
        dto.setId(record.getValue(Tables.INITIATIVE_VOTE.ID));
        dto.setUserId(record.getValue(Tables.INITIATIVE_VOTE.USER_ID));
        dto.setCreateDate(record.getValue(Tables.INITIATIVE_VOTE.CREATE_DATE));
        return dto;
    }


    public static List<InitiativeVoteDTO> translateArray(List<Record> records) {
        ArrayList<InitiativeVoteDTO> list = new ArrayList<InitiativeVoteDTO>();
        for (Record record : records) {
            list.add(InitiativeVoteDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSphereId() {
        return sphereId;
    }

    public void setSphereId(Integer sphereId) {
        this.sphereId = sphereId;
    }

    public String getSphereName() {
        return sphereName;
    }

    public void setSphereName(String sphereName) {
        this.sphereName = sphereName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
}
