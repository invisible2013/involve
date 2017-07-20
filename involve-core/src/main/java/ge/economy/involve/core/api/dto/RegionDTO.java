package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class RegionDTO {

    private int id;
    private  String name;


    public static RegionDTO translate(Record record) {
        RegionDTO dto = new RegionDTO();
        dto.setId(record.getValue(Tables.REGION.ID));
        dto.setName(record.getValue(Tables.REGION.NAME));
        return dto;
    }


    public static List<RegionDTO> translateArray(List<Record> records) {
        ArrayList<RegionDTO> list = new ArrayList<RegionDTO>();
        for (Record record : records) {
            list.add(RegionDTO.translate(record));
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
