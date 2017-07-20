package ge.economy.involve.core.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ge.economy.involve.core.jsonhelper.JsonDateSerializeSupport;
import ge.economy.involve.database.database.Tables;
import org.jooq.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nl on 7/20/2016.
 */
public class NewsDTO {

    private Integer id;
    private Integer typeId;
    private String title;
    private String shortDescription;
    private String text;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date createDate;
    private List<NewsFileDTO> files;

    public static int NEWS = 1;
    public static int NEWS_RESEARCH = 2;
    public static int NEWS_ANALISYS = 3;
    public static int NEWS_DOCUMENTATION = 4;
    public static int NEWS_HISTORY = 5;


    public static NewsDTO translate(Record record) {
        NewsDTO dto = new NewsDTO();
        dto.setId(record.getValue(Tables.NEWS.ID));
        dto.setTypeId(record.getValue(Tables.NEWS.TYPE_ID));
        dto.setTitle(record.getValue(Tables.NEWS.TITLE));
        dto.setShortDescription(record.getValue(Tables.NEWS.SHORT_DESCRIPTION));
        dto.setText(record.getValue(Tables.NEWS.TEXT));
        dto.setCreateDate(record.getValue(Tables.NEWS.CREATE_DATE));

        return dto;
    }

    public static List<NewsDTO> translateArray(List<Record> records) {
        ArrayList<NewsDTO> list = new ArrayList<NewsDTO>();
        for (Record record : records) {
            list.add(NewsDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<NewsFileDTO> getFiles() {
        return files;
    }

    public void setFiles(List<NewsFileDTO> files) {
        this.files = files;
    }
}
