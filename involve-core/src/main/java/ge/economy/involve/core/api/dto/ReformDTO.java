package ge.economy.involve.core.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.economy.involve.core.jsonhelper.JsonDateDeSerializeSupport;
import ge.economy.involve.core.jsonhelper.JsonDateSerializeSupport;
import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class ReformDTO {

    private int id;
    private String name;
    private String note;
    private String imageName;
    private Integer reformTypeId;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date createDate;
    private String progressBarName1;
    private Integer progressBarPercent1;
    private String progressBarName2;
    private Integer progressBarPercent2;
    private String progressBarName3;
    private Integer progressBarPercent3;
    private String generalInfo;
    private String experience;
    private String reformTypeName;
    private int yesPercent;
    private int noPercent;
    private List<ReformDetailDTO> reformDetails;
    private List<ReformFileDTO> reformFiles;
    private List<SessionDTO> sessions;
    private Integer statusId;
    public static final int ACTIVE_REFORM = 1;
    public static final int CLOSE_REFORM = 2;

    public static ReformDTO translate(Record record) {
        ReformDTO dto = new ReformDTO();
        dto.setId(record.getValue(Tables.REFORM.ID));
        dto.setName(record.getValue(Tables.REFORM.NAME));
        dto.setImageName(record.getValue(Tables.REFORM.IMAGE_NAME));
        dto.setGeneralInfo(record.getValue(Tables.REFORM.GENERAL_INFO));
        dto.setExperience(record.getValue(Tables.REFORM.EXPERIENCE));
        dto.setNote(record.getValue(Tables.REFORM.NOTE));
        dto.setProgressBarName1(record.getValue(Tables.REFORM.PROGRESS_BAR_NAME_1));
        dto.setProgressBarName2(record.getValue(Tables.REFORM.PROGRESS_BAR_NAME_2));
        dto.setProgressBarName3(record.getValue(Tables.REFORM.PROGRESS_BAR_NAME_3));
        if (record.getValue(Tables.REFORM.PROGRESS_BAR_PERCENT_1) != null) {
            dto.setProgressBarPercent1(Integer.parseInt(record.getValue(Tables.REFORM.PROGRESS_BAR_PERCENT_1)));
        }
        if (record.getValue(Tables.REFORM.PROGRESS_BAR_PERCENT_2) != null) {
            dto.setProgressBarPercent2(Integer.parseInt(record.getValue(Tables.REFORM.PROGRESS_BAR_PERCENT_2)));
        }
        if (record.getValue(Tables.REFORM.PROGRESS_BAR_PERCENT_3) != null) {
            dto.setProgressBarPercent3(Integer.parseInt(record.getValue(Tables.REFORM.PROGRESS_BAR_PERCENT_3)));
        }
        dto.setReformTypeId(record.getValue(Tables.REFORM.REFORM_TYPE_ID));
        dto.setReformTypeName(record.getValue(Tables.REFORM_TYPE.NAME));
        dto.setCreateDate(record.getValue(Tables.REFORM.CREATE_DATE));
        dto.setStatusId(record.getValue(Tables.REFORM.STATUS_ID));
        return dto;
    }


    public static List<ReformDTO> translateArray(List<Record> records) {
        ArrayList<ReformDTO> list = new ArrayList<ReformDTO>();
        for (Record record : records) {
            list.add(ReformDTO.translate(record));
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

    public Integer getReformTypeId() {
        return reformTypeId;
    }

    public void setReformTypeId(Integer reformTypeId) {
        this.reformTypeId = reformTypeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getProgressBarName1() {
        return progressBarName1;
    }

    public void setProgressBarName1(String progressBarName1) {
        this.progressBarName1 = progressBarName1;
    }

    public String getProgressBarName2() {
        return progressBarName2;
    }

    public void setProgressBarName2(String progressBarName2) {
        this.progressBarName2 = progressBarName2;
    }


    public String getProgressBarName3() {
        return progressBarName3;
    }

    public void setProgressBarName3(String progressBarName3) {
        this.progressBarName3 = progressBarName3;
    }


    public String getGeneralInfo() {
        return generalInfo;
    }

    public void setGeneralInfo(String generalInfo) {
        this.generalInfo = generalInfo;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getReformTypeName() {
        return reformTypeName;
    }

    public void setReformTypeName(String reformTypeName) {
        this.reformTypeName = reformTypeName;
    }

    public Integer getProgressBarPercent1() {
        return progressBarPercent1;
    }

    public void setProgressBarPercent1(Integer progressBarPercent1) {
        this.progressBarPercent1 = progressBarPercent1;
    }

    public Integer getProgressBarPercent2() {
        return progressBarPercent2;
    }

    public void setProgressBarPercent2(Integer progressBarPercent2) {
        this.progressBarPercent2 = progressBarPercent2;
    }

    public Integer getProgressBarPercent3() {
        return progressBarPercent3;
    }

    public void setProgressBarPercent3(Integer progressBarPercent3) {
        this.progressBarPercent3 = progressBarPercent3;
    }

    public List<ReformDetailDTO> getReformDetails() {
        return reformDetails;
    }

    public void setReformDetails(List<ReformDetailDTO> reformDetails) {
        this.reformDetails = reformDetails;
    }

    public List<ReformFileDTO> getReformFiles() {
        return reformFiles;
    }

    public void setReformFiles(List<ReformFileDTO> reformFiles) {
        this.reformFiles = reformFiles;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<SessionDTO> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionDTO> sessions) {
        this.sessions = sessions;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}
