package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class ChampionshipTypeRelationDTO {

    private int id;
    private int championshipTypeId;
    private String championshipTypeName;
    private int championshipSubTypeId;
    private String championshipSubTypeName;


    public static ChampionshipTypeRelationDTO translate(Record record) {
        ChampionshipTypeRelationDTO dto = new ChampionshipTypeRelationDTO();
        dto.setId(record.getValue(Tables.CHAMPIONSHIP_TYPE_RELATION.ID));
        dto.setChampionshipTypeId(record.getValue(Tables.CHAMPIONSHIP_TYPE_RELATION.CHAMPIONSHIP_TYPE_ID));
        dto.setChampionshipTypeName(record.getValue(Tables.CHAMPIONSHIP_TYPE.NAME));
        dto.setChampionshipSubTypeId(record.getValue(Tables.CHAMPIONSHIP_TYPE_RELATION.CHAMPIONSHIP_SUB_TYPE_ID));
        dto.setChampionshipSubTypeName(record.getValue(Tables.CHAMPIONSHIP_SUB_TYPE.NAME));
        return dto;
    }


    public static List<ChampionshipTypeRelationDTO> translateArray(List<Record> records) {
        ArrayList<ChampionshipTypeRelationDTO> list = new ArrayList<ChampionshipTypeRelationDTO>();
        for (Record record : records) {
            list.add(ChampionshipTypeRelationDTO.translate(record));
        }
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChampionshipTypeId() {
        return championshipTypeId;
    }

    public void setChampionshipTypeId(int championshipTypeId) {
        this.championshipTypeId = championshipTypeId;
    }

    public String getChampionshipTypeName() {
        return championshipTypeName;
    }

    public void setChampionshipTypeName(String championshipTypeName) {
        this.championshipTypeName = championshipTypeName;
    }

    public int getChampionshipSubTypeId() {
        return championshipSubTypeId;
    }

    public void setChampionshipSubTypeId(int championshipSubTypeId) {
        this.championshipSubTypeId = championshipSubTypeId;
    }

    public String getChampionshipSubTypeName() {
        return championshipSubTypeName;
    }

    public void setChampionshipSubTypeName(String championshipSubTypeName) {
        this.championshipSubTypeName = championshipSubTypeName;
    }
}
