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
    private String userName;
    private String email;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date birthDate;
    private int statusId;
    private String statusName;

    public static UserDTO translate(Record record) {
        UserDTO dto = new UserDTO();
        dto.setId(record.getValue(Tables.USER.ID));
        dto.setUserName(record.getValue(Tables.USER.USER_NAME));
        dto.setEmail(record.getValue(Tables.USER.EMAIL));
        dto.setBirthDate(record.getValue(Tables.USER.BIRTH_DATE));
        dto.setStatusId(record.getValue(Tables.USER_STATUS.ID));
        try {
            dto.setStatusName(record.getValue(Tables.USER_STATUS.NAME));
        } catch (Exception ex) {

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}

