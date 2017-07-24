package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class ReformTypeDTO {

    private int id;
    private  String name;


    public static ReformTypeDTO translate(Record record) {
        ReformTypeDTO dto = new ReformTypeDTO();
        dto.setId(record.getValue(Tables.REFORM_TYPE.ID));
        dto.setName(record.getValue(Tables.REFORM_TYPE.NAME));
        return dto;
    }


    public static List<ReformTypeDTO> translateArray(List<Record> records) {
        ArrayList<ReformTypeDTO> list = new ArrayList<ReformTypeDTO>();
        for (Record record : records) {
            list.add(ReformTypeDTO.translate(record));
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
