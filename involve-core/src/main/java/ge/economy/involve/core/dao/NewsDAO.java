package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.NewsFileRecord;
import ge.economy.involve.database.database.tables.records.NewsRecord;
import java.util.HashMap;
import java.util.List;
import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectField;
import org.jooq.SelectOnConditionStep;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDAO
        extends AbstractDAO
{
    public HashMap<String, Object> getNewsByType(int typeId, int start, int limit)
    {
        SelectOnConditionStep<Record> selectConditionStep = this.dslContext.select(new SelectField[0]).from(Tables.NEWS).join(Tables.NEWS_TYPE).on(new Condition[] { Tables.NEWS.TYPE_ID.eq(Tables.NEWS_TYPE.ID) });

        selectConditionStep.where(new Condition[] { Tables.NEWS.TYPE_ID.eq(Integer.valueOf(typeId)) });

        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.NEWS.ID.desc()).limit(limit).offset(start);

        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));
        return map;
    }

    public Record getNewsById(int id)
    {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.NEWS).join(Tables.NEWS_TYPE).on(new Condition[] { Tables.NEWS.TYPE_ID.eq(Tables.NEWS_TYPE.ID) }).where(new Condition[] { Tables.NEWS.ID.eq(Integer.valueOf(id)) }).fetchOne();
    }

    public NewsRecord getNewsObjectById(int id)
    {
        return (NewsRecord)this.dslContext.fetchOne(Tables.NEWS, Tables.NEWS.ID.eq(Integer.valueOf(id)));
    }

    public List<Record> getNewsTypes()
    {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.TRAINER_SPORT_TYPE).fetch();
    }

    public void deleteNews(int itemId)
    {
        this.dslContext.deleteFrom(Tables.NEWS).where(new Condition[] { Tables.NEWS.ID.eq(Integer.valueOf(itemId)) }).execute();
    }

    public NewsFileRecord getNewsFileObjectById(int id)
    {
        return (NewsFileRecord)this.dslContext.fetchOne(Tables.NEWS_FILE, Tables.NEWS_FILE.ID.eq(Integer.valueOf(id)));
    }

    public void deleteNewsFile(int itemId)
    {
        this.dslContext.deleteFrom(Tables.NEWS_FILE).where(new Condition[] { Tables.NEWS_FILE.ID.eq(Integer.valueOf(itemId)) }).execute();
    }

    public List<Record> getNewsFiles(int itemId)
    {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.NEWS_FILE).leftJoin(Tables.FILE_TYPE).on(new Condition[] { Tables.NEWS_FILE.FILE_TYPE_ID.eq(Tables.FILE_TYPE.ID) }).where(new Condition[] { Tables.NEWS_FILE.NEWS_ID.eq(Integer.valueOf(itemId)) }).orderBy(Tables.NEWS_FILE.ID.desc()).fetch();
    }
}
