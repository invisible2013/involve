package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class TrainerSportTypeDTO {

    private int id;
    private int trainerId;
    private int sportTypeId;
    private String sportTypeName;



    public static TrainerSportTypeDTO translate(Record record) {
        TrainerSportTypeDTO dto = new TrainerSportTypeDTO();
        dto.setId(record.getValue(Tables.TRAINER_SPORT_TYPE.ID));
        dto.setTrainerId(record.getValue(Tables.TRAINER_SPORT_TYPE.TRAINER_ID));
        dto.setSportTypeId(record.getValue(Tables.TRAINER_SPORT_TYPE.SPORT_TYPE_ID));
        dto.setSportTypeName(record.getValue(Tables.SPORT_TYPE.NAME));
        return dto;
    }


    public static List<TrainerSportTypeDTO> translateArray(List<Record> records) {
        ArrayList<TrainerSportTypeDTO> list = new ArrayList<TrainerSportTypeDTO>();
        for (Record record : records) {
            list.add(TrainerSportTypeDTO.translate(record));
        }
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
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
