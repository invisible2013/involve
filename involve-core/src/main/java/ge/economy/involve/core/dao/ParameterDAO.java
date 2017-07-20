package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.ChampionshipSubTypeRecord;
import ge.economy.involve.database.database.tables.records.SportTypeFileRecord;
import ge.economy.involve.database.database.tables.records.SportTypeRecord;
import java.util.List;
import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectField;
import org.springframework.stereotype.Repository;

@Repository
public class ParameterDAO
        extends AbstractDAO
{
    public List<Record> getSportTypes()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.SPORT_TYPE).orderBy(Tables.SPORT_TYPE.NAME).fetch();
    }

    public List<Record> getRegions()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.REGION).fetch();
    }

    public List<Record> getGenders()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.GENDER).fetch();
    }

    public List<Record> getCities()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.CITY).fetch();
    }

    public List<Record> getCitiesByRegion(int regionId)
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.CITY).where(new Condition[] { Tables.CITY.REGION_ID.eq(Integer.valueOf(regionId)) }).fetch();
    }

    public List<Record> getConditions()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.CONDITION).fetch();
    }

    public List<Record> getOwnerShip()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.OWNERSHIP).fetch();
    }

    public List<Record> getChampionshipTypes()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.CHAMPIONSHIP_TYPE).fetch();
    }

    public List<Record> getChampionshipSubTypesRelation(int typeId)
    {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.CHAMPIONSHIP_TYPE_RELATION).join(Tables.CHAMPIONSHIP_TYPE).on(new Condition[] { Tables.CHAMPIONSHIP_TYPE_RELATION.CHAMPIONSHIP_TYPE_ID.eq(Tables.CHAMPIONSHIP_TYPE.ID) }).join(Tables.CHAMPIONSHIP_SUB_TYPE).on(new Condition[] { Tables.CHAMPIONSHIP_TYPE_RELATION.CHAMPIONSHIP_SUB_TYPE_ID.eq(Tables.CHAMPIONSHIP_SUB_TYPE.ID) }).where(new Condition[] { Tables.CHAMPIONSHIP_TYPE_RELATION.CHAMPIONSHIP_TYPE_ID.eq(Integer.valueOf(typeId)) }).fetch();
    }

    public ChampionshipSubTypeRecord getChampionshipSubTypes(int typeId)
    {
        return (ChampionshipSubTypeRecord)this.dslContext.fetchOne(Tables.CHAMPIONSHIP_SUB_TYPE, Tables.CHAMPIONSHIP_SUB_TYPE.ID.eq(Integer.valueOf(typeId)));
    }

    public List<Record> getRanks()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.SPORTSMAN_RANK).fetch();
    }

    public List<Record> getRanges()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.STATISTIC_RANGE_TYPE).fetch();
    }

    public List<Record> getStatisticCategories(int typeId)
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.STATISTIC_CATEGORY).where(new Condition[] { Tables.STATISTIC_CATEGORY.STATISTIC_TYPE_ID.eq(Integer.valueOf(typeId)) }).fetch();
    }

    public List<Record> getRefereeCategories()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.REFEREE_CATEGORY).fetch();
    }

    public List<Record> getTrainerQualifications()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.TRAINER_QUALIFICATION).fetch();
    }

    public List<Record> getAwards()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.AWARD).fetch();
    }

    public List<Record> getChampionships()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.CHAMPIONSHIP).fetch();
    }

    public List<Record> getEventTypes()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.EVENT_TYPE).fetch();
    }

    public SportTypeRecord getSportTypeObjectById(int id)
    {
        return (SportTypeRecord)this.dslContext.fetchOne(Tables.SPORT_TYPE, Tables.SPORT_TYPE.ID.eq(Integer.valueOf(id)));
    }

    public SportTypeFileRecord getSportTypeFileObjectById(int id)
    {
        return (SportTypeFileRecord)this.dslContext.fetchOne(Tables.SPORT_TYPE_FILE, Tables.SPORT_TYPE_FILE.ID.eq(Integer.valueOf(id)));
    }

    public List<Record> getSportTypeFiles(int itemId)
    {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.SPORT_TYPE_FILE).leftJoin(Tables.FILE_TYPE).on(new Condition[] { Tables.SPORT_TYPE_FILE.FILE_TYPE_ID.eq(Tables.FILE_TYPE.ID) }).where(new Condition[] { Tables.SPORT_TYPE_FILE.SPORT_TYPE_ID.eq(Integer.valueOf(itemId)) }).orderBy(Tables.SPORT_TYPE_FILE.ID.desc()).fetch();
    }

    public void deleteSportTypeFile(int itemId)
    {
        this.dslContext.deleteFrom(Tables.SPORT_TYPE_FILE).where(new Condition[] { Tables.SPORT_TYPE_FILE.ID.eq(Integer.valueOf(itemId)) }).execute();
    }
}
