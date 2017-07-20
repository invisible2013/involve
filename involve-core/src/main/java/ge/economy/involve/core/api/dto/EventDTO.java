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
public class EventDTO {

    private Integer id;
    private String name;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date startDate;
    @JsonSerialize(using = JsonDateSerializeSupport.class)
    private Date endDate;
    private Integer eventTypeId;
    private String eventTypeName;
    private Integer sportTypeId;
    private String sportTypeName;
    private String location;
    private String description;



    public static EventDTO translate(Record record) {
        EventDTO dto = new EventDTO();
        dto.setId(record.getValue(Tables.EVENT.ID));
        dto.setName(record.getValue(Tables.EVENT.NAME));
        dto.setEventTypeId(record.getValue(Tables.EVENT.EVENT_TYPE_ID));
        dto.setEventTypeName(record.getValue(Tables.EVENT_TYPE.NAME));
        dto.setStartDate(record.getValue(Tables.EVENT.START_DATE));
        dto.setEndDate(record.getValue(Tables.EVENT.END_DATE));
        dto.setLocation(record.getValue(Tables.EVENT.LOCATION));
        dto.setDescription(record.getValue(Tables.EVENT.DESCRIPTION));
        dto.setSportTypeId(record.getValue(Tables.EVENT.SPORT_TYPE_ID));
        dto.setSportTypeName(record.getValue(Tables.SPORT_TYPE.NAME));
        return dto;
    }

    public static List<EventDTO> translateArray(List<Record> records) {
        ArrayList<EventDTO> list = new ArrayList<EventDTO>();
        for (Record record : records) {
            list.add(EventDTO.translate(record));
        }
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Integer eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSportTypeId() {
        return sportTypeId;
    }

    public void setSportTypeId(Integer sportTypeId) {
        this.sportTypeId = sportTypeId;
    }

    public String getSportTypeName() {
        return sportTypeName;
    }

    public void setSportTypeName(String sportTypeName) {
        this.sportTypeName = sportTypeName;
    }
}
