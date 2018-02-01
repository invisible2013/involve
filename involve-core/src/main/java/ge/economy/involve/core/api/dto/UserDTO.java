package ge.economy.involve.core.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.economy.involve.core.jsonhelper.JsonDateSerializeSupport;
import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nino on 7/10/16.
 */
public class UserDTO {


    private Integer id;
    private String firstName;
    private String lastName;
    private String orgName;
    private String idNumber;
    private String phone;
    private String email;
    private Integer genderId;
    private String genderName;
    private Integer typeId;
    private String typeName;
    private Boolean approved;
    private Integer groupId;
    private String groupName;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date createDate;
    private Integer statusId;
    private String statusName;
    private String name;
    private String token;

    public static Integer TOKEN_VALID_DAY_NUMBER = 365;

    public static Integer USER_PHISICAL = 1;
    public static Integer USER_JURIDICAL = 2;

    public static Integer USER_GROUP_ADMIN = 1;
    public static Integer USER_GROUP_USER = 2;

    public static Integer USER_STATUS_ACTIVE = 1;
    public static Integer USER_STATUS_INACTIVE = 2;

    public static UserDTO translate(Record record) {
        UserDTO dto = new UserDTO();
        dto.setId(record.getValue(Tables.USERS.ID));
        dto.setFirstName(record.getValue(Tables.USERS.FIRST_NAME));
        dto.setLastName(record.getValue(Tables.USERS.LAST_NAME));
        dto.setOrgName(record.getValue(Tables.USERS.ORG_NAME));
        dto.setEmail(record.getValue(Tables.USERS.EMAIL));
        dto.setIdNumber(record.getValue(Tables.USERS.ID_NUMBER));
        dto.setPhone(record.getValue(Tables.USERS.PHONE));
        dto.setGenderId(record.getValue(Tables.USERS.GENDER_ID));
        if (dto.getGenderId() != null && record.field(Tables.GENDER.NAME) != null) {
            dto.setGenderName(record.getValue(Tables.GENDER.NAME));
        }
        dto.setTypeId(record.getValue(Tables.USERS.USER_TYPE_ID));
        if (record.field(Tables.USER_TYPE.NAME) != null) {
            dto.setTypeName(record.getValue(Tables.USER_TYPE.NAME));
        }
        dto.setApproved(record.getValue(Tables.USERS.IS_APPROVED));
        dto.setGroupId(record.getValue(Tables.USERS.USER_GROUP_ID));
        if (record.field(Tables.USER_GROUP.NAME) != null) {
            dto.setGroupName(record.getValue(Tables.USER_GROUP.NAME));
        }
        dto.setCreateDate(record.getValue(Tables.USERS.CREATE_DATE));
        dto.setStatusId(record.getValue(Tables.USERS.STATUS_ID));
        if (record.field(Tables.USER_STATUS.NAME) != null) {
            dto.setStatusName(record.getValue(Tables.USER_STATUS.NAME));
        }
        if (dto.getTypeId() == USER_PHISICAL) {
            dto.setName(dto.getFirstName() + " " + dto.getLastName());
        } else {
            dto.setName(dto.getOrgName());
        }
        return dto;
    }


    public static List<UserDTO> translateArray(List<Record> records) {
        ArrayList<UserDTO> list = new ArrayList<UserDTO>();
        for (Record record : records) {
            list.add(UserDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

