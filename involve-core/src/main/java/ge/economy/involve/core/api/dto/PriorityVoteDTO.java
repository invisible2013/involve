package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NINO on 10/4/2017.
 */
public class PriorityVoteDTO {

    private Integer id;
    private Integer priorityId;
    private Integer answerId;
    private Integer userId;
    private String userName;
    private String clientUID;
    private String ipAddress;


    public static PriorityVoteDTO translate(Record record) {
        PriorityVoteDTO dto = new PriorityVoteDTO();
        dto.setId(record.getValue(Tables.PRIORITY_VOTE.ID));
        dto.setPriorityId(record.getValue(Tables.PRIORITY_VOTE.PRIORITY_ID));
        dto.setAnswerId(record.getValue(Tables.PRIORITY_VOTE.ANSWER_ID));
        dto.setUserId(record.getValue(Tables.PRIORITY_VOTE.USER_ID));
        if (dto.getUserId() != null && dto.getUserId() != 0) {
            dto.setUserName(record.getValue(Tables.USERS.ORG_NAME) == null ? record.getValue(Tables.USERS.FIRST_NAME) + " " + record.getValue(Tables.USERS.LAST_NAME) : record.getValue(Tables.USERS.ORG_NAME));
        }
        dto.setClientUID(record.getValue(Tables.PRIORITY_VOTE.CLIENT_UID));
        dto.setIpAddress(record.getValue(Tables.PRIORITY_VOTE.IP_ADDRESS));
        return dto;
    }


    public static List<PriorityVoteDTO> translateArray(List<Record> records) {
        ArrayList<PriorityVoteDTO> list = new ArrayList<PriorityVoteDTO>();
        for (Record record : records) {
            list.add(PriorityVoteDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getClientUID() {
        return clientUID;
    }

    public void setClientUID(String clientUID) {
        this.clientUID = clientUID;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
