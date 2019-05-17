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
public class ReformFileDTO {

    private Integer id;
    private Integer reformId;
    private Integer fileTypeId;
    private String fileTypeName;
    private String fileName;
    private String name;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date createDate;


    public static ReformFileDTO translate(Record record) {
        ReformFileDTO dto = new ReformFileDTO();
        dto.setId(record.getValue(Tables.REFORM_FILE.ID));
        dto.setFileTypeId(record.getValue(Tables.REFORM_FILE.FILE_TYPE_ID));
        dto.setFileName(record.getValue(Tables.REFORM_FILE.FILE_NAME));
        dto.setName(record.getValue(Tables.REFORM_FILE.NAME));
        dto.setCreateDate(record.getValue(Tables.REFORM_FILE.CREATE_DATE));
        return dto;
    }


    public static List<ReformFileDTO> translateArray(List<Record> records) {
        ArrayList<ReformFileDTO> list = new ArrayList<ReformFileDTO>();
        for (Record record : records) {
            list.add(ReformFileDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReformId() {
        return reformId;
    }

    public void setReformId(Integer reformId) {
        this.reformId = reformId;
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
