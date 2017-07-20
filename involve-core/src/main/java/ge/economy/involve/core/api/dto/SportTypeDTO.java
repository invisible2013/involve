package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class SportTypeDTO {

    private int id;
    private  String name;
    private  String description;
    private List<SportTypeFileDTO> files;


    public static SportTypeDTO translate(Record record) {
        SportTypeDTO dto = new SportTypeDTO();
        dto.setId(record.getValue(Tables.SPORT_TYPE.ID));
        dto.setName(record.getValue(Tables.SPORT_TYPE.NAME));
        return dto;
    }

    public static SportTypeDTO translateWithDescription(Record record) {
        SportTypeDTO dto = new SportTypeDTO();
        dto.setId(record.getValue(Tables.SPORT_TYPE.ID));
        dto.setName(record.getValue(Tables.SPORT_TYPE.NAME));
        dto.setDescription(record.getValue(Tables.SPORT_TYPE.DESCRIPTION));
        return dto;
    }



    public static List<SportTypeDTO> translateArray(List<Record> records) {
        ArrayList<SportTypeDTO> list = new ArrayList<SportTypeDTO>();
        for (Record record : records) {
            list.add(SportTypeDTO.translate(record));
        }
        return list;
    }

    public static List<SportTypeDTO> translateArrayWithDescription(List<Record> records) {
        ArrayList<SportTypeDTO> list = new ArrayList<SportTypeDTO>();
        for (Record record : records) {
            list.add(SportTypeDTO.translateWithDescription(record));
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SportTypeFileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<SportTypeFileDTO> files) {
        this.files = files;
    }
}
