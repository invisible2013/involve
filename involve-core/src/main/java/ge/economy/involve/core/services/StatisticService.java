package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.*;
import ge.economy.involve.core.api.dto.*;
import ge.economy.involve.core.api.request.AddStatisticRequest;
import ge.economy.involve.core.dao.StatisticDAO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.StatisticRecord;
import ge.economy.involve.database.database.tables.records.StatisticSportTypeRecord;
import org.jooq.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by nl on 7/20/2016.
 */
@Service
public class StatisticService {
    @Autowired
    private StatisticDAO statisticDAO;
    @Autowired
    private DSLContext dslContext;

    public StatisticService() {
    }

    public StatisticDTO saveStatistic(AddStatisticRequest request) {
        boolean newRecord = false;
        StatisticRecord record = null;
        boolean isMulti = false;
        if(request.getId() != 0) {
            record = this.statisticDAO.getStatisticById(request.getId());
        }

        if(record == null) {
            record = (StatisticRecord)this.dslContext.newRecord(Tables.STATISTIC);
            newRecord = true;
        }

        record.setSportTypeId(Integer.valueOf(request.getSportTypeId()));
        record.setGenderId(Integer.valueOf(request.getGenderId()));
        record.setRankId(Integer.valueOf(request.getRankId()));
        record.setRegionId(Integer.valueOf(request.getRegionId()));
        record.setRangeFrom(Integer.valueOf(request.getRangeFrom()));
        record.setRangeTo(Integer.valueOf(request.getRangeTo()));
        record.setCount(Integer.valueOf(request.getCount()));
        record.setTypeId(Integer.valueOf(request.getTypeId()));
        record.setRangeTypeId(Integer.valueOf(request.getRangeTypeId()));
        record.setIsPara(Boolean.valueOf(request.isPara()));
        if(newRecord) {
            record.store();
        } else {
            record.update();
        }

        if(request.getSportTypes() != null && request.getSportTypes().size() > 0) {
            this.statisticDAO.deleteStatisticSportTypesById(record.getId().intValue());
            Iterator var5 = request.getSportTypes().iterator();

            while(var5.hasNext()) {
                StatisticSportTypeDTO item = (StatisticSportTypeDTO)var5.next();

                try {
                    if(item.getSportTypeId() != 0) {
                        StatisticSportTypeRecord stRecord = (StatisticSportTypeRecord)this.dslContext.newRecord(Tables.STATISTIC_SPORT_TYPE);
                        stRecord.setStatisticId(record.getId());
                        stRecord.setSportTypeId(Integer.valueOf(item.getSportTypeId()));
                        stRecord.store();
                    }
                } catch (Exception var8) {
                    ;
                }
            }
        }

        return null;
    }

    public HashMap<String, Object> getStatisticByType(int typeId, int start, int limit) {
        new HashMap();
        HashMap<String, Object> resultMap = new HashMap();
        HashMap<String, Object> map = this.statisticDAO.getStatisticByType(typeId, start, limit);
        List<StatisticDTO> list = StatisticDTO.translateArray((List)map.get("list"));
        Iterator var7 = list.iterator();

        while(var7.hasNext()) {
            StatisticDTO dto = (StatisticDTO)var7.next();
            dto.setSportTypes(StatisticSportTypeDTO.translateArray(this.statisticDAO.getStatisticSportTypesById(dto.getId().intValue())));
        }

        resultMap.put("list", list);
        resultMap.put("size", map.get("size"));
        return resultMap;
    }

    public void deleteStatistic(int itemId) {
        this.statisticDAO.deleteStatistic(itemId);
    }

    public List<RegionStatisticDTO> getRegionStatistic(int typeId) {
        List<RegionStatisticPojo> result = this.statisticDAO.getRegionStatistic(typeId);
        List<RegionStatisticDTO> statistic = new ArrayList();
        Iterator var4 = result.iterator();

        while(var4.hasNext()) {
            RegionStatisticPojo r = (RegionStatisticPojo)var4.next();
            RegionStatisticDTO d = new RegionStatisticDTO();
            d.setRegionId(r.getStatisticTypeId().intValue());
            d.setCount(Integer.valueOf(r.getCount().intValue()));
            statistic.add(d);
        }

        return statistic;
    }

    public List<RegionStatisticDTO> getAllRegionStatistic() {
        Result<Record3<Integer, Integer, BigDecimal>> result = this.statisticDAO.getAllRegionStatistic();
        List<RegionStatisticDTO> statistic = new ArrayList();
        Iterator var3 = result.iterator();

        while(var3.hasNext()) {
            Record3<Integer, Integer, BigDecimal> r = (Record3)var3.next();
            RegionStatisticDTO d = new RegionStatisticDTO();
            d.setRegionId(((Integer)r.value2()).intValue());
            Optional<RegionStatisticDTO> object = statistic.stream().filter((s) -> {
                return s.getRegionId() == d.getRegionId();
            }).findAny();
            if(object.isPresent()) {
                RegionStatisticDTO item = (RegionStatisticDTO)object.get();
                if(item != null) {
                    d.setSportsmanCount(item.getSportsmanCount());
                    d.setRefereeCount(item.getRefereeCount());
                    d.setTrainerCount(item.getTrainerCount());
                    statistic.remove(item);
                }
            }

            if(((Integer)r.value1()).intValue() == StatisticDTO.STATISTIC_SPORTSMAN) {
                d.setSportsmanCount(Integer.valueOf(((BigDecimal)r.value3()).intValue()));
            } else if(((Integer)r.value1()).intValue() == StatisticDTO.STATISTIC_TRAINER) {
                d.setTrainerCount(Integer.valueOf(((BigDecimal)r.value3()).intValue()));
            } else if(((Integer)r.value1()).intValue() == StatisticDTO.STATISTIC_REFEREE) {
                d.setRefereeCount(Integer.valueOf(((BigDecimal)r.value3()).intValue()));
            }

            d.setParaSportsmanCount(this.statisticDAO.getParaSportsmanCountByRegion(d.getRegionId()));
            d.setWomanSportsmanCount(this.statisticDAO.getSportsmanCountByGenderAndRegion(StatisticDTO.GENDER_WOMAN,d.getRegionId()));
            d.setManSportsmanCount(this.statisticDAO.getSportsmanCountByGenderAndRegion(StatisticDTO.GENDER_MAN,d.getRegionId()));
            statistic.add(d);
        }

        return statistic;
    }

    public RegionStatisticDTO getStatisticByRegion(int regionId) {
        List<RegionStatisticPojo> result = this.statisticDAO.getStatisticByRegion(regionId);
        new ArrayList();
        RegionStatisticDTO regionStatistic = new RegionStatisticDTO();
        regionStatistic.setRegionId(regionId);
        Iterator var5 = result.iterator();

        while(var5.hasNext()) {
            RegionStatisticPojo r = (RegionStatisticPojo)var5.next();
            if(r.getStatisticTypeId().intValue() == StatisticDTO.STATISTIC_SPORTSMAN) {
                regionStatistic.setSportsmanCount(Integer.valueOf(r.getCount().intValue()));
            } else if(r.getStatisticTypeId().intValue() == StatisticDTO.STATISTIC_REFEREE) {
                regionStatistic.setRefereeCount(Integer.valueOf(r.getCount().intValue()));
            } else if(r.getStatisticTypeId().intValue() == StatisticDTO.STATISTIC_TRAINER) {
                regionStatistic.setTrainerCount(Integer.valueOf(r.getCount().intValue()));
            }
        }

        return regionStatistic;
    }

    public MainStatisticDTO getMainStatistic() {
        MainStatisticDTO mainStatistic = new MainStatisticDTO();
        List<RegionStatisticPojo> pojos = this.statisticDAO.getStatisticByType();
        Iterator var3 = pojos.iterator();

        while(var3.hasNext()) {
            RegionStatisticPojo r = (RegionStatisticPojo)var3.next();
            if(r.getStatisticTypeId().intValue() == StatisticDTO.STATISTIC_SPORTSMAN) {
                mainStatistic.setSportsmanCount(Integer.valueOf(r.getCount().intValue()));
            } else if(r.getStatisticTypeId().intValue() == StatisticDTO.STATISTIC_REFEREE) {
                mainStatistic.setRefereeCount(Integer.valueOf(r.getCount().intValue()));
            } else if(r.getStatisticTypeId().intValue() == StatisticDTO.STATISTIC_TRAINER) {
                mainStatistic.setTrainerCount(Integer.valueOf(r.getCount().intValue()));
            }
        }

        mainStatistic.setParaSportsmanCount(this.statisticDAO.getParaSportsmanCount());
        mainStatistic.setWomanSportsmanCount(this.statisticDAO.getSportsmanCountByGender(StatisticDTO.GENDER_WOMAN));
        mainStatistic.setManSportsmanCount(this.statisticDAO.getSportsmanCountByGender(StatisticDTO.GENDER_MAN));
        mainStatistic.setOlimpicChampionCount(this.statisticDAO.getOLympicChampion());
        mainStatistic.setOlimpicGoldMedalCount(this.statisticDAO.getOLympicGoldMedal());
        mainStatistic.setOlimpicSportsmanCount(this.statisticDAO.getOLympicSportsman());
        mainStatistic.setParalympicChampionCount(this.statisticDAO.getParaLympicChampion());
        mainStatistic.setParalympicGoldMedalCount(this.statisticDAO.getParaLympicGoldMedal());
        mainStatistic.setParalympicSportsmanCount(this.statisticDAO.getParaLympicSportsman());
        return mainStatistic;
    }

    public MainStatisticDTO getStatistic(int regionId, int sportTypeId, int genderId, int rankId, int rangeId) {
        List<RegionStatisticPojo> pojos = this.statisticDAO.getStatistic(regionId, sportTypeId, genderId, rankId, rangeId);
        MainStatisticDTO mainStatistic = new MainStatisticDTO();
        Iterator var8 = pojos.iterator();

        while(var8.hasNext()) {
            RegionStatisticPojo r = (RegionStatisticPojo)var8.next();
            if(r.getStatisticTypeId().intValue() == StatisticDTO.STATISTIC_SPORTSMAN) {
                mainStatistic.setSportsmanCount(Integer.valueOf(r.getCount().intValue()));
            } else if(r.getStatisticTypeId().intValue() == StatisticDTO.STATISTIC_REFEREE) {
                mainStatistic.setRefereeCount(Integer.valueOf(r.getCount().intValue()));
            } else if(r.getStatisticTypeId().intValue() == StatisticDTO.STATISTIC_TRAINER) {
                mainStatistic.setTrainerCount(Integer.valueOf(r.getCount().intValue()));
            }
        }

        return mainStatistic;
    }
}
