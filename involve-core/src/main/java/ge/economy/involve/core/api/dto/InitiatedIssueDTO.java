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
public class InitiatedIssueDTO {

    private Integer id;
    private Integer userId;
    private Integer groupId;
    private Integer sphereId;
    private String sphereName;
    private String description;
    private String userName;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date createDate;


    public static InitiatedIssueDTO translate(Record record) {
        InitiatedIssueDTO dto = new InitiatedIssueDTO();
        dto.setId(record.getValue(Tables.INITIATED_ISSUE.ID));
        dto.setUserId(record.getValue(Tables.INITIATED_ISSUE.CREATOR_ID));
        dto.setDescription(record.getValue(Tables.INITIATED_ISSUE.DESCRIPTION));
        dto.setUserName(record.getValue(Tables.USERS.FIRST_NAME) + " " + record.getValue(Tables.USERS.LAST_NAME) + " " + record.getValue(Tables.USERS.ORG_NAME));
        dto.setCreateDate(record.getValue(Tables.INITIATED_ISSUE.CREATE_DATE));
        return dto;
    }


    public static List<InitiatedIssueDTO> translateArray(List<Record> records) {
        ArrayList<InitiatedIssueDTO> list = new ArrayList<InitiatedIssueDTO>();
        for (Record record : records) {
            list.add(InitiatedIssueDTO.translate(record));
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
}
