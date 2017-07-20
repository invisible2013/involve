package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.TrainerRecord;
import java.util.HashMap;
import java.util.List;
import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectOnConditionStep;
import org.springframework.stereotype.Repository;

@Repository
public class TrainerDAO
        extends AbstractDAO
{
    public List<Record> getTrainers()
    {
        return

                this.dslContext.select().from(Tables.TRAINER).join(Tables.TRAINER_QUALIFICATION).on(new Condition[] { Tables.TRAINER.QUALIFICATION_ID.eq(Tables.TRAINER_QUALIFICATION.ID) }).join(Tables.REGION).on(new Condition[] { Tables.TRAINER.REGION_ID.eq(Tables.REGION.ID) }).join(Tables.CITY).on(new Condition[] { Tables.TRAINER.CITY_ID.eq(Tables.CITY.ID) }).join(Tables.GENDER).on(new Condition[] { Tables.TRAINER.GENDER_ID.eq(Tables.GENDER.ID) }).orderBy(Tables.TRAINER.ID.desc()).fetch();
    }

    public HashMap<String, Object> searchTrainers(String name,String firstLetter, int sportTypeId, int categoryId, int genderId, int regionId, int cityId, int start, int limit)
    {
        SelectOnConditionStep<Record> selectConditionStep = this.dslContext.select().from(Tables.TRAINER).join(Tables.TRAINER_QUALIFICATION).on(new Condition[] { Tables.TRAINER.QUALIFICATION_ID.eq(Tables.TRAINER_QUALIFICATION.ID) }).join(Tables.REGION).on(new Condition[] { Tables.TRAINER.REGION_ID.eq(Tables.REGION.ID) }).join(Tables.CITY).on(new Condition[] { Tables.TRAINER.CITY_ID.eq(Tables.CITY.ID) }).join(Tables.GENDER).on(new Condition[] { Tables.TRAINER.GENDER_ID.eq(Tables.GENDER.ID) });

        selectConditionStep.where(new Condition[0]);
        if (name != null)
        {
            String[] names = name.split(" ");
            if (names.length <= 1) {
                selectConditionStep.and(Tables.TRAINER.FIRST_NAME.like("%" + name + "%").or(Tables.TRAINER.LAST_NAME.like("%" + name + "%")));
            } else {
                selectConditionStep.and(Tables.TRAINER.FIRST_NAME.like("%" + names[0] + "%").or(Tables.TRAINER.LAST_NAME.like("%" + names[1] + "%")));
            }
        }
        if (firstLetter != null && firstLetter.length() > 0) {
            selectConditionStep.and(Tables.TRAINER.LAST_NAME.like(firstLetter + "%"));
        }
        if (sportTypeId != 0) {
            selectConditionStep.leftJoin(Tables.TRAINER_SPORT_TYPE).on(new Condition[] { Tables.TRAINER.ID.eq(Tables.TRAINER_SPORT_TYPE.TRAINER_ID) });
        }
        if (categoryId != 0) {
            selectConditionStep.and(Tables.TRAINER.QUALIFICATION_ID.eq(Integer.valueOf(categoryId)));
        }
        if (genderId != 0) {
            selectConditionStep.and(Tables.TRAINER.GENDER_ID.eq(Integer.valueOf(genderId)));
        }
        if (regionId != 0) {
            selectConditionStep.and(Tables.TRAINER.REGION_ID.eq(Integer.valueOf(regionId)));
        }
        if (cityId != 0) {
            selectConditionStep.and(Tables.TRAINER.CITY_ID.eq(Integer.valueOf(cityId)));
        }
        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.TRAINER.ID.desc()).limit(limit).offset(start);

        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));
        return map;
    }

    public List<Record> getTrainerWithDependencyInfo(Integer trainerId)
    {
        return

                this.dslContext.select().from(Tables.TRAINER).
                        join(Tables.TRAINER_SPORT_TYPE).on(Tables.TRAINER.ID.eq(Tables.TRAINER_SPORT_TYPE.TRAINER_ID)).
                        where(Tables.TRAINER_SPORT_TYPE.TRAINER_ID.eq(trainerId)).
                        orderBy(Tables.TRAINER.ID).
                        fetch();
    }

    public Record getTrainerById(int id)
    {
        return

                this.dslContext.select().from(Tables.TRAINER).join(Tables.TRAINER_QUALIFICATION).on(new Condition[] { Tables.TRAINER.QUALIFICATION_ID.eq(Tables.TRAINER_QUALIFICATION.ID) }).join(Tables.REGION).on(new Condition[] { Tables.TRAINER.REGION_ID.eq(Tables.REGION.ID) }).join(Tables.CITY).on(new Condition[] { Tables.TRAINER.CITY_ID.eq(Tables.CITY.ID) }).join(Tables.GENDER).on(new Condition[] { Tables.TRAINER.GENDER_ID.eq(Tables.GENDER.ID) }).where(new Condition[] { Tables.TRAINER.ID.eq(Integer.valueOf(id)) }).fetchOne();
    }

    public TrainerRecord getTrainerObjectById(int id)
    {
        return (TrainerRecord)this.dslContext.fetchOne(Tables.TRAINER, Tables.TRAINER.ID.eq(Integer.valueOf(id)));
    }

    public void deleteTrainerSportTypesById(int trainerId)
    {
        this.dslContext.deleteFrom(Tables.TRAINER_SPORT_TYPE).where(new Condition[] { Tables.TRAINER_SPORT_TYPE.TRAINER_ID.eq(Integer.valueOf(trainerId)) }).execute();
    }

    public void deleteTrainerById(int trainerId)
    {
        this.dslContext.deleteFrom(Tables.TRAINER).where(new Condition[] { Tables.TRAINER.ID.eq(Integer.valueOf(trainerId)) }).execute();
    }

    public List<Record> getTrainerSportTypes(int trainerId)
    {
        return

                this.dslContext.select().from(Tables.TRAINER_SPORT_TYPE).join(Tables.SPORT_TYPE).on(new Condition[] { Tables.TRAINER_SPORT_TYPE.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID) }).where(new Condition[] { Tables.TRAINER_SPORT_TYPE.TRAINER_ID.eq(Integer.valueOf(trainerId)) }).orderBy(Tables.TRAINER_SPORT_TYPE.ID).fetch();
    }
}
