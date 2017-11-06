package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class SphereDTO {

    private int id;
    private  String name;


    public static SphereDTO translate(Record record) {
        SphereDTO dto = new SphereDTO();
        dto.setId(record.getValue(Tables.SPHERE.ID));
        dto.setName(record.getValue(Tables.SPHERE.NAME));
        return dto;
    }


    public static List<SphereDTO> translateArray(List<Record> records) {
        ArrayList<SphereDTO> list = new ArrayList<SphereDTO>();
        for (Record record : records) {
            list.add(SphereDTO.translate(record));
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
