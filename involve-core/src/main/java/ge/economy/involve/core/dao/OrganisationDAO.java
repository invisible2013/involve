package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.OrganisationRecord;
import java.util.HashMap;
import java.util.List;
import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectField;
import org.jooq.SelectOnConditionStep;
import org.springframework.stereotype.Repository;

@Repository
public class OrganisationDAO
        extends AbstractDAO
{
    public List<Record> getOrganisations()
    {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.ORGANISATION).join(Tables.REGION).on(new Condition[] { Tables.ORGANISATION.REGION_ID.eq(Tables.REGION.ID) }).orderBy(Tables.ORGANISATION.ID.desc()).fetch();
    }

    public HashMap<String, Object> getOrganisations(int start, int limit)
    {
        SelectOnConditionStep<Record> selectConditionStep = this.dslContext.select(new SelectField[0]).from(Tables.ORGANISATION).join(Tables.REGION).on(new Condition[] { Tables.ORGANISATION.REGION_ID.eq(Tables.REGION.ID) });

        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.ORGANISATION.NAME.asc()).limit(limit).offset(start);

        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));
        return map;
    }

    public Record getOrganisationById(int id)
    {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.ORGANISATION).join(Tables.REGION).on(new Condition[] { Tables.ORGANISATION.REGION_ID.eq(Tables.REGION.ID) }).where(new Condition[] { Tables.ORGANISATION.ID.eq(Integer.valueOf(id)) }).fetchOne();
    }

    public OrganisationRecord getOrganisationObjectById(int id)
    {
        return (OrganisationRecord)this.dslContext.fetchOne(Tables.ORGANISATION, Tables.ORGANISATION.ID.eq(Integer.valueOf(id)));
    }

    public void deleteOrganisation(int itemId)
    {
        this.dslContext.deleteFrom(Tables.ORGANISATION).where(new Condition[] { Tables.ORGANISATION.ID.eq(Integer.valueOf(itemId)) }).execute();
    }
}
