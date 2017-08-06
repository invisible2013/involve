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
public class SessionDTO {

    private Integer id;
    private Integer reformId;
    private Integer workPercent;
    private String name;
    private String imageName;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date startDate;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date endDate;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date createDate;


    public static SessionDTO translate(Record record) {
        SessionDTO dto = new SessionDTO();
        dto.setId(record.getValue(Tables.SESSION.ID));
        dto.setReformId(record.getValue(Tables.SESSION.REFORM_ID));
        dto.setName(record.getValue(Tables.SESSION.NAME));
        dto.setImageName(record.getValue(Tables.SESSION.IMAGE_NAME));
        dto.setStartDate(record.getValue(Tables.SESSION.START_DATE));
        dto.setEndDate(record.getValue(Tables.SESSION.END_DATE));
        dto.setCreateDate(record.getValue(Tables.SESSION.CREATE_DATE));
        dto.setWorkPercent(record.getValue(Tables.SESSION.WORK_PERCENT));
        return dto;
    }


    public static List<SessionDTO> translateArray(List<Record> records) {
        ArrayList<SessionDTO> list = new ArrayList<SessionDTO>();
        for (Record record : records) {
            list.add(SessionDTO.translate(record));
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getWorkPercent() {
        return workPercent;
    }

    public void setWorkPercent(Integer workPercent) {
        this.workPercent = workPercent;
    }
}
