package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class RefereeSportTypeDTO {

    private int id;
    private int refereeId;
    private int sportTypeId;
    private String sportTypeName;



    public static RefereeSportTypeDTO translate(Record record) {
        RefereeSportTypeDTO dto = new RefereeSportTypeDTO();
        dto.setId(record.getValue(Tables.REFEREE_SPORT_TYPE.ID));
        dto.setRefereeId(record.getValue(Tables.REFEREE_SPORT_TYPE.REFEREE_ID));
        dto.setSportTypeId(record.getValue(Tables.REFEREE_SPORT_TYPE.SPORT_TYPE_ID));
        dto.setSportTypeName(record.getValue(Tables.SPORT_TYPE.NAME));
        return dto;
    }


    public static List<RefereeSportTypeDTO> translateArray(List<Record> records) {
        ArrayList<RefereeSportTypeDTO> list = new ArrayList<RefereeSportTypeDTO>();
        for (Record record : records) {
            list.add(RefereeSportTypeDTO.translate(record));
        }
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRefereeId() {
        return refereeId;
    }

    public void setRefereeId(int refereeId) {
        this.refereeId = refereeId;
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
