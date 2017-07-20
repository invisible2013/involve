package ge.economy.involve.core.api.dto;

import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nl on 7/25/2016.
 */
public class NewsFileDTO {

    private Integer id;
    private  String fileName;
    private  String fileTypeName;
    private  Integer fileTypeId;
    private  Boolean main;
    private  Integer newsId;


    public static NewsFileDTO translate(Record record) {
        NewsFileDTO dto = new NewsFileDTO();
        dto.setId(record.getValue(Tables.NEWS_FILE.ID));
        dto.setFileTypeId(record.getValue(Tables.NEWS_FILE.FILE_TYPE_ID));
        dto.setFileTypeName(record.getValue(Tables.FILE_TYPE.NAME));
        dto.setFileName(record.getValue(Tables.NEWS_FILE.FILE_NAME));
        dto.setNewsId(record.getValue(Tables.NEWS_FILE.NEWS_ID));
        dto.setMain(record.getValue(Tables.NEWS_FILE.IS_MAIN));
        return dto;
    }


    public static List<NewsFileDTO> translateArray(List<Record> records) {
        ArrayList<NewsFileDTO> list = new ArrayList<NewsFileDTO>();
        for (Record record : records) {
            list.add(NewsFileDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(Integer fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName;
    }

    public Boolean getMain() {
        return main;
    }

    public void setMain(Boolean main) {
        this.main = main;
    }
}
