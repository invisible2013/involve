package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class GenderDTO {

    private int id;
    private  String name;


    public static GenderDTO translate(Record record) {
        GenderDTO dto = new GenderDTO();
        dto.setId(record.getValue(Tables.GENDER.ID));
        dto.setName(record.getValue(Tables.GENDER.NAME));
        return dto;
    }


    public static List<GenderDTO> translateArray(List<Record> records) {
        ArrayList<GenderDTO> list = new ArrayList<GenderDTO>();
        for (Record record : records) {
            list.add(GenderDTO.translate(record));
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
