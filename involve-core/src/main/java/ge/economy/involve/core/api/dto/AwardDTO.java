package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class AwardDTO {

    private int id;
    private  String name;


    public static AwardDTO translate(Record record) {
        AwardDTO dto = new AwardDTO();
        dto.setId(record.getValue(Tables.AWARD.ID));
        dto.setName(record.getValue(Tables.AWARD.NAME));
        return dto;
    }


    public static List<AwardDTO> translateArray(List<Record> records) {
        ArrayList<AwardDTO> list = new ArrayList<AwardDTO>();
        for (Record record : records) {
            list.add(AwardDTO.translate(record));
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
