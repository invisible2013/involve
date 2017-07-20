package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.ChampionshipRecord;

import java.util.List;

import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectField;
import org.springframework.stereotype.Repository;

@Repository
public class ChampionshipDAO
        extends AbstractDAO {
    public List<Record> getChampionships() {
        return this.dslContext.select(new SelectField[0]).from(Tables.CHAMPIONSHIP).join(Tables.CHAMPIONSHIP_TYPE).on(new Condition[]{Tables.CHAMPIONSHIP.CHAMPIONSHIP_TYPE_ID.eq(Tables.CHAMPIONSHIP_TYPE.ID)}).join(Tables.CHAMPIONSHIP_SUB_TYPE).on(new Condition[]{Tables.CHAMPIONSHIP.CHAMPIONSHIP_SUB_TYPE_ID.eq(Tables.CHAMPIONSHIP_SUB_TYPE.ID)}).orderBy(Tables.CHAMPIONSHIP.ID.desc()).fetch();
    }

    public List<Record> getChampionshipsAlphabet() {
        return this.dslContext.select(new SelectField[0]).from(Tables.CHAMPIONSHIP).join(Tables.CHAMPIONSHIP_TYPE).on(new Condition[]{Tables.CHAMPIONSHIP.CHAMPIONSHIP_TYPE_ID.eq(Tables.CHAMPIONSHIP_TYPE.ID)}).join(Tables.CHAMPIONSHIP_SUB_TYPE).on(new Condition[]{Tables.CHAMPIONSHIP.CHAMPIONSHIP_SUB_TYPE_ID.eq(Tables.CHAMPIONSHIP_SUB_TYPE.ID)}).orderBy(Tables.CHAMPIONSHIP.NAME).fetch();
    }

    public ChampionshipRecord getChampionshiptById(int id) {
        return (ChampionshipRecord) this.dslContext.fetchOne(Tables.CHAMPIONSHIP, Tables.CHAMPIONSHIP.ID.eq(Integer.valueOf(id)));
    }

    public void deleteChampionship(int itemId) {
        this.dslContext.deleteFrom(Tables.CHAMPIONSHIP).where(new Condition[]{Tables.CHAMPIONSHIP.ID.eq(Integer.valueOf(itemId))}).execute();
    }
}
