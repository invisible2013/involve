package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class TokenDTO {

    private Integer id;
    private Integer userId;
    private String key;
    private Date createDate;
    private Date validDate;


    public static TokenDTO translate(Record record) {
        TokenDTO dto = new TokenDTO();
        dto.setId(record.getValue(Tables.TOKEN.ID));
        dto.setUserId(record.getValue(Tables.TOKEN.USER_ID));
        dto.setKey(record.getValue(Tables.TOKEN.KEY));
        dto.setCreateDate(record.getValue(Tables.TOKEN.CREATE_DATE));
        dto.setValidDate(record.getValue(Tables.TOKEN.VALID_DATE));
        return dto;
    }


    public static List<TokenDTO> translateArray(List<Record> records) {
        ArrayList<TokenDTO> list = new ArrayList<TokenDTO>();
        for (Record record : records) {
            list.add(TokenDTO.translate(record));
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }
}
