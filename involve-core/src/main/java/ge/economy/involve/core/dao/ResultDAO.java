package ge.economy.involve.core.dao;

import ge.economy.involve.core.api.dto.ChampionshipDTO;
import ge.economy.involve.core.api.dto.ResultDTO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.ResultRecord;
import java.util.List;
import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.SelectField;
import org.jooq.SelectOnConditionStep;
import org.springframework.stereotype.Repository;

@Repository
public class ResultDAO
        extends AbstractDAO
{
    public List<Record> getResult()
    {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.RESULT).join(Tables.CHAMPIONSHIP).on(new Condition[] { Tables.RESULT.CHAMPIONSHIP_ID.eq(Tables.CHAMPIONSHIP.ID) }).join(Tables.SPORT_TYPE).on(new Condition[] { Tables.RESULT.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID) }).join(Tables.SPORTSMAN).on(new Condition[] { Tables.RESULT.SPORTSMAN_ID.eq(Tables.SPORTSMAN.ID) }).join(Tables.AWARD).on(new Condition[] { Tables.RESULT.AWARD_ID.eq(Tables.AWARD.ID) }).orderBy(Tables.RESULT.ID.desc()).fetch();
    }

    public ResultRecord getResultById(int id)
    {
        return (ResultRecord)this.dslContext.fetchOne(Tables.RESULT, Tables.RESULT.ID.eq(Integer.valueOf(id)));
    }

    public List<Record> getResultBySportsmanId(int sportsmanId)
    {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.RESULT).join(Tables.CHAMPIONSHIP).on(new Condition[] { Tables.RESULT.CHAMPIONSHIP_ID.eq(Tables.CHAMPIONSHIP.ID) }).join(Tables.SPORT_TYPE).on(new Condition[] { Tables.RESULT.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID) }).join(Tables.SPORTSMAN).on(new Condition[] { Tables.RESULT.SPORTSMAN_ID.eq(Tables.SPORTSMAN.ID) }).join(Tables.AWARD).on(new Condition[] { Tables.RESULT.AWARD_ID.eq(Tables.AWARD.ID) }).where(new Condition[] { Tables.RESULT.SPORTSMAN_ID.eq(Integer.valueOf(sportsmanId)) }).orderBy(Tables.RESULT.ID.desc()).fetch();
    }

    public List<Record> getResultBySportTypeId(int sportTypeId)
    {
        return

                this.dslContext.select().
                        from(Tables.RESULT).join(Tables.CHAMPIONSHIP).on(new Condition[] { Tables.RESULT.CHAMPIONSHIP_ID.eq(Tables.CHAMPIONSHIP.ID) }).join(Tables.SPORT_TYPE).on(new Condition[] { Tables.RESULT.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID) }).join(Tables.SPORTSMAN).on(new Condition[] { Tables.RESULT.SPORTSMAN_ID.eq(Tables.SPORTSMAN.ID) }).join(Tables.AWARD).on(new Condition[] { Tables.RESULT.AWARD_ID.eq(Tables.AWARD.ID) }).where(new Condition[] { Tables.RESULT.SPORT_TYPE_ID.eq(Integer.valueOf(sportTypeId)) }).orderBy(Tables.RESULT.ID.desc()).fetch();
    }

    public void deleteResult(int itemId)
    {
        this.dslContext.deleteFrom(Tables.RESULT).where(new Condition[] { Tables.RESULT.ID.eq(Integer.valueOf(itemId)) }).execute();
    }

    public List<Record> searchChampionsByType(String name, int championshipSubTypeId, int sportTypeId, int genderId, int regionId, int cityId)
    {
        SelectOnConditionStep<Record> selectConditionStep = this.dslContext.select(new SelectField[0]).from(Tables.SPORTSMAN).join(Tables.REGION).on(new Condition[] { Tables.SPORTSMAN.REGION_ID.eq(Tables.REGION.ID) }).join(Tables.CITY).on(new Condition[] { Tables.SPORTSMAN.CITY_ID.eq(Tables.CITY.ID) }).join(Tables.SPORT_TYPE).on(new Condition[] { Tables.SPORTSMAN.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID) }).join(Tables.GENDER).on(new Condition[] { Tables.SPORTSMAN.GENDER_ID.eq(Tables.GENDER.ID) }).join(Tables.SPORTSMAN_RANK).on(new Condition[] { Tables.SPORTSMAN.RANK_ID.eq(Tables.SPORTSMAN_RANK.ID) });

        selectConditionStep.where(new Condition[] { Tables.SPORTSMAN.ID.in(this.dslContext
                .select(Tables.RESULT.SPORTSMAN_ID)
                .from(Tables.RESULT)
                .join(Tables.CHAMPIONSHIP)
                .on(new Condition[] {Tables.RESULT.CHAMPIONSHIP_ID.eq(Tables.CHAMPIONSHIP.ID) })
                .and(Tables.CHAMPIONSHIP.CHAMPIONSHIP_SUB_TYPE_ID.eq(Integer.valueOf(championshipSubTypeId)))
                .where(new Condition[] {Tables.RESULT.AWARD_ID.eq(Integer.valueOf(ResultDTO.AWARD_GOLD)) })) });
        if (name != null)
        {
            String[] names = name.split(" ");
            if (names.length <= 1) {
                selectConditionStep.and(Tables.SPORTSMAN.FIRST_NAME.like("%" + name + "%").or(Tables.SPORTSMAN.LAST_NAME.like("%" + name + "%")));
            } else {
                selectConditionStep.and(Tables.SPORTSMAN.FIRST_NAME.like("%" + names[0] + "%").or(Tables.SPORTSMAN.LAST_NAME.like("%" + names[1] + "%")));
            }
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
        return
                selectConditionStep.orderBy(Tables.SPORTSMAN.LAST_NAME).fetch();
    }

    public List<Record> searchOlimpicPrizeWinners(String name, int sportTypeId, int genderId, int regionId, int cityId)
    {
        SelectOnConditionStep<Record> selectConditionStep = this.dslContext.select(new SelectField[0]).from(Tables.SPORTSMAN).join(Tables.REGION).on(new Condition[] { Tables.SPORTSMAN.REGION_ID.eq(Tables.REGION.ID) }).join(Tables.CITY).on(new Condition[] { Tables.SPORTSMAN.CITY_ID.eq(Tables.CITY.ID) }).join(Tables.SPORT_TYPE).on(new Condition[] { Tables.SPORTSMAN.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID) }).join(Tables.GENDER).on(new Condition[] { Tables.SPORTSMAN.GENDER_ID.eq(Tables.GENDER.ID) }).join(Tables.SPORTSMAN_RANK).on(new Condition[] { Tables.SPORTSMAN.RANK_ID.eq(Tables.SPORTSMAN_RANK.ID) });

        selectConditionStep.where(new Condition[] { Tables.SPORTSMAN.ID.in(this.dslContext
                .select(Tables.RESULT.SPORTSMAN_ID)
                .from(Tables.RESULT)
                .join(Tables.CHAMPIONSHIP)
                .on(new Condition[] {Tables.RESULT.CHAMPIONSHIP_ID.eq(Tables.CHAMPIONSHIP.ID) })
                .and(Tables.CHAMPIONSHIP.CHAMPIONSHIP_SUB_TYPE_ID.eq(Integer.valueOf(ChampionshipDTO.CHAMPIONSHIP_OLIMPIC)))
                .where(new Condition[] {Tables.RESULT.AWARD_ID.in(new Integer[] {Integer.valueOf(ResultDTO.AWARD_BRONZE), Integer.valueOf(ResultDTO.AWARD_SILVER) })
                        .and(Tables.RESULT.SPORTSMAN_ID.notIn(this.dslContext
                        .select(Tables.RESULT.SPORTSMAN_ID)
                        .from(Tables.RESULT)
                        .join(Tables.CHAMPIONSHIP)
                        .on(new Condition[] {Tables.RESULT.CHAMPIONSHIP_ID.eq(Tables.CHAMPIONSHIP.ID) })
                        .and(Tables.CHAMPIONSHIP.CHAMPIONSHIP_SUB_TYPE_ID.eq(Integer.valueOf(ChampionshipDTO.CHAMPIONSHIP_OLIMPIC)))
                        .where(new Condition[] {Tables.RESULT.AWARD_ID.eq(Integer.valueOf(ResultDTO.AWARD_GOLD)) }))) })) });
        if (name != null)
        {
            String[] names = name.split(" ");
            if (names.length <= 1) {
                selectConditionStep.and(Tables.SPORTSMAN.FIRST_NAME.like("%" + name + "%").or(Tables.SPORTSMAN.LAST_NAME.like("%" + name + "%")));
            } else {
                selectConditionStep.and(Tables.SPORTSMAN.FIRST_NAME.like("%" + names[0] + "%").or(Tables.SPORTSMAN.LAST_NAME.like("%" + names[1] + "%")));
            }
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
        return
                selectConditionStep.orderBy(Tables.SPORTSMAN.LAST_NAME).fetch();
    }

    public List<Record> getChampionsByType(int championshipSubTypeId)
    {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.SPORTSMAN).join(Tables.REGION).on(new Condition[] { Tables.SPORTSMAN.REGION_ID.eq(Tables.REGION.ID) }).join(Tables.CITY).on(new Condition[] { Tables.SPORTSMAN.CITY_ID.eq(Tables.CITY.ID) }).join(Tables.SPORT_TYPE).on(new Condition[] { Tables.SPORTSMAN.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID) }).join(Tables.GENDER).on(new Condition[] { Tables.SPORTSMAN.GENDER_ID.eq(Tables.GENDER.ID) }).join(Tables.SPORTSMAN_RANK).on(new Condition[] { Tables.SPORTSMAN.RANK_ID.eq(Tables.SPORTSMAN_RANK.ID) }).where(new Condition[] { Tables.SPORTSMAN.ID.in(this.dslContext.select(Tables.RESULT.SPORTSMAN_ID).from(Tables.RESULT).join(Tables.CHAMPIONSHIP).on(new Condition[] { Tables.RESULT.CHAMPIONSHIP_ID.eq(Tables.CHAMPIONSHIP.ID) }).and(Tables.CHAMPIONSHIP.CHAMPIONSHIP_SUB_TYPE_ID.eq(Integer.valueOf(championshipSubTypeId))).where(new Condition[] { Tables.RESULT.AWARD_ID.eq(Integer.valueOf(ResultDTO.AWARD_GOLD)) })) }).orderBy(Tables.SPORTSMAN.LAST_NAME).fetch();
    }

    public List<Record> getOlimpicPrizeWinners()
    {
        return

                this.dslContext.select(new SelectField[0]).from(Tables.SPORTSMAN).join(Tables.REGION).on(new Condition[] { Tables.SPORTSMAN.REGION_ID.eq(Tables.REGION.ID) }).join(Tables.CITY).on(new Condition[] { Tables.SPORTSMAN.CITY_ID.eq(Tables.CITY.ID) }).join(Tables.SPORT_TYPE).on(new Condition[] { Tables.SPORTSMAN.SPORT_TYPE_ID.eq(Tables.SPORT_TYPE.ID) }).join(Tables.GENDER).on(new Condition[] { Tables.SPORTSMAN.GENDER_ID.eq(Tables.GENDER.ID) }).join(Tables.SPORTSMAN_RANK).on(new Condition[] { Tables.SPORTSMAN.RANK_ID.eq(Tables.SPORTSMAN_RANK.ID) }).where(new Condition[] { Tables.SPORTSMAN.ID.in(this.dslContext.select(Tables.RESULT.SPORTSMAN_ID).from(Tables.RESULT).join(Tables.CHAMPIONSHIP).on(new Condition[] { Tables.RESULT.CHAMPIONSHIP_ID.eq(Tables.CHAMPIONSHIP.ID) }).and(Tables.CHAMPIONSHIP.CHAMPIONSHIP_SUB_TYPE_ID.eq(Integer.valueOf(ChampionshipDTO.CHAMPIONSHIP_OLIMPIC))).where(new Condition[] { Tables.RESULT.AWARD_ID.in(new Integer[] { Integer.valueOf(ResultDTO.AWARD_BRONZE), Integer.valueOf(ResultDTO.AWARD_SILVER) }) })) }).orderBy(Tables.SPORTSMAN.LAST_NAME).fetch();
    }
}
