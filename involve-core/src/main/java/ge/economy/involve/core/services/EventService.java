package ge.economy.involve.core.services;

import ge.economy.involve.core.dao.EventDAO;
import ge.economy.involve.core.api.dto.EventDTO;
import ge.economy.involve.core.api.dto.EventTypeDTO;
import ge.economy.involve.core.api.request.AddEventRequest;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.EventRecord;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService
{
    @Autowired
    private EventDAO eventDAO;
    @Autowired
    private DSLContext dslContext;
    Logger logger = Logger.getLogger(EventService.class);

    public EventDTO saveEvent(AddEventRequest request)
    {
        boolean newRecord = false;
        EventRecord record = null;
        if (request.getId() != 0) {
            record = this.eventDAO.getEventById(request.getId());
        }
        if (record == null)
        {
            record = (EventRecord)this.dslContext.newRecord(Tables.EVENT);
            newRecord = true;
        }
        record.setName(request.getName());
        record.setEventTypeId(Integer.valueOf(request.getEventTypeId()));
        record.setStartDate(request.getStartDate());
        record.setEndDate(request.getEndDate());
        record.setLocation(request.getLocation());
        record.setDescription(request.getDescription());
        record.setSportTypeId(Integer.valueOf(request.getSportTypeId()));
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }
        return null;
    }

    public EventDTO getEventById(int itemId)
    {
        return EventDTO.translate(this.eventDAO.getEventById(itemId));
    }

    public List<EventDTO> getEvents()
    {
        return EventDTO.translateArray(this.eventDAO.getEvents());
    }

    public List<EventTypeDTO> getEventTypes()
    {
        return EventTypeDTO.translateArray(this.eventDAO.getEventTypes());
    }

    public List<EventDTO> getEventsByDate(String startDateString, String endDateString, int sportTypeId, int eventTypeId)
    {
        try
        {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date startDate = df.parse(startDateString);
            Date endDate = df.parse(endDateString);
            return EventDTO.translateArray(this.eventDAO.getEventsByDate(startDate, endDate, sportTypeId, eventTypeId));
        }
        catch (Exception ex)
        {
            this.logger.error(ex);
        }
        return null;
    }

    public void deleteEvent(int itemId)
    {
        this.eventDAO.deleteEvent(itemId);
    }
}
