package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class UserTypeDTO {

    private int id;
    private  String name;


    public static UserTypeDTO translate(Record record) {
        UserTypeDTO dto = new UserTypeDTO();
        dto.setId(record.getValue(Tables.USER_TYPE.ID));
        dto.setName(record.getValue(Tables.USER_TYPE.NAME));
        return dto;
    }


    public static List<UserTypeDTO> translateArray(List<Record> records) {
        ArrayList<UserTypeDTO> list = new ArrayList<UserTypeDTO>();
        for (Record record : records) {
            list.add(UserTypeDTO.translate(record));
        }
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
