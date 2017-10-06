package ge.economy.involve.core.api.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ge.economy.involve.core.api.dto.ReformDetailDTO;
import ge.economy.involve.core.jsonhelper.JsonDateDeSerializeSupport;

import java.util.Date;
import java.util.List;

/**
 * Created by nl on 7/20/2016.
 */
public class AddReformRequest {

    private Integer id;
    private String name;
    private Integer reformTypeId;
    @JsonDeserialize(using = JsonDateDeSerializeSupport.class)
    private Date createDate;
    private String progressBarName1;
    private Integer progressBarPercent1;
    private String progressBarName2;
    private Integer progressBarPercent2;
    private String progressBarName3;
    private Integer progressBarPercent3;
    private String generalInfo;
    private String experience;
    private String note;
    private List<ReformDetailDTO> reformDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getProgressBarPercent1() {
        return progressBarPercent1;
    }

    public void setProgressBarPercent1(Integer progressBarPercent1) {
        this.progressBarPercent1 = progressBarPercent1;
    }

    public String getProgressBarName2() {
        return progressBarName2;
    }

    public void setProgressBarName2(String progressBarName2) {
        this.progressBarName2 = progressBarName2;
    }

    public Integer getProgressBarPercent2() {
        return progressBarPercent2;
    }

    public void setProgressBarPercent2(Integer progressBarPercent2) {
        this.progressBarPercent2 = progressBarPercent2;
    }

    public String getProgressBarName3() {
        return progressBarName3;
    }

    public void setProgressBarName3(String progressBarName3) {
        this.progressBarName3 = progressBarName3;
    }

    public Integer getProgressBarPercent3() {
        return progressBarPercent3;
    }

    public void setProgressBarPercent3(Integer progressBarPercent3) {
        this.progressBarPercent3 = progressBarPercent3;
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

    public List<ReformDetailDTO> getReformDetails() {
        return reformDetails;
    }

    public void setReformDetails(List<ReformDetailDTO> reformDetails) {
        this.reformDetails = reformDetails;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
