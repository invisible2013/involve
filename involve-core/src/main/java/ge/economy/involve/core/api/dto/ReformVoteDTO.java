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
public class ReformVoteDTO {

    private Integer id;
    private Integer reformId;
    private Integer userId;
    private String mail;
    private Boolean agreed;
    private Integer rangeId;
    private String profession;
    private String fieldOfActivity;
    private String clientUid;
    private String firstName;
    private String lastName;
    private String orgName;
    private String reformName;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date createDate;

    public static ReformVoteDTO translate(Record record) {
        ReformVoteDTO dto = new ReformVoteDTO();
        dto.setId(record.getValue(Tables.REFORM_VOTE.ID));
        dto.setReformId(record.getValue(Tables.REFORM_VOTE.REFORM_ID));
        dto.setUserId(record.getValue(Tables.REFORM_VOTE.USER_ID));
        dto.setClientUid(record.getValue(Tables.REFORM_VOTE.CLIENT_UID));
        dto.setMail(record.getValue(Tables.REFORM_VOTE.MAIL));
        dto.setAgreed(record.getValue(Tables.REFORM_VOTE.AGREED));
        dto.setRangeId(record.getValue(Tables.REFORM_VOTE.RANGE_ID));
        dto.setProfession(record.getValue(Tables.REFORM_VOTE.PROFESSION));
        dto.setFieldOfActivity(record.getValue(Tables.REFORM_VOTE.FIELD_OF_ACTIVITY));
        dto.setCreateDate(record.getValue(Tables.REFORM_VOTE.CREATE_DATE));
        return dto;
    }

    public static ReformVoteDTO translate2(Record record) {
        ReformVoteDTO dto = new ReformVoteDTO();
        dto.setId(record.getValue(Tables.REFORM_VOTE.ID));
        dto.setReformId(record.getValue(Tables.REFORM_VOTE.REFORM_ID));
        dto.setUserId(record.getValue(Tables.REFORM_VOTE.USER_ID));
        dto.setClientUid(record.getValue(Tables.REFORM_VOTE.CLIENT_UID));
        dto.setMail(record.getValue(Tables.REFORM_VOTE.MAIL));
        dto.setAgreed(record.getValue(Tables.REFORM_VOTE.AGREED));
        dto.setRangeId(record.getValue(Tables.REFORM_VOTE.RANGE_ID));
        dto.setProfession(record.getValue(Tables.REFORM_VOTE.PROFESSION));
        dto.setFieldOfActivity(record.getValue(Tables.REFORM_VOTE.FIELD_OF_ACTIVITY));
        dto.setCreateDate(record.getValue(Tables.REFORM_VOTE.CREATE_DATE));
        dto.setReformName(record.getValue(Tables.REFORM.NAME));
        if (dto.getUserId() != null && dto.getUserId() != 0) {
            dto.setFirstName(record.getValue(Tables.USERS.FIRST_NAME));
            dto.setLastName(record.getValue(Tables.USERS.LAST_NAME));
            dto.setOrgName(record.getValue(Tables.USERS.ORG_NAME));
        }
        return dto;
    }


    public static List<ReformVoteDTO> translateArray(List<Record> records) {
        ArrayList<ReformVoteDTO> list = new ArrayList<ReformVoteDTO>();
        for (Record record : records) {
            list.add(ReformVoteDTO.translate(record));
        }
        return list;
    }

    public static List<ReformVoteDTO> translateArray2(List<Record> records) {
        ArrayList<ReformVoteDTO> list = new ArrayList<ReformVoteDTO>();
        for (Record record : records) {
            list.add(ReformVoteDTO.translate2(record));
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

    public String getClientUid() {
        return clientUid;
    }

    public void setClientUid(String clientUid) {
        this.clientUid = clientUid;
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

    public String getReformName() {
        return reformName;
    }

    public void setReformName(String reformName) {
        this.reformName = reformName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
