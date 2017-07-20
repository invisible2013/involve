package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class ChampionshipSubTypeDTO {

    private int id;
    private  String name;


    public static ChampionshipSubTypeDTO translate(Record record) {
        ChampionshipSubTypeDTO dto = new ChampionshipSubTypeDTO();
        dto.setId(record.getValue(Tables.CHAMPIONSHIP_SUB_TYPE.ID));
        dto.setName(record.getValue(Tables.CHAMPIONSHIP_SUB_TYPE.NAME));
        return dto;
    }


    public static List<ChampionshipSubTypeDTO> translateArray(List<Record> records) {
        ArrayList<ChampionshipSubTypeDTO> list = new ArrayList<ChampionshipSubTypeDTO>();
        for (Record record : records) {
            list.add(ChampionshipSubTypeDTO.translate(record));
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
