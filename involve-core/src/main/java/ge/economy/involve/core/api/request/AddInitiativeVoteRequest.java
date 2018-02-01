package ge.economy.involve.core.api.request;

/**
 * Created by nl on 7/20/2016.
 */
public class AddInitiativeVoteRequest {

    private Integer id;
    private Integer userId;
    private Integer initiatedIssueId;
    private Boolean agreed;
    private String ipAddress;
    private String clientUID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInitiatedIssueId() {
        return initiatedIssueId;
    }

    public void setInitiatedIssueId(Integer initiatedIssueId) {
        this.initiatedIssueId = initiatedIssueId;
    }

    public Boolean getAgreed() {
        return agreed;
    }

    public void setAgreed(Boolean agreed) {
        this.agreed = agreed;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getClientUID() {
        return clientUID;
    }

    public void setClientUID(String clientUID) {
        this.clientUID = clientUID;
    }
}
