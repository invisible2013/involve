package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class SportTypeFileDTO {

    private Integer id;
    private  String fileName;
    private  String fileTypeName;
    private  Integer fileTypeId;
    private  Integer sportTypeId;


    public static SportTypeFileDTO translate(Record record) {
        SportTypeFileDTO dto = new SportTypeFileDTO();
        dto.setId(record.getValue(Tables.SPORT_TYPE_FILE.ID));
        dto.setSportTypeId(record.getValue(Tables.SPORT_TYPE_FILE.SPORT_TYPE_ID));
        dto.setFileTypeId(record.getValue(Tables.SPORT_TYPE_FILE.FILE_TYPE_ID));
        dto.setFileTypeName(record.getValue(Tables.FILE_TYPE.NAME));
        dto.setFileName(record.getValue(Tables.SPORT_TYPE_FILE.FILE_NAME));
        return dto;
    }


    public static List<SportTypeFileDTO> translateArray(List<Record> records) {
        ArrayList<SportTypeFileDTO> list = new ArrayList<SportTypeFileDTO>();
        for (Record record : records) {
            list.add(SportTypeFileDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public Integer getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(Integer fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public Integer getSportTypeId() {
        return sportTypeId;
    }

    public void setSportTypeId(Integer sportTypeId) {
        this.sportTypeId = sportTypeId;
    }
}
