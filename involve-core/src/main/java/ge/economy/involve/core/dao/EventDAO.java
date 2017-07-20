package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.EventRecord;
import java.util.Date;
import java.util.List;
import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectField;
import org.jooq.SelectOnConditionStep;
import org.springframework.stereotype.Repository;

@Repository
public class EventDAO
        extends AbstractDAO
{
    public List<Record> getEvents()
    {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.EVENT).join(Tables.EVENT_TYPE).on(new Condition[] { Tables.EVENT.EVENT_TYPE_ID.eq(Tables.EVENT_TYPE.ID) }).leftJoin(Tables.SPORT_TYPE).on(new Condition[] { Tables.EVENT.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID) }).orderBy(Tables.EVENT.ID.desc()).fetch();
    }

    public List<Record> getEventTypes()
    {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.EVENT_TYPE).orderBy(Tables.EVENT_TYPE.ID).fetch();
    }

    public List<Record> getEventsByDate(Date startDate, Date endDate, int sportTypeId, int eventTypeId)
    {
        SelectOnConditionStep<Record> selectConditionStep = this.dslContext.select(new SelectField[0]).from(Tables.EVENT).join(Tables.EVENT_TYPE).on(new Condition[] { Tables.EVENT.EVENT_TYPE_ID.eq(Tables.EVENT_TYPE.ID) }).leftJoin(Tables.SPORT_TYPE).on(new Condition[] { Tables.EVENT.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID) });
        selectConditionStep.where(new Condition[] { Tables.EVENT.START_DATE.between(startDate, endDate) });
        if (sportTypeId != 0) {
            selectConditionStep.and(Tables.EVENT.SPORT_TYPE_ID.eq(Integer.valueOf(sportTypeId)));
        }
        if (eventTypeId != 0) {
            selectConditionStep.and(Tables.EVENT.EVENT_TYPE_ID.eq(Integer.valueOf(eventTypeId)));
        }
        return
                selectConditionStep.orderBy(Tables.EVENT.ID.desc()).fetch();
    }

    public List<EventRecord> getEventsByStartDate(Date startDate, Date endDate)
    {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.EVENT).where(new Condition[] { Tables.EVENT.START_DATE.between(startDate, endDate) }).fetch().into(EventRecord.class);
    }

    public EventRecord getEventById(int id)
    {
        return (EventRecord)this.dslContext.fetchOne(Tables.EVENT, Tables.EVENT.ID.eq(Integer.valueOf(id)));
    }

    public void deleteEvent(int itemId)
    {
        this.dslContext.deleteFrom(Tables.EVENT).where(new Condition[] { Tables.EVENT.ID.eq(Integer.valueOf(itemId)) }).execute();
    }
}
