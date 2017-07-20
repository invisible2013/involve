package ge.economy.involve.core.dao;

import ge.economy.involve.core.api.dto.RegionStatisticPojo;
import ge.economy.involve.core.api.dto.StatisticDTO;
import ge.economy.involve.core.api.dto.ChampionshipDTO;
import ge.economy.involve.core.api.dto.ResultDTO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.StatisticRecord;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.jooq.Condition;
import org.jooq.GroupField;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.SelectConditionStep;
import org.jooq.SelectField;
import org.jooq.SelectOnConditionStep;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticDAO extends AbstractDAO {
    public HashMap<String, Object> getStatisticByType(int typeId, int start, int limit) {
        SelectOnConditionStep<Record> selectConditionStep = this.dslContext.select(new SelectField[0]).from(Tables.STATISTIC).join(Tables.REGION).on(new Condition[]{Tables.STATISTIC.REGION_ID.eq(Tables.REGION.ID)}).leftJoin(Tables.SPORT_TYPE).on(new Condition[]{Tables.STATISTIC.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID)}).leftJoin(Tables.STATISTIC_CATEGORY).on(new Condition[]{Tables.STATISTIC.RANK_ID.eq(Tables.STATISTIC_CATEGORY.ID)}).leftJoin(Tables.STATISTIC_RANGE_TYPE).on(new Condition[]{Tables.STATISTIC.RANGE_TYPE_ID.eq(Tables.STATISTIC_RANGE_TYPE.ID)}).leftJoin(Tables.GENDER).on(new Condition[]{Tables.STATISTIC.GENDER_ID.eq(Tables.GENDER.ID)});

        selectConditionStep.where(new Condition[]{Tables.STATISTIC.TYPE_ID.eq(Integer.valueOf(typeId))});

        SelectOnConditionStep<Record> selectConditionStepSize = selectConditionStep;
        int recordSize = selectConditionStepSize.fetch().size();
        selectConditionStep.orderBy(Tables.STATISTIC.ID.desc()).limit(limit).offset(start).fetch();

        HashMap<String, Object> map = new HashMap();
        map.put("list", selectConditionStep.fetch());
        map.put("size", Integer.valueOf(recordSize));

        return map;
    }

    public StatisticRecord getStatisticById(int id) {
        return (StatisticRecord) this.dslContext.fetchOne(Tables.STATISTIC, Tables.STATISTIC.ID.eq(Integer.valueOf(id)));
    }

    public void deleteStatistic(int itemId) {
        this.dslContext.deleteFrom(Tables.STATISTIC).where(new Condition[]{Tables.STATISTIC.ID.eq(Integer.valueOf(itemId))}).execute();
    }

    public List<RegionStatisticPojo> getRegionStatistic(int typeId) {
        return

                this.dslContext.select(Tables.STATISTIC.REGION_ID, Tables.STATISTIC.COUNT.sum()).from(Tables.STATISTIC).where(new Condition[]{Tables.STATISTIC.TYPE_ID.eq(Integer.valueOf(typeId))}).groupBy(new GroupField[]{Tables.STATISTIC.REGION_ID}).fetch().into(RegionStatisticPojo.class);
    }

    public org.jooq.Result<Record3<Integer, Integer, BigDecimal>> getAllRegionStatistic() {
        return this.dslContext.select(Tables.STATISTIC.TYPE_ID, Tables.STATISTIC.REGION_ID, Tables.STATISTIC.COUNT.sum()).
                from(Tables.STATISTIC).groupBy(Tables.STATISTIC.TYPE_ID, Tables.STATISTIC.REGION_ID).fetch();
    }

    public List<RegionStatisticPojo> getStatisticByRegion(int regionId) {
        return

                this.dslContext.select(Tables.STATISTIC.TYPE_ID, Tables.STATISTIC.COUNT.sum()).from(Tables.STATISTIC).where(new Condition[]{Tables.STATISTIC.REGION_ID.eq(Integer.valueOf(regionId))}).groupBy(new GroupField[]{Tables.STATISTIC.TYPE_ID}).fetch().into(RegionStatisticPojo.class);
    }

    public List<RegionStatisticPojo> getStatisticByType() {
        return

                this.dslContext.select(Tables.STATISTIC.TYPE_ID, Tables.STATISTIC.COUNT.sum()).from(Tables.STATISTIC).groupBy(new GroupField[]{Tables.STATISTIC.TYPE_ID}).fetch().into(RegionStatisticPojo.class);
    }

    public Integer getParaSportsmanCount() {
        return

                (Integer) ((Record1) dslContext.select(Tables.STATISTIC.COUNT.sum()).
                        from(Tables.STATISTIC).
                        where(Tables.STATISTIC.TYPE_ID.eq(Integer.valueOf(StatisticDTO.STATISTIC_SPORTSMAN)).
                                and(Tables.STATISTIC.IS_PARA.eq(Boolean.valueOf(true)))).
                        fetchOne()).
                        into(Integer.class);
    }

    public Integer getParaSportsmanCountByRegion(int regionId) {
        return

                (Integer) ((Record1) dslContext.select(Tables.STATISTIC.COUNT.sum()).
                        from(Tables.STATISTIC).
                        where(Tables.STATISTIC.TYPE_ID.eq(Integer.valueOf(StatisticDTO.STATISTIC_SPORTSMAN)).
                                and(Tables.STATISTIC.IS_PARA.eq(Boolean.valueOf(true))).
                                and(Tables.STATISTIC.REGION_ID.eq(regionId))).
                        fetchOne()).
                        into(Integer.class);
    }

    public Integer getParaLympicGoldMedal() {
        return

                (Integer) ((Record1) this.dslContext.selectCount().from(Tables.RESULT).join(Tables.SPORTSMAN).on(new Condition[]{Tables.SPORTSMAN.ID.eq(Tables.RESULT.SPORTSMAN_ID)}).where(new Condition[]{Tables.RESULT.AWARD_ID.eq(Integer.valueOf(ResultDTO.AWARD_GOLD))}).and(Tables.SPORTSMAN.IS_PARALYMPIC.eq(Boolean.valueOf(true))).fetchOne()).into(Integer.class);
    }

    public Integer getParaLympicSportsman() {
        return

                (Integer) ((Record1) this.dslContext.selectCount().from(Tables.SPORTSMAN).where(new Condition[]{Tables.SPORTSMAN.IS_PARALYMPIC.eq(Boolean.valueOf(true))}).fetchOne()).into(Integer.class);
    }

    public Integer getParaLympicChampion() {
        return

                (Integer) ((Record1) this.dslContext.selectCount().from(Tables.SPORTSMAN).where(new Condition[]{Tables.SPORTSMAN.ID.in(this.dslContext.select(Tables.RESULT.SPORTSMAN_ID).from(Tables.RESULT).where(new Condition[]{Tables.RESULT.AWARD_ID.eq(Integer.valueOf(ResultDTO.AWARD_GOLD))}))}).and(Tables.SPORTSMAN.IS_PARALYMPIC.eq(Boolean.valueOf(true))).fetchOne()).into(Integer.class);
    }

    public Integer getOLympicChampion() {
        return

                (Integer) ((Record1) this.dslContext.selectCount().from(Tables.SPORTSMAN).where(new Condition[]{Tables.SPORTSMAN.ID.in(this.dslContext.select(Tables.RESULT.SPORTSMAN_ID).from(Tables.RESULT).join(Tables.CHAMPIONSHIP).on(new Condition[]{Tables.RESULT.CHAMPIONSHIP_ID.eq(Tables.CHAMPIONSHIP.ID).and(Tables.CHAMPIONSHIP.CHAMPIONSHIP_SUB_TYPE_ID.eq(Integer.valueOf(ChampionshipDTO.CHAMPIONSHIP_OLIMPIC)))}).where(new Condition[]{Tables.RESULT.AWARD_ID.eq(Integer.valueOf(ResultDTO.AWARD_GOLD))}))}).fetchOne()).into(Integer.class);
    }

    public Integer getOLympicGoldMedal() {
        return

                (Integer) ((Record1) this.dslContext.selectCount().from(Tables.RESULT).join(Tables.CHAMPIONSHIP).on(new Condition[]{Tables.RESULT.CHAMPIONSHIP_ID.eq(Tables.CHAMPIONSHIP.ID).and(Tables.CHAMPIONSHIP.CHAMPIONSHIP_SUB_TYPE_ID.eq(Integer.valueOf(ChampionshipDTO.CHAMPIONSHIP_OLIMPIC)))}).where(new Condition[]{Tables.RESULT.AWARD_ID.eq(Integer.valueOf(ResultDTO.AWARD_GOLD))}).fetchOne()).into(Integer.class);
    }

    public Integer getOLympicSportsman() {
        return

                (Integer) ((Record1) this.dslContext.selectCount().from(Tables.SPORTSMAN).where(new Condition[]{Tables.SPORTSMAN.ID.in(this.dslContext.select(Tables.RESULT.SPORTSMAN_ID).from(Tables.RESULT).join(Tables.CHAMPIONSHIP).on(new Condition[]{Tables.RESULT.CHAMPIONSHIP_ID.eq(Tables.CHAMPIONSHIP.ID).and(Tables.CHAMPIONSHIP.CHAMPIONSHIP_SUB_TYPE_ID.eq(Integer.valueOf(ChampionshipDTO.CHAMPIONSHIP_OLIMPIC)))}))}).fetchOne()).into(Integer.class);
    }

    public Integer getSportsmanCountByGender(int genderId) {
        return

                (Integer) ((Record1) this.dslContext.select(Tables.STATISTIC.COUNT.sum()).from(Tables.STATISTIC).where(new Condition[]{Tables.STATISTIC.GENDER_ID.eq(Integer.valueOf(genderId))}).and(Tables.STATISTIC.TYPE_ID.eq(Integer.valueOf(StatisticDTO.STATISTIC_SPORTSMAN))).fetchOne()).into(Integer.class);
    }


    public Integer getSportsmanCountByGenderAndRegion(int genderId, int regionId) {
        return (Integer) ((Record1) dslContext.select(Tables.STATISTIC.COUNT.sum()).
                from(Tables.STATISTIC).
                where(Tables.STATISTIC.GENDER_ID.eq(Integer.valueOf(genderId))).
                and(Tables.STATISTIC.TYPE_ID.eq(Integer.valueOf(StatisticDTO.STATISTIC_SPORTSMAN))).
                and(Tables.STATISTIC.REGION_ID.eq(regionId)).
                fetchOne()).into(Integer.class);
    }

    public List<RegionStatisticPojo> getStatistic(int regionId, int sportTypeId, int genderId, int rankId, int rangeId) {
        SelectConditionStep<Record2<Integer, BigDecimal>> selectConditionStep = this.dslContext.select(Tables.STATISTIC.TYPE_ID, Tables.STATISTIC.COUNT.sum()).from(Tables.STATISTIC).where(new Condition[0]);
        if (sportTypeId != 0) {
            selectConditionStep.and(Tables.STATISTIC.ID.in(dslContext.select(Tables.STATISTIC_SPORT_TYPE.STATISTIC_ID).
                    from(Tables.STATISTIC_SPORT_TYPE).
                    where(Tables.STATISTIC_SPORT_TYPE.SPORT_TYPE_ID
                            .eq(sportTypeId))));
        }
        if (regionId != 0) {
            selectConditionStep.and(Tables.STATISTIC.REGION_ID.eq(Integer.valueOf(regionId)));
        }
        if (genderId != 0) {
            selectConditionStep.and(Tables.STATISTIC.GENDER_ID.eq(Integer.valueOf(genderId)));
        }
        if (rankId != 0) {
            selectConditionStep.and(Tables.STATISTIC.RANK_ID.eq(Integer.valueOf(rankId)));
        }
        if (rangeId != 0) {
            selectConditionStep.and(Tables.STATISTIC.RANGE_TYPE_ID.eq(Integer.valueOf(rangeId)));
        }
        return selectConditionStep.groupBy(new GroupField[]{Tables.STATISTIC.TYPE_ID}).fetch().into(RegionStatisticPojo.class);
    }

    public void deleteStatisticSportTypesById(int statisticId) {
        this.dslContext.deleteFrom(Tables.STATISTIC_SPORT_TYPE).where(Tables.STATISTIC_SPORT_TYPE.STATISTIC_ID.eq(Integer.valueOf(statisticId))).execute();
    }

    public List<Record> getStatisticSportTypesById(int id) {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.STATISTIC_SPORT_TYPE).join(Tables.SPORT_TYPE).on(new Condition[]{Tables.STATISTIC_SPORT_TYPE.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID)}).where(new Condition[]{Tables.STATISTIC_SPORT_TYPE.STATISTIC_ID.eq(Integer.valueOf(id))}).fetch();
    }
}
