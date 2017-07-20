package ge.economy.involve.core.api.request;

/**
 * Created by nl on 7/20/2016.
 */
public class AddResultRequest {

    private int id;
    private int championshipId;
    private int sportTypeId;
    private int sportsmanId;
    private int teamId;
    private int awardId;
    private String score;
    private String note;
    private String weight;
    private String discipline;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChampionshipId() {
        return championshipId;
    }

    public void setChampionshipId(int championshipId) {
        this.championshipId = championshipId;
    }

    public int getSportsmanId() {
        return sportsmanId;
    }

    public void setSportsmanId(int sportsmanId) {
        this.sportsmanId = sportsmanId;
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
