package ge.economy.involve.core.dao;

import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.SportsmanFileRecord;
import ge.economy.involve.database.database.tables.records.SportsmanRecord;

import java.util.HashMap;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectField;
import org.jooq.SelectOnConditionStep;
import org.springframework.stereotype.Repository;

@Repository
public class SportsmanDAO
        extends AbstractDAO {
    public HashMap<String, Object> getSportsmans(int start, int limit) {
        SelectOnConditionStep<Record> selectConditionStep = this.dslContext.select(new SelectField[0]).from(Tables.SPORTSMAN).join(Tables.REGION).on(new Condition[]{Tables.SPORTSMAN.REGION_ID.eq(Tables.REGION.ID)}).join(Tables.CITY).on(new Condition[]{Tables.SPORTSMAN.CITY_ID.eq(Tables.CITY.ID)}).join(Tables.SPORT_TYPE).on(new Condition[]{Tables.SPORTSMAN.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID)}).join(Tables.GENDER).on(new Condition[]{Tables.SPORTSMAN.GENDER_ID.eq(Tables.GENDER.ID)}).join(Tables.SPORTSMAN_RANK).on(new Condition[]{Tables.SPORTSMAN.RANK_ID.eq(Tables.SPORTSMAN_RANK.ID)});
        selectConditionStep.where(new Condition[0]);
        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.SPORTSMAN.ID.desc()).limit(limit).offset(start);
        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));

        return map;
    }

    public List<Record> getSportsmansAlphabet() {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.SPORTSMAN).join(Tables.REGION).on(new Condition[]{Tables.SPORTSMAN.REGION_ID.eq(Tables.REGION.ID)}).join(Tables.CITY).on(new Condition[]{Tables.SPORTSMAN.CITY_ID.eq(Tables.CITY.ID)}).join(Tables.SPORT_TYPE).on(new Condition[]{Tables.SPORTSMAN.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID)}).join(Tables.GENDER).on(new Condition[]{Tables.SPORTSMAN.GENDER_ID.eq(Tables.GENDER.ID)}).join(Tables.SPORTSMAN_RANK).on(new Condition[]{Tables.SPORTSMAN.RANK_ID.eq(Tables.SPORTSMAN_RANK.ID)}).orderBy(Tables.SPORTSMAN.LAST_NAME).fetch();
    }

    public HashMap<String, Object> searchSportsmans(String name, String firstLetter, int sportTypeId, int genderId, int regionId, int cityId, int start, int limit) {
        SelectOnConditionStep<Record> selectConditionStep = this.dslContext.select(new SelectField[0]).from(Tables.SPORTSMAN).join(Tables.REGION).on(new Condition[]{Tables.SPORTSMAN.REGION_ID.eq(Tables.REGION.ID)}).join(Tables.CITY).on(new Condition[]{Tables.SPORTSMAN.CITY_ID.eq(Tables.CITY.ID)}).join(Tables.SPORT_TYPE).on(new Condition[]{Tables.SPORTSMAN.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID)}).join(Tables.GENDER).on(new Condition[]{Tables.SPORTSMAN.GENDER_ID.eq(Tables.GENDER.ID)}).join(Tables.SPORTSMAN_RANK).on(new Condition[]{Tables.SPORTSMAN.RANK_ID.eq(Tables.SPORTSMAN_RANK.ID)});

        selectConditionStep.where(new Condition[0]);
        if (name != null) {
            String[] names = name.split(" ");
            if (names.length <= 1) {
                selectConditionStep.and(Tables.SPORTSMAN.FIRST_NAME.like("%" + name + "%").or(Tables.SPORTSMAN.LAST_NAME.like("%" + name + "%")));
            } else {
                selectConditionStep.and(Tables.SPORTSMAN.FIRST_NAME.like("%" + names[0] + "%").or(Tables.SPORTSMAN.LAST_NAME.like("%" + names[1] + "%")));
            }
        }
        if (firstLetter != null && firstLetter.length() > 0) {
            selectConditionStep.and(Tables.SPORTSMAN.LAST_NAME.like(firstLetter + "%"));
        }
        if (sportTypeId != 0) {
            selectConditionStep.and(Tables.SPORTSMAN.SPORT_TYPE_ID.eq(Integer.valueOf(sportTypeId)));
        }
        if (genderId != 0) {
            selectConditionStep.and(Tables.SPORTSMAN.GENDER_ID.eq(Integer.valueOf(genderId)));
        }
        if (regionId != 0) {
            selectConditionStep.and(Tables.SPORTSMAN.REGION_ID.eq(Integer.valueOf(regionId)));
        }
        if (cityId != 0) {
            selectConditionStep.and(Tables.SPORTSMAN.CITY_ID.eq(Integer.valueOf(cityId)));
        }
        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.SPORTSMAN.ID.desc()).limit(limit).offset(start);

        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));
        return map;
    }

    public Record getSportsmanById(int id) {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.SPORTSMAN).join(Tables.REGION).on(new Condition[]{Tables.SPORTSMAN.REGION_ID.eq(Tables.REGION.ID)}).join(Tables.CITY).on(new Condition[]{Tables.SPORTSMAN.CITY_ID.eq(Tables.CITY.ID)}).join(Tables.SPORT_TYPE).on(new Condition[]{Tables.SPORTSMAN.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID)}).join(Tables.GENDER).on(new Condition[]{Tables.SPORTSMAN.GENDER_ID.eq(Tables.GENDER.ID)}).join(Tables.SPORTSMAN_RANK).on(new Condition[]{Tables.SPORTSMAN.RANK_ID.eq(Tables.SPORTSMAN_RANK.ID)}).where(new Condition[]{Tables.SPORTSMAN.ID.eq(Integer.valueOf(id))}).fetchOne();
    }

    public SportsmanRecord getSportsmanObjectById(int id) {
        return (SportsmanRecord) this.dslContext.fetchOne(Tables.SPORTSMAN, Tables.SPORTSMAN.ID.eq(Integer.valueOf(id)));
    }

    public List<Record> getSportTypes() {
        return
                this.dslContext.select(new SelectField[0]).from(Tables.SPORT_TYPE).fetch();
    }

    public void deleteSportsman(int sportsmanId) {
        this.dslContext.deleteFrom(Tables.SPORTSMAN).where(new Condition[]{Tables.SPORTSMAN.ID.eq(Integer.valueOf(sportsmanId))}).execute();
    }

    public SportsmanFileRecord getSportsmanFileObjectById(int id) {
        return (SportsmanFileRecord) this.dslContext.fetchOne(Tables.SPORTSMAN_FILE, Tables.SPORTSMAN_FILE.ID.eq(Integer.valueOf(id)));
    }

    public void deleteSportsmanFile(int itemId) {
        this.dslContext.deleteFrom(Tables.SPORTSMAN_FILE).where(new Condition[]{Tables.SPORTSMAN_FILE.ID.eq(Integer.valueOf(itemId))}).execute();
    }

    public List<Record> getSportsmanFiles(int sportsmanId) {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.SPORTSMAN_FILE).leftJoin(Tables.FILE_TYPE).on(new Condition[]{Tables.SPORTSMAN_FILE.FILE_TYPE_ID.eq(Tables.FILE_TYPE.ID)}).where(new Condition[]{Tables.SPORTSMAN_FILE.SPORTSMAN_ID.eq(Integer.valueOf(sportsmanId))}).fetch();
    }
}
