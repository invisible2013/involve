package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.ResultDTO;
import ge.economy.involve.core.api.dto.SportTypeDTO;
import ge.economy.involve.core.api.dto.SportsmanDTO;
import ge.economy.involve.core.api.dto.SportsmanFileDTO;
import ge.economy.involve.core.api.request.AddSportsmanRequest;
import ge.economy.involve.core.api.dto.*;
import ge.economy.involve.core.dao.SportsmanDAO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.SportsmanFileRecord;
import ge.economy.involve.database.database.tables.records.SportsmanRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nl on 7/20/2016.
 */
@Service
public class SportsmanService {
    @Autowired
    private SportsmanDAO sportsmanDAO;
    @Autowired
    private DSLContext dslContext;
    @Autowired
    private FileService fileService;
    @Autowired
    private ResultService resultService;

    public SportsmanService() {
    }

    public SportsmanDTO saveSportsman(AddSportsmanRequest request) {
        boolean newRecord = false;
        SportsmanRecord record = null;
        if (request.getId() != 0) {
            record = this.sportsmanDAO.getSportsmanObjectById(request.getId());
        }

        if (record == null) {
            record = (SportsmanRecord) this.dslContext.newRecord(Tables.SPORTSMAN);
            newRecord = true;
        }

        record.setFirstName(request.getFirstName());
        record.setLastName(request.getLastName());
        record.setBirthDate(request.getBirthDate());
        record.setCareerStartDate(request.getCareerStartDate());
        record.setCareerEndDate(request.getCareerEndDate());
        record.setDeathDate(request.getDeathDate());
        record.setSportTypeId(request.getSportTypeId());
        record.setRegionId(request.getRegionId());
        record.setGenderId(request.getGenderId());
        record.setRankId(request.getRankId());
        record.setCityId(request.getCityId());
        record.setHeight(request.getHeight());
        record.setBiography(request.getBiography());
        record.setIsParaSportsman(Boolean.valueOf(request.isParaSportsman()));
        record.setIsOlimpic(Boolean.valueOf(request.isOlimpic()));
        record.setDistrict(request.getDistrict());
        record.setComment(request.getComment());
        record.setIsParalympic(Boolean.valueOf(request.isParalympic()));
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }

        return null;
    }

    public SportsmanDTO getSportsmanById(int sportsmanId) {
        SportsmanDTO selected = SportsmanDTO.translate(this.sportsmanDAO.getSportsmanById(sportsmanId));
        List<ResultDTO> results = this.resultService.getResultBySportsman(selected.getId().intValue());
        selected.setFiles(this.getSportsmanFiles(selected.getId().intValue()));
        selected.setResults(results);
        long count = results.stream().filter((r) -> {
            return ResultDTO.AWARD_GOLD == r.getAwardId();
        }).count();
        selected.setGoldCount(Integer.valueOf((int) count));
        count = results.stream().filter((r) -> {
            return ResultDTO.AWARD_SILVER == r.getAwardId();
        }).count();
        selected.setSilverCount(Integer.valueOf((int) count));
        count = results.stream().filter((r) -> {
            return ResultDTO.AWARD_BRONZE == r.getAwardId();
        }).count();
        selected.setBronzeCount(Integer.valueOf((int) count));
        return selected;
    }

    public HashMap<String, Object> getSportsmans(int start, int limit) {
        new HashMap();
        HashMap<String, Object> resultMap = new HashMap();
        HashMap<String, Object> map = this.sportsmanDAO.getSportsmans(start, limit);
        List<SportsmanDTO> sportsmans = SportsmanDTO.translateArray((List) map.get("list"));
        resultMap.put("list", sportsmans);
        resultMap.put("size", map.get("size"));
        return resultMap;
    }

    public List<SportsmanFileDTO> getSportsmanFiles(int sportsmanId) {
        return SportsmanFileDTO.translateArray(this.sportsmanDAO.getSportsmanFiles(sportsmanId));
    }

    public List<SportsmanDTO> getSportsmansAlphabet() {
        return SportsmanDTO.translateArray(this.sportsmanDAO.getSportsmansAlphabet());
    }

    public HashMap<String, Object> searchSportsmans(String name, String firstLetter, int sportTypeId, int genderId, int regionId, int cityId, int start, int limit) {
        new HashMap();
        HashMap<String, Object> resultMap = new HashMap();
        HashMap<String, Object> map = this.sportsmanDAO.searchSportsmans(name, firstLetter, sportTypeId, genderId, regionId, cityId, start, limit);
        List<SportsmanDTO> sportsmans = SportsmanDTO.translateArray((List) map.get("list"));
        Iterator var11 = sportsmans.iterator();

        while (var11.hasNext()) {
            SportsmanDTO s = (SportsmanDTO) var11.next();
            List<ResultDTO> results = this.resultService.getResultBySportsman(s.getId().intValue());
            s.setFiles(this.getSportsmanFiles(s.getId().intValue()));
            long count = results.stream().filter((r) -> {
                return ResultDTO.AWARD_GOLD == r.getAwardId();
            }).count();
            s.setGoldCount(Integer.valueOf((int) count));
            count = results.stream().filter((r) -> {
                return ResultDTO.AWARD_SILVER == r.getAwardId();
            }).count();
            s.setSilverCount(Integer.valueOf((int) count));
            count = results.stream().filter((r) -> {
                return ResultDTO.AWARD_BRONZE == r.getAwardId();
            }).count();
            s.setBronzeCount(Integer.valueOf((int) count));
        }

        resultMap.put("list", sportsmans);
        resultMap.put("size", map.get("size"));
        return resultMap;
    }

    public List<SportTypeDTO> getSportTypes() {
        return SportTypeDTO.translateArray(this.sportsmanDAO.getSportTypes());
    }

    public void deleteSportsman(int sportsmanId) {
        this.sportsmanDAO.deleteSportsman(sportsmanId);
    }

    public void addSportsmanImage(int sportsmanId, int fileTypeId, String fileUrl, MultipartFile file) {
        String fileName = "";
        if (fileTypeId != FileTypes.VIDEO.id()) {
            fileName = this.fileService.saveFile(file, sportsmanId + "");
        }

        try {
            if (fileName != null && !fileName.isEmpty() || fileTypeId == FileTypes.VIDEO.id()) {
                SportsmanFileRecord record = (SportsmanFileRecord) this.dslContext.newRecord(Tables.SPORTSMAN_FILE);
                record.setSportsmanId(Integer.valueOf(sportsmanId));
                record.setFileName(fileName);
                record.setFileTypeId(Integer.valueOf(fileTypeId));
                record.setFileUrl(fileUrl);
                record.store();
            }
        } catch (Exception var7) {
            ;
        }

    }

    public void deleteSportsmanFile(int itemId) {
        SportsmanFileRecord record = this.sportsmanDAO.getSportsmanFileObjectById(itemId);
        if (record != null) {
            this.fileService.deleteFile(record.getFileName());
        }

        this.sportsmanDAO.deleteSportsmanFile(itemId);
    }

    public void setMainFile(int itemId) {
        SportsmanFileRecord record = this.sportsmanDAO.getSportsmanFileObjectById(itemId);
        if (record != null) {
            List<Record> files = this.sportsmanDAO.getSportsmanFiles(record.getSportsmanId().intValue());
            Iterator var4 = files.iterator();

            while (var4.hasNext()) {
                Record s = (Record) var4.next();
                SportsmanFileRecord rec = this.sportsmanDAO.getSportsmanFileObjectById(((Integer) s.getValue(Tables.SPORTSMAN_FILE.ID)).intValue());
                rec.setIsMain(Boolean.valueOf(false));
                rec.update();
            }

            record.setIsMain(Boolean.valueOf(true));
            record.update();
        }

    }
}

enum FileTypes {
    IMAGE(1),
    FILE(2),
    VIDEO(3);
    private int id;

    FileTypes(int index) {
        this.id = index;
    }

    public int id() {
        return this.id;
    }
}