package ge.economy.involve.core.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.economy.involve.core.jsonhelper.JsonDateSerializeSupport;
import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nl on 7/20/2016.
 */
public class SportsmanDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date birthDate;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date careerStartDate;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date careerEndDate;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date deathDate;
    private Integer genderId;
    private String genderName;
    private Integer rankId;
    private String rankName;
    private Integer regionId;
    private String regionName;
    private Integer cityId;
    private String cityName;
    private Integer sportTypeId;
    private String sportTypeName;
    private String height;
    private Boolean paraSportsman;
    private Boolean olimpic;
    private Integer goldCount;
    private Integer silverCount;
    private Integer bronzeCount;
    private String biography;
    private String district;
    private String comment;
    private List<ResultDTO> results;
    private List<SportsmanFileDTO> files;

    public static SportsmanDTO translate(Record record) {
        SportsmanDTO dto = new SportsmanDTO();
        dto.setId(record.getValue(Tables.SPORTSMAN.ID));
        dto.setFirstName(record.getValue(Tables.SPORTSMAN.FIRST_NAME));
        dto.setLastName(record.getValue(Tables.SPORTSMAN.LAST_NAME));
        dto.setBirthDate(record.getValue(Tables.SPORTSMAN.BIRTH_DATE));
        dto.setCareerStartDate(record.getValue(Tables.SPORTSMAN.CAREER_START_DATE));
        dto.setCareerEndDate(record.getValue(Tables.SPORTSMAN.CAREER_END_DATE));
        dto.setDeathDate(record.getValue(Tables.SPORTSMAN.DEATH_DATE));
        dto.setRankId(record.getValue(Tables.SPORTSMAN_RANK.ID));
        dto.setRankName(record.getValue(Tables.SPORTSMAN_RANK.NAME));
        dto.setGenderId(record.getValue(Tables.GENDER.ID));
        dto.setGenderName(record.getValue(Tables.GENDER.NAME));
        dto.setRegionId(record.getValue(Tables.REGION.ID));
        dto.setRegionName(record.getValue(Tables.REGION.NAME));
        dto.setCityId(record.getValue(Tables.CITY.ID));
        dto.setCityName(record.getValue(Tables.CITY.NAME));
        dto.setSportTypeId(record.getValue(Tables.SPORT_TYPE.ID));
        dto.setSportTypeName(record.getValue(Tables.SPORT_TYPE.NAME));
        dto.setParaSportsman(record.getValue(Tables.SPORTSMAN.IS_PARA_SPORTSMAN));
        dto.setHeight(record.getValue(Tables.SPORTSMAN.HEIGHT));
        dto.setBiography(record.getValue(Tables.SPORTSMAN.BIOGRAPHY));
        dto.setDistrict(record.getValue(Tables.SPORTSMAN.DISTRICT));
        dto.setComment(record.getValue(Tables.SPORTSMAN.COMMENT));
        dto.setOlimpic(record.getValue(Tables.SPORTSMAN.IS_OLIMPIC));
        //dto.setResults(resultService.getResultBySportsman(dto.getId()));
        return dto;
    }

    public static List<SportsmanDTO> translateArray(List<Record> records) {
        ArrayList<SportsmanDTO> list = new ArrayList<SportsmanDTO>();
        for (Record record : records) {
            list.add(SportsmanDTO.translate(record));
        }
        return list;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public Integer getRankId() {
        return rankId;
    }

    public void setRankId(Integer rankId) {
        this.rankId = rankId;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getSportTypeId() {
        return sportTypeId;
    }

    public void setSportTypeId(Integer sportTypeId) {
        this.sportTypeId = sportTypeId;
    }

    public String getSportTypeName() {
        return sportTypeName;
    }

    public void setSportTypeName(String sportTypeName) {
        this.sportTypeName = sportTypeName;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Boolean getParaSportsman() {
        return paraSportsman;
    }

    public void setParaSportsman(Boolean paraSportsman) {
        this.paraSportsman = paraSportsman;
    }

    public Integer getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(Integer goldCount) {
        this.goldCount = goldCount;
    }

    public Integer getSilverCount() {
        return silverCount;
    }

    public void setSilverCount(Integer silverCount) {
        this.silverCount = silverCount;
    }

    public Integer getBronzeCount() {
        return bronzeCount;
    }

    public void setBronzeCount(Integer bronzeCount) {
        this.bronzeCount = bronzeCount;
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

    public List<ResultDTO> getResults() {
        return results;
    }

    public void setResults(List<ResultDTO> results) {
        this.results = results;
    }

    public Boolean getOlimpic() {
        return olimpic;
    }

    public void setOlimpic(Boolean olimpic) {
        this.olimpic = olimpic;
    }

    public List<SportsmanFileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<SportsmanFileDTO> files) {
        this.files = files;
    }
}
