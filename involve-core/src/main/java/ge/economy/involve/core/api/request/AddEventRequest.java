package ge.economy.involve.core.api.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ge.economy.involve.core.jsonhelper.JsonDateDeSerializeSupport;

import java.util.Date;

/**
 * Created by nl on 7/20/2016.
 */
public class AddEventRequest {

    private int id;
    private String name;
    private int eventTypeId;
    private String eventTypeName;
    private String location;
    private String description;
    @JsonDeserialize(using = JsonDateDeSerializeSupport.class)
    private Date startDate;
    @JsonDeserialize(using = JsonDateDeSerializeSupport.class)
    private Date endDate;
    private int sportTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
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

    public int getSportTypeId() {
        return sportTypeId;
    }

    public void setSportTypeId(int sportTypeId) {
        this.sportTypeId = sportTypeId;
    }
}
