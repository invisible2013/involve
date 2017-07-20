package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class RangeTypeDTO {

    private int id;
    private  String name;
    private  int from;
    private  int to;


    public static RangeTypeDTO translate(Record record) {
        RangeTypeDTO dto = new RangeTypeDTO();
        dto.setId(record.getValue(Tables.STATISTIC_RANGE_TYPE.ID));
        dto.setName(record.getValue(Tables.STATISTIC_RANGE_TYPE.NAME));
        dto.setFrom(record.getValue(Tables.STATISTIC_RANGE_TYPE.FROM));
        dto.setTo(record.getValue(Tables.STATISTIC_RANGE_TYPE.TO));
        return dto;
    }


    public static List<RangeTypeDTO> translateArray(List<Record> records) {
        ArrayList<RangeTypeDTO> list = new ArrayList<RangeTypeDTO>();
        for (Record record : records) {
            list.add(RangeTypeDTO.translate(record));
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

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
