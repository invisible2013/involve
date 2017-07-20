package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/20/2016.
 */
public class StatisticDTO {

    private Integer id;
    private Integer sportTypeId;
    private String sportTypeName;
    private Integer regionId;
    private String regionName;
    private Integer rankId;
    private String rankName;
    private Integer genderId;
    private String genderName;
    private Integer rangeTypeId;
    private String rangeTypeName;
    private Integer rangeFrom;
    private Integer rangeTo;
    private Integer count;
    private Boolean para;
    private List<StatisticSportTypeDTO> sportTypes;

    public static int STATISTIC_SPORTSMAN = 1;
    public static int STATISTIC_REFEREE = 2;
    public static int STATISTIC_TRAINER = 3;
    public static int GENDER_WOMAN = 1;
    public static int GENDER_MAN = 2;


    public static StatisticDTO translate(Record record) {
        StatisticDTO dto = new StatisticDTO();
        dto.setId(record.getValue(Tables.STATISTIC.ID));
        dto.setSportTypeId(record.getValue(Tables.STATISTIC.SPORT_TYPE_ID));
        dto.setSportTypeName(record.getValue(Tables.SPORT_TYPE.NAME));
        dto.setRegionId(record.getValue(Tables.STATISTIC.REGION_ID));
        dto.setRegionName(record.getValue(Tables.REGION.NAME));
        dto.setGenderId(record.getValue(Tables.STATISTIC.GENDER_ID));
        dto.setGenderName(record.getValue(Tables.GENDER.NAME));
        dto.setRankId(record.getValue(Tables.STATISTIC.RANK_ID));
        dto.setRankName(record.getValue(Tables.STATISTIC_CATEGORY.NAME));
        dto.setRangeFrom(record.getValue(Tables.STATISTIC.RANGE_FROM));
        dto.setRangeTo(record.getValue(Tables.STATISTIC.RANGE_TO));
        dto.setRangeTypeId(record.getValue(Tables.STATISTIC.RANGE_TYPE_ID));
        dto.setRangeTypeName(record.getValue(Tables.STATISTIC_RANGE_TYPE.NAME));
        dto.setCount(record.getValue(Tables.STATISTIC.COUNT));
        dto.setPara(record.getValue(Tables.STATISTIC.IS_PARA));
        return dto;
    }

    public static List<StatisticDTO> translateArray(List<Record> records) {
        ArrayList<StatisticDTO> list = new ArrayList<StatisticDTO>();
        for (Record record : records) {
            list.add(StatisticDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getRangeTypeId() {
        return rangeTypeId;
    }

    public void setRangeTypeId(Integer rangeTypeId) {
        this.rangeTypeId = rangeTypeId;
    }

    public String getRangeTypeName() {
        return rangeTypeName;
    }

    public void setRangeTypeName(String rangeTypeName) {
        this.rangeTypeName = rangeTypeName;
    }

    public Integer getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(Integer rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public Integer getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(Integer rangeTo) {
        this.rangeTo = rangeTo;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Boolean getPara() {
        return para;
    }

    public void setPara(Boolean para) {
        this.para = para;
    }

    public List<StatisticSportTypeDTO> getSportTypes() {
        return sportTypes;
    }

    public void setSportTypes(List<StatisticSportTypeDTO> sportTypes) {
        this.sportTypes = sportTypes;
    }
}
