package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.ReformDTO;
import ge.economy.involve.core.api.request.AddSportsmanRequest;
import ge.economy.involve.core.dao.ReformDAO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.ReformRecord;
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
public class ReformService {
    @Autowired
    private ReformDAO reformDAO;
    @Autowired
    private DSLContext dslContext;
    @Autowired
    private FileService fileService;

    public ReformService() {
    }

    public ReformDTO saveSportsman(AddSportsmanRequest request) {
        boolean newRecord = false;
        ReformRecord record = null;
        if (request.getId() != 0) {
            record = reformDAO.getReformObjectById(request.getId());
        }

        if (record == null) {
            record = (ReformRecord) this.dslContext.newRecord(Tables.REFORM);
            newRecord = true;
        }

        record.setName(request.getAddress());
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }

        return null;
    }

    public ReformDTO getReformById(int itemId) {
        ReformDTO selected = ReformDTO.translate(reformDAO.getReformById(itemId));

        return selected;
    }

    public HashMap<String, Object> getReforms(int start, int limit) {
        new HashMap();
        HashMap<String, Object> resultMap = new HashMap();
        HashMap<String, Object> map = this.reformDAO.getReforms(start, limit);
        List<ReformDTO> items = ReformDTO.translateArray((List) map.get("list"));
        resultMap.put("list", items);
        resultMap.put("size", map.get("size"));
        return resultMap;
    }

  /*  public List<SportsmanFileDTO> getSportsmanFiles(int sportsmanId) {
        return SportsmanFileDTO.translateArray(reformDAO.getReformFiles(sportsmanId));
    }*/


    public HashMap<String, Object> searchSportsmans(String name, String firstLetter, int sportTypeId, int genderId, int regionId, int cityId, int start, int limit) {
        new HashMap();
        HashMap<String, Object> resultMap = new HashMap();
      /*  HashMap<String, Object> map = this.sportsmanDAO.searchSportsmans(name, firstLetter, sportTypeId, genderId, regionId, cityId, start, limit);
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
        */
        return resultMap;
    }



    public void deleteReform(int itemId) {
        this.reformDAO.deleteReform(itemId);
    }

    public void addSportsmanImage(int sportsmanId, int fileTypeId, String fileUrl, MultipartFile file) {
        String fileName = "";
        if (fileTypeId != FileTypes.VIDEO.id()) {
            fileName = this.fileService.saveFile(file, sportsmanId + "");
        }

        try {
            if (fileName != null && !fileName.isEmpty() || fileTypeId == FileTypes.VIDEO.id()) {
              /*  SportsmanFileRecord record = (SportsmanFileRecord) this.dslContext.newRecord(Tables.SPORTSMAN_FILE);
                record.setSportsmanId(Integer.valueOf(sportsmanId));
                record.setFileName(fileName);
                record.setFileTypeId(Integer.valueOf(fileTypeId));
                record.setFileUrl(fileUrl);
                record.store();*/
            }
        } catch (Exception var7) {
            ;
        }

    }

    public void deleteSportsmanFile(int itemId) {
      /*  SportsmanFileRecord record = this.sportsmanDAO.getSportsmanFileObjectById(itemId);
        if (record != null) {
            this.fileService.deleteFile(record.getFileName());
        }

        this.sportsmanDAO.deleteSportsmanFile(itemId);*/
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