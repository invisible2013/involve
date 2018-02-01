package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class AgeRangeDTO {

    private Integer id;
    private String name;
    private Integer from;
    private Integer to;


    public static AgeRangeDTO translate(Record record) {
        AgeRangeDTO dto = new AgeRangeDTO();
        dto.setId(record.getValue(Tables.AGE_RANGE.ID));
        dto.setName(record.getValue(Tables.AGE_RANGE.NAME));
        dto.setFrom(record.getValue(Tables.AGE_RANGE.FROM));
        dto.setTo(record.getValue(Tables.AGE_RANGE.TO));
        return dto;
    }


    public static List<AgeRangeDTO> translateArray(List<Record> records) {
        ArrayList<AgeRangeDTO> list = new ArrayList<AgeRangeDTO>();
        for (Record record : records) {
            list.add(AgeRangeDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }
}
