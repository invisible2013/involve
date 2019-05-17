package ge.economy.involve.core.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.economy.involve.core.jsonhelper.JsonDateSerializeSupport;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.utils.DateTimeUtils;
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
    private Integer timePercent;
    private String name;
    private String note;
    private String imageName;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date startDate;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date endDate;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date createDate;
    private List<SessionPollDTO> polls;
    private List<SessionFileDTO> files;
    private ReformDTO reform;
    private int yesPercent;
    private int noPercent;
    private int statusId;
    public static final int ACTIVE_SESSION = 1;
    public static final int CLOSE_SESSION = 2;

    public static SessionDTO translate(Record record) {
        if (record != null) {
            SessionDTO dto = new SessionDTO();
            dto.setId(record.getValue(Tables.SESSION.ID));
            dto.setReformId(record.getValue(Tables.SESSION.REFORM_ID));
            dto.setName(record.getValue(Tables.SESSION.NAME));
            dto.setNote(record.getValue(Tables.SESSION.NOTE));
            dto.setImageName(record.getValue(Tables.SESSION.IMAGE_NAME));
            dto.setStartDate(record.getValue(Tables.SESSION.START_DATE));
            dto.setEndDate(record.getValue(Tables.SESSION.END_DATE));
            dto.setCreateDate(record.getValue(Tables.SESSION.CREATE_DATE));
            dto.setWorkPercent(record.getValue(Tables.SESSION.WORK_PERCENT));
            dto.setTimePercent(DateTimeUtils.getPastTimePercent(dto.getStartDate(), dto.getEndDate()));
            dto.setStatusId(dto.getEndDate().compareTo(new Date()) < 0 ? CLOSE_SESSION : ACTIVE_SESSION);
            return dto;
        }
        return null;
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

    public Integer getTimePercent() {
        return timePercent;
    }

    public void setTimePercent(Integer timePercent) {
        this.timePercent = timePercent;
    }

    public List<SessionPollDTO> getPolls() {
        return polls;
    }

    public void setPolls(List<SessionPollDTO> polls) {
        this.polls = polls;
    }

    public ReformDTO getReform() {
        return reform;
    }

    public void setReform(ReformDTO reform) {
        this.reform = reform;
    }

    public int getYesPercent() {
        return yesPercent;
    }

    public void setYesPercent(int yesPercent) {
        this.yesPercent = yesPercent;
    }

    public int getNoPercent() {
        return noPercent;
    }

    public void setNoPercent(int noPercent) {
        this.noPercent = noPercent;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public List<SessionFileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<SessionFileDTO> files) {
        this.files = files;
    }
}
