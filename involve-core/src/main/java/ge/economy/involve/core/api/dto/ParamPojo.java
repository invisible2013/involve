package ge.economy.involve.core.api.dto;

/**
 * Created by NINO on 10/4/2017.
 */
public class ParamPojo {

    private int id;
    private int sessionVoteId;
    private int yesPercent;
    private int noPercent;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSessionVoteId() {
        return sessionVoteId;
    }

    public void setSessionVoteId(int sessionVoteId) {
        this.sessionVoteId = sessionVoteId;
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
}
