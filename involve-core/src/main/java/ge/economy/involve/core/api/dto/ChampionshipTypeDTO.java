package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class ChampionshipTypeDTO {

    private int id;
    private  String name;


    public static ChampionshipTypeDTO translate(Record record) {
        ChampionshipTypeDTO dto = new ChampionshipTypeDTO();
        dto.setId(record.getValue(Tables.CHAMPIONSHIP_TYPE.ID));
        dto.setName(record.getValue(Tables.CHAMPIONSHIP_TYPE.NAME));
        return dto;
    }


    public static List<ChampionshipTypeDTO> translateArray(List<Record> records) {
        ArrayList<ChampionshipTypeDTO> list = new ArrayList<ChampionshipTypeDTO>();
        for (Record record : records) {
            list.add(ChampionshipTypeDTO.translate(record));
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
