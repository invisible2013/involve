package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class ReformDetailDTO {

    private Integer id;
    private  String name;
    private  String value;


    public static ReformDetailDTO translate(Record record) {
        ReformDetailDTO dto = new ReformDetailDTO();
        dto.setId(record.getValue(Tables.REFORM_DETAIL.ID));
        dto.setName(record.getValue(Tables.REFORM_DETAIL.NAME));
        dto.setValue(record.getValue(Tables.REFORM_DETAIL.VALUE));
        return dto;
    }


    public static List<ReformDetailDTO> translateArray(List<Record> records) {
        ArrayList<ReformDetailDTO> list = new ArrayList<ReformDetailDTO>();
        for (Record record : records) {
            list.add(ReformDetailDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
