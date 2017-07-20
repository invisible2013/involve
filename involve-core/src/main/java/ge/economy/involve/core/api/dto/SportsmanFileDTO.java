package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class SportsmanFileDTO {

    private Integer id;
    private  String fileName;
    private  String fileUrl;
    private  Integer fileTypeId;
    private  String fileTypeName;
    private  Integer sportsmanId;
    private  Boolean main;


    public static SportsmanFileDTO translate(Record record) {
        SportsmanFileDTO dto = new SportsmanFileDTO();
        dto.setId(record.getValue(Tables.SPORTSMAN_FILE.ID));
        dto.setFileName(record.getValue(Tables.SPORTSMAN_FILE.FILE_NAME));
        dto.setSportsmanId(record.getValue(Tables.SPORTSMAN_FILE.SPORTSMAN_ID));
        dto.setMain(record.getValue(Tables.SPORTSMAN_FILE.IS_MAIN));
        dto.setFileUrl(record.getValue(Tables.SPORTSMAN_FILE.FILE_URL));
        dto.setFileTypeId(record.getValue(Tables.SPORTSMAN_FILE.FILE_TYPE_ID));
        dto.setFileTypeName(record.getValue(Tables.FILE_TYPE.NAME));
        return dto;
    }


    public static List<SportsmanFileDTO> translateArray(List<Record> records) {
        ArrayList<SportsmanFileDTO> list = new ArrayList<SportsmanFileDTO>();
        for (Record record : records) {
            list.add(SportsmanFileDTO.translate(record));
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

    public Integer getSportsmanId() {
        return sportsmanId;
    }

    public void setSportsmanId(Integer sportsmanId) {
        this.sportsmanId = sportsmanId;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }

    public Integer getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(Integer fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }
}
