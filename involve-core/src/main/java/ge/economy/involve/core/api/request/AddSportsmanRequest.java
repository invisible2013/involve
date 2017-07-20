package ge.economy.involve.core.api.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ge.economy.involve.core.jsonhelper.JsonDateDeSerializeSupport;

import java.util.Date;

/**
 * Created by nl on 7/20/2016.
 */
public class AddSportsmanRequest {

    private int id;
    private String firstName;
    private String lastName;
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
    private Integer regionId;
    private Integer genderId;
    private Integer sportTypeId;
    private Integer rankId;
    private Integer cityId;
    private String height;
    private String weight;
    private String biography;
    private String district;
    private String comment;
    private boolean paraSportsman;
    private boolean olimpic;
    private boolean paralympic;


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

    public Integer getSportTypeId() {
        return sportTypeId;
    }

    public void setSportTypeId(Integer sportTypeId) {
        this.sportTypeId = sportTypeId;
    }

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public boolean isParaSportsman() {
        return paraSportsman;
    }

    public void setParaSportsman(boolean paraSportsman) {
        this.paraSportsman = paraSportsman;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isOlimpic() {
        return olimpic;
    }

    public void setOlimpic(boolean olimpic) {
        this.olimpic = olimpic;
    }

    public boolean isParalympic() {
        return paralympic;
    }

    public void setParalympic(boolean paralympic) {
        this.paralympic = paralympic;
    }
}
