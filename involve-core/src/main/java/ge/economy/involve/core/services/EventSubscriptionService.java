package ge.economy.involve.core.services;

import ge.economy.involve.utils.GsonUtil;
import ge.economy.involve.utils.email.SendEmailWithAttachment;
import ge.economy.involve.core.api.request.eventsubscription.SubscribeEventRequest;
import ge.economy.involve.core.freemaker.FreeMakerManager;
import ge.economy.involve.core.SendEmailWithAttachmentFactory;
import ge.economy.involve.core.api.request.eventsubscription.UnSubscribeEventRequest;
import ge.economy.involve.core.dao.EventDAO;
import ge.economy.involve.core.dao.EventSubscriptionDAO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.EventRecord;
import ge.economy.involve.database.database.tables.records.EventSubscriptionRecord;
import ge.economy.involve.database.seraliazedobjects.EventSubscriptionObject;
import ge.economy.involve.database.seraliazedobjects.EventSubscriptionSportTypeItem;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventSubscriptionService
{
    @Autowired
    private EventSubscriptionDAO eventSubscriptionDAO;
    @Autowired
    private EventDAO eventDAO;
    @Autowired
    private DSLContext dslContext;
    @Autowired
    private SendEmailWithAttachmentFactory sendEmailWithAttachmentFactory;
    private Logger logger = Logger.getLogger(EventSubscriptionService.class);

    public void subscribeEvent(SubscribeEventRequest subscribeEventRequest)
    {
        EventSubscriptionRecord eventSubscriptionRecord = this.eventSubscriptionDAO.getEventSubscription(subscribeEventRequest.getEmail());
        if (eventSubscriptionRecord == null) {
            eventSubscriptionRecord = (EventSubscriptionRecord)this.dslContext.newRecord(Tables.EVENT_SUBSCRIPTION);
        }
        EventSubscriptionObject eventSubscriptionObject = new EventSubscriptionObject();
        for (Integer i : subscribeEventRequest.getSportTypes())
        {
            EventSubscriptionSportTypeItem eventSubscriptionSportTypeItem = new EventSubscriptionSportTypeItem();
            eventSubscriptionSportTypeItem.setSportTypeId(i);
            eventSubscriptionObject.getSportTypes().add(eventSubscriptionSportTypeItem);
        }
        eventSubscriptionRecord.setRecipientEmail(subscribeEventRequest.getEmail().toLowerCase());
        eventSubscriptionRecord.setSportTypes(GsonUtil.toJson(eventSubscriptionObject));
        eventSubscriptionRecord.setActive(Boolean.valueOf(true));
        if (eventSubscriptionRecord.getId() == null) {
            eventSubscriptionRecord.insert();
        } else {
            eventSubscriptionRecord.update();
        }
    }

    public void unSubscribeEvent(UnSubscribeEventRequest unSubscribeEventRequest)
    {
        EventSubscriptionRecord eventSubscriptionRecord = this.eventSubscriptionDAO.getEventSubscription(unSubscribeEventRequest.getEmail());
        if (eventSubscriptionRecord != null)
        {
            eventSubscriptionRecord.setActive(Boolean.valueOf(false));
            eventSubscriptionRecord.update();
        }
    }

    public void checkingNearEvents()
    {
        Calendar currentDay = Calendar.getInstance();

        Calendar nextDay = Calendar.getInstance();
        nextDay.add(5, 1);

        List<EventRecord> eventRecords = this.eventDAO.getEventsByStartDate(currentDay.getTime(), nextDay.getTime());

        List<EventSubscriptionRecord> eventSubscriptionRecords = this.eventSubscriptionDAO.getActiveEventSubscriptions();

        SendEmailWithAttachment sendEmailWithAttachment = this.sendEmailWithAttachmentFactory.getInstance();
        for (EventRecord eventRecord : eventRecords)
        {
            sendEmailWithAttachment.setSubject(eventRecord.getName());
            sendEmailWithAttachment.setBody(getBody(eventRecord.getName(), eventRecord.getStartDate().toString()));
            for (EventSubscriptionRecord eventSubscriptionRecord : eventSubscriptionRecords)
            {
                EventSubscriptionObject eventSubscriptionObject = (EventSubscriptionObject)GsonUtil.fromJson(eventSubscriptionRecord.getSportTypes(), EventSubscriptionObject.class);
                if (eventSubscriptionObject.contains(eventRecord.getSportTypeId())) {
                    sendEmailWithAttachment.addBCC(eventSubscriptionRecord.getRecipientEmail());
                }
            }
            if (!sendEmailWithAttachment.getBccEmails().isEmpty()) {
                sendEmailWithAttachment.sendInNewThread();
            }
        }
    }

    public String getBody(String eventName, String eventStartDate)
    {
        Map<String, Object> data = new HashMap();
        data.put("event_name", eventName);
        data.put("event_start_date", eventStartDate);

        FreeMakerManager freeMakerManager = new FreeMakerManager();
        return freeMakerManager.getText("event_subscription.ftl", data);
    }
}
