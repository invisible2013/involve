package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class CityDTO {

    private int id;
    private  String name;
    private  int regionId;


    public static CityDTO translate(Record record) {
        CityDTO dto = new CityDTO();
        dto.setId(record.getValue(Tables.CITY.ID));
        dto.setName(record.getValue(Tables.CITY.NAME));
        dto.setRegionId(record.getValue(Tables.CITY.REGION_ID));
        return dto;
    }


    public static List<CityDTO> translateArray(List<Record> records) {
        ArrayList<CityDTO> list = new ArrayList<CityDTO>();
        for (Record record : records) {
            list.add(CityDTO.translate(record));
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

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
}
