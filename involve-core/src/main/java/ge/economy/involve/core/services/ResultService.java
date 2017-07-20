package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.SportsmanDTO;
import ge.economy.involve.core.api.request.AddResultRequest;
import ge.economy.involve.core.dao.ResultDAO;
import ge.economy.involve.core.api.dto.ResultDTO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.ResultRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by nl on 7/20/2016.
 */
@Service
public class ResultService {
    @Autowired
    private ResultDAO resultDAO;
    @Autowired
    private DSLContext dslContext;
    @Autowired
    private SportsmanService sportsmanService;

    public ResultService() {
    }

    public ResultDTO saveResult(AddResultRequest request) {
        boolean newRecord = false;
        ResultRecord record = null;
        if(request.getId() != 0) {
            record = this.resultDAO.getResultById(request.getId());
        }

        if(record == null) {
            record = (ResultRecord)this.dslContext.newRecord(Tables.RESULT);
            newRecord = true;
        }

        record.setChampionshipId(Integer.valueOf(request.getChampionshipId()));
        record.setSportTypeId(Integer.valueOf(request.getSportTypeId()));
        record.setAwardId(Integer.valueOf(request.getAwardId()));
        record.setSportsmanId(Integer.valueOf(request.getSportsmanId()));
        record.setScore(request.getScore());
        record.setNote(request.getNote());
        record.setWeight(request.getWeight());
        record.setDiscipline(request.getDiscipline());
        if(newRecord) {
            record.store();
        } else {
            record.update();
        }

        return null;
    }

    public ResultDTO getResultById(int itemId) {
        return ResultDTO.translate(this.resultDAO.getResultById(itemId));
    }

    public List<ResultDTO> getResultBySportsman(int sportsmanId) {
        return ResultDTO.translateArray(this.resultDAO.getResultBySportsmanId(sportsmanId));
    }

    public List<ResultDTO> getResultBySportTypeId(int sportTypeId) {
        return ResultDTO.translateArray(this.resultDAO.getResultBySportTypeId(sportTypeId));
    }

    public List<ResultDTO> getResults() {
        return ResultDTO.translateArray(this.resultDAO.getResult());
    }

    public void deleteResult(int itemId) {
        this.resultDAO.deleteResult(itemId);
    }

    public List<SportsmanDTO> searchChampionsByType(String name, int championshipSubTypeId, int sportTypeId, int genderId, int regionId, int cityId) {
        List<SportsmanDTO> sportsmans = SportsmanDTO.translateArray(this.resultDAO.searchChampionsByType(name, championshipSubTypeId, sportTypeId, genderId, regionId, cityId));
        Iterator var8 = sportsmans.iterator();

        while(var8.hasNext()) {
            SportsmanDTO s = (SportsmanDTO)var8.next();
            List<ResultDTO> results = this.getResultBySportsman(s.getId().intValue());
            s.setFiles(this.sportsmanService.getSportsmanFiles(s.getId().intValue()));
            long count = results.stream().filter((r) -> {
                return ResultDTO.AWARD_GOLD == r.getAwardId();
            }).count();
            s.setGoldCount(Integer.valueOf((int)count));
            count = results.stream().filter((r) -> {
                return ResultDTO.AWARD_SILVER == r.getAwardId();
            }).count();
            s.setSilverCount(Integer.valueOf((int)count));
            count = results.stream().filter((r) -> {
                return ResultDTO.AWARD_BRONZE == r.getAwardId();
            }).count();
            s.setBronzeCount(Integer.valueOf((int)count));
        }

        return sportsmans;
    }

    public List<SportsmanDTO> getChampionsByType(int championshipSubTypeId) {
        List<SportsmanDTO> sportsmans = SportsmanDTO.translateArray(this.resultDAO.getChampionsByType(championshipSubTypeId));
        Iterator var3 = sportsmans.iterator();

        while(var3.hasNext()) {
            SportsmanDTO s = (SportsmanDTO)var3.next();
            List<ResultDTO> results = this.getResultBySportsman(s.getId().intValue());
            s.setFiles(this.sportsmanService.getSportsmanFiles(s.getId().intValue()));
            long count = results.stream().filter((r) -> {
                return ResultDTO.AWARD_GOLD == r.getAwardId();
            }).count();
            s.setGoldCount(Integer.valueOf((int)count));
            count = results.stream().filter((r) -> {
                return ResultDTO.AWARD_SILVER == r.getAwardId();
            }).count();
            s.setSilverCount(Integer.valueOf((int)count));
            count = results.stream().filter((r) -> {
                return ResultDTO.AWARD_BRONZE == r.getAwardId();
            }).count();
            s.setBronzeCount(Integer.valueOf((int)count));
        }

        return sportsmans;
    }

    public List<SportsmanDTO> getOlimpicPrizeWinners(String name, int sportTypeId, int genderId, int regionId, int cityId) {
        List<SportsmanDTO> sportsmans = SportsmanDTO.translateArray(this.resultDAO.searchOlimpicPrizeWinners(name, sportTypeId, genderId, regionId, cityId));
        Iterator var7 = sportsmans.iterator();

        while(var7.hasNext()) {
            SportsmanDTO s = (SportsmanDTO)var7.next();
            List<ResultDTO> results = this.getResultBySportsman(s.getId().intValue());
            s.setFiles(this.sportsmanService.getSportsmanFiles(s.getId().intValue()));
            long count = results.stream().filter((r) -> {
                return ResultDTO.AWARD_GOLD == r.getAwardId();
            }).count();
            s.setGoldCount(Integer.valueOf((int)count));
            count = results.stream().filter((r) -> {
                return ResultDTO.AWARD_SILVER == r.getAwardId();
            }).count();
            s.setSilverCount(Integer.valueOf((int)count));
            count = results.stream().filter((r) -> {
                return ResultDTO.AWARD_BRONZE == r.getAwardId();
            }).count();
            s.setBronzeCount(Integer.valueOf((int)count));
        }

        return sportsmans;
    }
}