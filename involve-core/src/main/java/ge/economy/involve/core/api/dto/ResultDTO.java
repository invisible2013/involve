package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/20/2016.
 */
public class ResultDTO {

    private Integer id;
    private int championshipId;
    private String championshipName;
    private int sportTypeId;
    private String sportTypeName;
    private int sportsmanId;
    private String sportsmanName;
    private int teamId;
    private int awardId;
    private String awardName;
    private String score;
    private String note;
    private String weight;
    private String discipline;
    public static int AWARD_GOLD=1;
    public static int AWARD_SILVER=2;
    public static int AWARD_BRONZE=3;




    public static ResultDTO translate(Record record) {
        ResultDTO dto = new ResultDTO();
        dto.setId(record.getValue(Tables.RESULT.ID));
        dto.setChampionshipId(record.getValue(Tables.RESULT.CHAMPIONSHIP_ID));
        dto.setChampionshipName(record.getValue(Tables.CHAMPIONSHIP.NAME));
        dto.setSportTypeId(record.getValue(Tables.RESULT.SPORT_TYPE_ID));
        dto.setSportTypeName(record.getValue(Tables.SPORT_TYPE.NAME));
        dto.setSportsmanId(record.getValue(Tables.RESULT.SPORTSMAN_ID));
        dto.setSportsmanName(record.getValue(Tables.SPORTSMAN.FIRST_NAME)+" "+record.getValue(Tables.SPORTSMAN.LAST_NAME));
        dto.setAwardId(record.getValue(Tables.RESULT.AWARD_ID));
        dto.setAwardName(record.getValue(Tables.AWARD.NAME));
        dto.setScore(record.getValue(Tables.RESULT.SCORE));
        dto.setNote(record.getValue(Tables.RESULT.NOTE));
        dto.setWeight(record.getValue(Tables.RESULT.WEIGHT));
        dto.setDiscipline(record.getValue(Tables.RESULT.DISCIPLINE));
        return dto;
    }

    public static List<ResultDTO> translateArray(List<Record> records) {
        ArrayList<ResultDTO> list = new ArrayList<ResultDTO>();
        for (Record record : records) {
            list.add(ResultDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getChampionshipId() {
        return championshipId;
    }

    public void setChampionshipId(int championshipId) {
        this.championshipId = championshipId;
    }

    public String getChampionshipName() {
        return championshipName;
    }

    public void setChampionshipName(String championshipName) {
        this.championshipName = championshipName;
    }

    public String getSportsmanName() {
        return sportsmanName;
    }

    public void setSportsmanName(String sportsmanName) {
        this.sportsmanName = sportsmanName;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public int getSportTypeId() {
        return sportTypeId;
    }

    public void setSportTypeId(int sportTypeId) {
        this.sportTypeId = sportTypeId;
    }


    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getAwardId() {
        return awardId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSportTypeName() {
        return sportTypeName;
    }

    public void setSportTypeName(String sportTypeName) {
        this.sportTypeName = sportTypeName;
    }

    public int getSportsmanId() {
        return sportsmanId;
    }

    public void setSportsmanId(int sportsmanId) {
        this.sportsmanId = sportsmanId;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
}
