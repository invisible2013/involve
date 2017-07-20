package ge.economy.involve.core.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.economy.involve.core.jsonhelper.JsonDateSerializeSupport;
import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class ChampionshipDTO {

    private int id;
    private String name;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date startDate;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date endDate;
    private int championshipTypeId;
    private String championshipTypeName;
    private int championshipSubTypeId;
    private String championshipSubTypeName;
    private String location;
    private String description;
    public static int CHAMPIONSHIP_OLIMPIC_PRIZE = 1;
    public static int CHAMPIONSHIP_OLIMPIC = 2;
    public static int CHAMPIONSHIP_WORLD = 3;
    public static int CHAMPIONSHIP_EOUROPEAN = 4;


    public static ChampionshipDTO translate(Record record) {
        ChampionshipDTO dto = new ChampionshipDTO();
        dto.setId(record.getValue(Tables.CHAMPIONSHIP.ID));
        dto.setName(record.getValue(Tables.CHAMPIONSHIP.NAME));
        dto.setStartDate(record.getValue(Tables.CHAMPIONSHIP.START_DATE));
        dto.setEndDate(record.getValue(Tables.CHAMPIONSHIP.END_DATE));
        dto.setChampionshipTypeId(record.getValue(Tables.CHAMPIONSHIP.CHAMPIONSHIP_TYPE_ID));
        dto.setChampionshipTypeName(record.getValue(Tables.CHAMPIONSHIP_TYPE.NAME));
        dto.setChampionshipSubTypeId(record.getValue(Tables.CHAMPIONSHIP.CHAMPIONSHIP_SUB_TYPE_ID));
        dto.setChampionshipSubTypeName(record.getValue(Tables.CHAMPIONSHIP_SUB_TYPE.NAME));
        dto.setLocation(record.getValue(Tables.CHAMPIONSHIP.LOCATION));
        dto.setDescription(record.getValue(Tables.CHAMPIONSHIP.DESCRIPTION));
        return dto;
    }


    public static List<ChampionshipDTO> translateArray(List<Record> records) {
        ArrayList<ChampionshipDTO> list = new ArrayList<ChampionshipDTO>();
        for (Record record : records) {
            list.add(ChampionshipDTO.translate(record));
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
