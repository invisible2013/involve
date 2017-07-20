package ge.economy.involve.core.api.request;

/**
 * Created by nino on 7/10/16.
 */
public class AddSportsmanFileRequest {

    private Integer sportsmanId;
    private Integer fileTypeId;
    private String fileUrl;


    public Integer getSportsmanId() {
        return sportsmanId;
    }

    public void setSportsmanId(Integer sportsmanId) {
        this.sportsmanId = sportsmanId;
    }

    public Integer getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(Integer fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
