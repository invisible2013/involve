package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.RefereeRecord;

import java.util.HashMap;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectField;
import org.jooq.SelectOnConditionStep;
import org.springframework.stereotype.Repository;

@Repository
public class RefereeDAO
        extends AbstractDAO {
    public List<Record> getReferees() {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.REFEREE).join(Tables.REFEREE_CATEGORY).on(new Condition[]{Tables.REFEREE.CATEGORY_ID.eq(Tables.REFEREE_CATEGORY.ID)}).join(Tables.REGION).on(new Condition[]{Tables.REFEREE.REGION_ID.eq(Tables.REGION.ID)}).join(Tables.CITY).on(new Condition[]{Tables.REFEREE.CITY_ID.eq(Tables.CITY.ID)}).join(Tables.GENDER).on(new Condition[]{Tables.REFEREE.GENDER_ID.eq(Tables.GENDER.ID)}).orderBy(Tables.REFEREE.ID.desc()).fetch();
    }

    public HashMap<String, Object> searchReferee(String name, String firstLetter, int sportTypeId, int categoryId, int genderId, int regionId, int cityId, int start, int limit) {
        SelectOnConditionStep<Record> selectConditionStep = this.dslContext.select(new SelectField[0]).from(Tables.REFEREE).join(Tables.REFEREE_CATEGORY).on(new Condition[]{Tables.REFEREE.CATEGORY_ID.eq(Tables.REFEREE_CATEGORY.ID)}).join(Tables.REGION).on(new Condition[]{Tables.REFEREE.REGION_ID.eq(Tables.REGION.ID)}).join(Tables.CITY).on(new Condition[]{Tables.REFEREE.CITY_ID.eq(Tables.CITY.ID)}).join(Tables.GENDER).on(new Condition[]{Tables.REFEREE.GENDER_ID.eq(Tables.GENDER.ID)});

        selectConditionStep.where(new Condition[0]);
        if (name != null) {
            String[] names = name.split(" ");
            if (names.length <= 1) {
                selectConditionStep.and(Tables.REFEREE.FIRST_NAME.like("%" + name + "%").or(Tables.REFEREE.LAST_NAME.like("%" + name + "%")));
            } else {
                selectConditionStep.and(Tables.REFEREE.FIRST_NAME.like("%" + names[0] + "%").or(Tables.REFEREE.LAST_NAME.like("%" + names[1] + "%")));
            }
        }
        if (firstLetter != null && firstLetter.length() > 0) {
            selectConditionStep.and(Tables.REFEREE.LAST_NAME.like(firstLetter + "%"));
        }
        if (sportTypeId != 0) {
            selectConditionStep.leftJoin(Tables.REFEREE_SPORT_TYPE).on(new Condition[]{Tables.REFEREE.ID.eq(Tables.REFEREE_SPORT_TYPE.REFEREE_ID)});
        }
        if (categoryId != 0) {
            selectConditionStep.and(Tables.REFEREE.CATEGORY_ID.eq(Integer.valueOf(categoryId)));
        }
        if (genderId != 0) {
            selectConditionStep.and(Tables.REFEREE.GENDER_ID.eq(Integer.valueOf(genderId)));
        }
        if (regionId != 0) {
            selectConditionStep.and(Tables.REFEREE.REGION_ID.eq(Integer.valueOf(regionId)));
        }
        if (cityId != 0) {
            selectConditionStep.and(Tables.REFEREE.CITY_ID.eq(Integer.valueOf(cityId)));
        }
        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.REFEREE.ID.desc()).limit(limit).offset(start);

        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));
        return map;
    }

    public List<Record> getRefereesWithDependencyInfo(Integer refereeId) {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.REFEREE).join(Tables.REFEREE_SPORT_TYPE).on(new Condition[]{Tables.REFEREE.ID.eq(Tables.REFEREE_SPORT_TYPE.REFEREE_ID)}).where(new Condition[]{Tables.REFEREE_SPORT_TYPE.REFEREE_ID.eq(refereeId)}).orderBy(Tables.REFEREE.ID).fetch();
    }

    public Record getRefereeById(int id) {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.REFEREE).join(Tables.REFEREE_CATEGORY).on(new Condition[]{Tables.REFEREE.CATEGORY_ID.eq(Tables.REFEREE_CATEGORY.ID)}).join(Tables.REGION).on(new Condition[]{Tables.REFEREE.REGION_ID.eq(Tables.REGION.ID)}).join(Tables.CITY).on(new Condition[]{Tables.REFEREE.CITY_ID.eq(Tables.CITY.ID)}).join(Tables.GENDER).on(new Condition[]{Tables.REFEREE.GENDER_ID.eq(Tables.GENDER.ID)}).where(new Condition[]{Tables.REFEREE.ID.eq(Integer.valueOf(id))}).fetchOne();
    }

    public RefereeRecord getRefereeObjectById(int id) {
        return (RefereeRecord) this.dslContext.fetchOne(Tables.REFEREE, Tables.REFEREE.ID.eq(Integer.valueOf(id)));
    }

    public List<Record> getRefereeSportTypes(int refereeId) {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.REFEREE_SPORT_TYPE).join(Tables.SPORT_TYPE).on(new Condition[]{Tables.REFEREE_SPORT_TYPE.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID)}).where(new Condition[]{Tables.REFEREE_SPORT_TYPE.REFEREE_ID.eq(Integer.valueOf(refereeId))}).orderBy(Tables.REFEREE_SPORT_TYPE.ID).fetch();
    }

    public void deleteRefereById(int refereId) {
        this.dslContext.deleteFrom(Tables.REFEREE).where(new Condition[]{Tables.REFEREE.ID.eq(Integer.valueOf(refereId))}).execute();
    }

    public void deleteRefereSportTypesById(int refereId) {
        this.dslContext.deleteFrom(Tables.REFEREE_SPORT_TYPE).where(new Condition[]{Tables.REFEREE_SPORT_TYPE.REFEREE_ID.eq(Integer.valueOf(refereId))}).execute();
    }
}
