package ge.economy.involve.core.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.economy.involve.core.jsonhelper.JsonDateSerializeSupport;
import ge.economy.involve.database.database.Tables;
import org.jooq.Record;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class InitiateDTO {

    private Integer id;
    private Integer userId;
    private Integer groupId;
    private Integer sphereId;
    private String sphereName;
    private String name;
    private String description;
    private String necessity;
    private String advantages;
    private String userName;
    private String otherSphereName;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date createDate;
    private int yesCount;


    public static InitiateDTO translate(Record record) {
        InitiateDTO dto = new InitiateDTO();
        dto.setId(record.getValue(Tables.INITIATE.ID));
        dto.setUserId(record.getValue(Tables.INITIATE.USER_ID));
        dto.setGroupId(record.getValue(Tables.INITIATE.GROUP_ID));
        dto.setSphereId(record.getValue(Tables.INITIATE.SPHERE_ID));
        dto.setSphereName(record.getValue(Tables.SPHERE.NAME));
        dto.setName(record.getValue(Tables.INITIATE.NAME));
        dto.setDescription(record.getValue(Tables.INITIATE.DESCRIPTION));
        dto.setAdvantages(record.getValue(Tables.INITIATE.ADVANTAGES));
        dto.setNecessity(record.getValue(Tables.INITIATE.NECESSITY));
        dto.setOtherSphereName(record.getValue(Tables.INITIATE.OTHER_SPHERE_NAME));
        if (dto.getUserId() != null && dto.getUserId() != 0 && Tables.USERS != null) {
            dto.setUserName(record.getValue(Tables.USERS.ORG_NAME) == null ? record.getValue(Tables.USERS.FIRST_NAME) + " " + record.getValue(Tables.USERS.LAST_NAME) : record.getValue(Tables.USERS.ORG_NAME));
        }
        dto.setCreateDate(record.getValue(Tables.INITIATE.CREATE_DATE));
        return dto;
    }


    public static List<InitiateDTO> translateArray(List<Record> records) {
        ArrayList<InitiateDTO> list = new ArrayList<InitiateDTO>();
        for (Record record : records) {
            list.add(InitiateDTO.translate(record));
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

    public int getYesCount() {
        return yesCount;
    }

    public void setYesCount(int yesCount) {
        this.yesCount = yesCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNecessity() {
        return necessity;
    }

    public void setNecessity(String necessity) {
        this.necessity = necessity;
    }

    public String getAdvantages() {
        return advantages;
    }

    public void setAdvantages(String advantages) {
        this.advantages = advantages;
    }

    public String getOtherSphereName() {
        return otherSphereName;
    }

    public void setOtherSphereName(String otherSphereName) {
        this.otherSphereName = otherSphereName;
    }
}
