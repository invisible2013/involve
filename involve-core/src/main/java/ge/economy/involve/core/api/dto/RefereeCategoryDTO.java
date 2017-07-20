package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class RefereeCategoryDTO {

    private int id;
    private  String name;


    public static RefereeCategoryDTO translate(Record record) {
        RefereeCategoryDTO dto = new RefereeCategoryDTO();
        dto.setId(record.getValue(Tables.REFEREE_CATEGORY.ID));
        dto.setName(record.getValue(Tables.REFEREE_CATEGORY.NAME));
        return dto;
    }


    public static List<RefereeCategoryDTO> translateArray(List<Record> records) {
        ArrayList<RefereeCategoryDTO> list = new ArrayList<RefereeCategoryDTO>();
        for (Record record : records) {
            list.add(RefereeCategoryDTO.translate(record));
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
