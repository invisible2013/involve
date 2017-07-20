package ge.economy.involve.core.api.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ge.economy.involve.core.api.dto.TrainerSportTypeDTO;
import ge.economy.involve.core.jsonhelper.JsonDateDeSerializeSupport;

import java.util.Date;
import java.util.List;

/**
 * Created by nl on 7/20/2016.
 */
public class AddTrainerRequest {

    private int id;
    private String firstName;
    private String lastName;
    private int sportTypeId;
    private int qualificationId;
    private String experiense;
    private String mobileNumber;
    private String address;
    @JsonDeserialize(using = JsonDateDeSerializeSupport.class)
    private Date birthDate;
    @JsonDeserialize(using = JsonDateDeSerializeSupport.class)
    private Date careerStartDate;
    @JsonDeserialize(using = JsonDateDeSerializeSupport.class)
    private Date careerEndDate;
    @JsonDeserialize(using = JsonDateDeSerializeSupport.class)
    private Date deathDate;
    private String biography;
    private String comment;
    private Integer regionId;
    private Integer genderId;
    private Integer cityId;
    private List<TrainerSportTypeDTO> sportTypes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSportTypeId() {
        return sportTypeId;
    }

    public void setSportTypeId(int sportTypeId) {
        this.sportTypeId = sportTypeId;
    }



    public String getExperiense() {
        return experiense;
    }

    public void setExperiense(String experiense) {
        this.experiense = experiense;
    }

    public int getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(int qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public List<TrainerSportTypeDTO> getSportTypes() {
        return sportTypes;
    }

    public void setSportTypes(List<TrainerSportTypeDTO> sportTypes) {
        this.sportTypes = sportTypes;
    }

    public Date getCareerStartDate() {
        return careerStartDate;
    }

    public void setCareerStartDate(Date careerStartDate) {
        this.careerStartDate = careerStartDate;
    }

    public Date getCareerEndDate() {
        return careerEndDate;
    }

    public void setCareerEndDate(Date careerEndDate) {
        this.careerEndDate = careerEndDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
