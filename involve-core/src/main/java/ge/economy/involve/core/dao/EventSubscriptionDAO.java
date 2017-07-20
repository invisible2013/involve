package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.EventSubscriptionRecord;

import java.util.List;

import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectField;
import org.springframework.stereotype.Repository;

@Repository
public class EventSubscriptionDAO
        extends AbstractDAO {
    public EventSubscriptionRecord getEventSubscription(String email) {
        List<Record> records = this.dslContext.select(new SelectField[0]).from(Tables.EVENT_SUBSCRIPTION).where(new Condition[]{Tables.EVENT_SUBSCRIPTION.RECIPIENT_EMAIL.equalIgnoreCase(email)}).fetch();

        return records.isEmpty() ? null : (EventSubscriptionRecord) ((Record) records.get(0)).into(EventSubscriptionRecord.class);
    }

    public List<EventSubscriptionRecord> getActiveEventSubscriptions() {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.EVENT_SUBSCRIPTION).where(new Condition[]{Tables.EVENT_SUBSCRIPTION.ACTIVE.eq(Boolean.valueOf(true))}).fetch().into(EventSubscriptionRecord.class);
    }
}
