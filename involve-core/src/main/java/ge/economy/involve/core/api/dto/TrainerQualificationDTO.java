package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class TrainerQualificationDTO {

    private int id;
    private  String name;


    public static TrainerQualificationDTO translate(Record record) {
        TrainerQualificationDTO dto = new TrainerQualificationDTO();
        dto.setId(record.getValue(Tables.TRAINER_QUALIFICATION.ID));
        dto.setName(record.getValue(Tables.TRAINER_QUALIFICATION.NAME));
        return dto;
    }


    public static List<TrainerQualificationDTO> translateArray(List<Record> records) {
        ArrayList<TrainerQualificationDTO> list = new ArrayList<TrainerQualificationDTO>();
        for (Record record : records) {
            list.add(TrainerQualificationDTO.translate(record));
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
