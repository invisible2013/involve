package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class StatisticSportTypeDTO {

    private int id;
    private int statisticId;
    private int sportTypeId;
    private String sportTypeName;



    public static StatisticSportTypeDTO translate(Record record) {
        StatisticSportTypeDTO dto = new StatisticSportTypeDTO();
        dto.setId(record.getValue(Tables.STATISTIC_SPORT_TYPE.ID));
        dto.setStatisticId(record.getValue(Tables.STATISTIC_SPORT_TYPE.STATISTIC_ID));
        dto.setSportTypeId(record.getValue(Tables.STATISTIC_SPORT_TYPE.SPORT_TYPE_ID));
        dto.setSportTypeName(record.getValue(Tables.SPORT_TYPE.NAME));
        return dto;
    }


    public static List<StatisticSportTypeDTO> translateArray(List<Record> records) {
        ArrayList<StatisticSportTypeDTO> list = new ArrayList<StatisticSportTypeDTO>();
        for (Record record : records) {
            list.add(StatisticSportTypeDTO.translate(record));
        }
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatisticId() {
        return statisticId;
    }

    public void setStatisticId(int statisticId) {
        this.statisticId = statisticId;
    }

    public int getSportTypeId() {
        return sportTypeId;
    }

    public void setSportTypeId(int sportTypeId) {
        this.sportTypeId = sportTypeId;
    }

    public String getSportTypeName() {
        return sportTypeName;
    }

    public void setSportTypeName(String sportTypeName) {
        this.sportTypeName = sportTypeName;
    }
}
