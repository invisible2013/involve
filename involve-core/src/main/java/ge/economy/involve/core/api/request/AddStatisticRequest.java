package ge.economy.involve.core.api.request;

import ge.economy.involve.core.api.dto.StatisticSportTypeDTO;

import java.util.List;

/**
 * Created by nl on 7/20/2016.
 */
public class AddStatisticRequest {

    private int id;
    private int sportTypeId;
    private int sportsmanId;
    private int rankId;
    private int genderId;
    private int regionId;
    private int rangeFrom;
    private int rangeTo;
    private int count;
    private int typeId;
    private int rangeTypeId;
    private boolean para;
    private List<StatisticSportTypeDTO> sportTypes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSportTypeId() {
        return sportTypeId;
    }

    public void setSportTypeId(int sportTypeId) {
        this.sportTypeId = sportTypeId;
    }

    public int getSportsmanId() {
        return sportsmanId;
    }

    public void setSportsmanId(int sportsmanId) {
        this.sportsmanId = sportsmanId;
    }

    public int getRankId() {
        return rankId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public int getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(int rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public int getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(int rangeTo) {
        this.rangeTo = rangeTo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getRangeTypeId() {
        return rangeTypeId;
    }

    public void setRangeTypeId(int rangeTypeId) {
        this.rangeTypeId = rangeTypeId;
    }

    public boolean isPara() {
        return para;
    }

    public void setPara(boolean para) {
        this.para = para;
    }

    public List<StatisticSportTypeDTO> getSportTypes() {
        return sportTypes;
    }

    public void setSportTypes(List<StatisticSportTypeDTO> sportTypes) {
        this.sportTypes = sportTypes;
    }
}
