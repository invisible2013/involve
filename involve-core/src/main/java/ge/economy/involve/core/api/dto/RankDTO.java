package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class RankDTO {

    private int id;
    private  String name;


    public static RankDTO translate(Record record) {
        RankDTO dto = new RankDTO();
        dto.setId(record.getValue(Tables.SPORTSMAN_RANK.ID));
        dto.setName(record.getValue(Tables.SPORTSMAN_RANK.NAME));
        return dto;
    }


    public static List<RankDTO> translateArray(List<Record> records) {
        ArrayList<RankDTO> list = new ArrayList<RankDTO>();
        for (Record record : records) {
            list.add(RankDTO.translate(record));
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
