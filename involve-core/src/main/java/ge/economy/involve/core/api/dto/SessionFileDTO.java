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
public class SessionFileDTO {

    private Integer id;
    private Integer sessionId;
    private Integer fileTypeId;
    private String fileTypeName;
    private String fileName;
    private String name;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date createDate;


    public static SessionFileDTO translate(Record record) {
        SessionFileDTO dto = new SessionFileDTO();
        dto.setId(record.getValue(Tables.SESSION_FILE.ID));
        dto.setSessionId(record.getValue(Tables.SESSION_FILE.SESSION_ID));
        dto.setFileTypeId(record.getValue(Tables.SESSION_FILE.FILE_TYPE_ID));
        dto.setFileName(record.getValue(Tables.SESSION_FILE.FILE_NAME));
        dto.setName(record.getValue(Tables.SESSION_FILE.NAME));
        dto.setCreateDate(record.getValue(Tables.SESSION_FILE.CREATE_DATE));
        return dto;
    }


    public static List<SessionFileDTO> translateArray(List<Record> records) {
        ArrayList<SessionFileDTO> list = new ArrayList<SessionFileDTO>();
        for (Record record : records) {
            list.add(SessionFileDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(Integer fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
