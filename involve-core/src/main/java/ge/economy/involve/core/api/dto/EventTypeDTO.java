package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class EventTypeDTO {

    private int id;
    private  String name;


    public static EventTypeDTO translate(Record record) {
        EventTypeDTO dto = new EventTypeDTO();
        dto.setId(record.getValue(Tables.EVENT_TYPE.ID));
        dto.setName(record.getValue(Tables.EVENT_TYPE.NAME));
        return dto;
    }


    public static List<EventTypeDTO> translateArray(List<Record> records) {
        ArrayList<EventTypeDTO> list = new ArrayList<EventTypeDTO>();
        for (Record record : records) {
            list.add(EventTypeDTO.translate(record));
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
