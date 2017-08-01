package ge.economy.involve.core.api.request;

/**
 * Created by nino on 7/10/16.
 */
public class AddReformFileRequest {

    private Integer itemId;
    private Integer fileTypeId;
    private String fileName;


    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(Integer fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
