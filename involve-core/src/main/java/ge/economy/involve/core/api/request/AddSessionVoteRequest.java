package ge.economy.involve.core.api.request;

/**
 * Created by nl on 7/20/2016.
 */
public class AddSessionVoteRequest {

    private Integer id;
    private Integer sessionVoteId;
    private Integer sessionId;
    private Integer userId;
    private String mail;
    private Boolean agreed;
    private int rangeId;
    private String profession;
    private String fieldOfActivity;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Boolean getAgreed() {
        return agreed;
    }

    public void setAgreed(Boolean agreed) {
        this.agreed = agreed;
    }

    public int getRangeId() {
        return rangeId;
    }

    public void setRangeId(int rangeId) {
        this.rangeId = rangeId;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getFieldOfActivity() {
        return fieldOfActivity;
    }

    public void setFieldOfActivity(String fieldOfActivity) {
        this.fieldOfActivity = fieldOfActivity;
    }

    public Integer getSessionVoteId() {
        return sessionVoteId;
    }

    public void setSessionVoteId(Integer sessionVoteId) {
        this.sessionVoteId = sessionVoteId;
    }
}
