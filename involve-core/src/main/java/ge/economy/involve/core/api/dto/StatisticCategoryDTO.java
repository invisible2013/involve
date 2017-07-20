package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class StatisticCategoryDTO {

    private int id;
    private  String name;


    public static StatisticCategoryDTO translate(Record record) {
        StatisticCategoryDTO dto = new StatisticCategoryDTO();
        dto.setId(record.getValue(Tables.STATISTIC_CATEGORY.ID));
        dto.setName(record.getValue(Tables.STATISTIC_CATEGORY.NAME));
        return dto;
    }


    public static List<StatisticCategoryDTO> translateArray(List<Record> records) {
        ArrayList<StatisticCategoryDTO> list = new ArrayList<StatisticCategoryDTO>();
        for (Record record : records) {
            list.add(StatisticCategoryDTO.translate(record));
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
