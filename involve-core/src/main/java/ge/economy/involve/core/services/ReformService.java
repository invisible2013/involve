package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.*;
import ge.economy.involve.core.api.request.AddReformRequest;
import ge.economy.involve.core.api.request.AddSessionPollRequest;
import ge.economy.involve.core.api.request.AddSessionRequest;
import ge.economy.involve.core.api.request.AddSportsmanRequest;
import ge.economy.involve.core.dao.ReformDAO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.*;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.Date;
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

    public ReformDTO saveReform(AddReformRequest request) {
        boolean newRecord = false;
        ReformRecord record = null;
        if (request.getId() != null) {
            record = reformDAO.getReformObjectById(request.getId());
        }

        if (record == null) {
            record = (ReformRecord) this.dslContext.newRecord(Tables.REFORM);
            newRecord = true;
        }

        record.setName(request.getName());
        record.setReformTypeId(request.getReformTypeId());
        record.setExperience(request.getExperience());
        record.setGeneralInfo(request.getGeneralInfo());
        record.setProgressBarName_1(request.getProgressBarName1());
        record.setProgressBarName_2(request.getProgressBarName2());
        record.setProgressBarName_3(request.getProgressBarName3());
        if (request.getProgressBarPercent1() != null) {
            record.setProgressBarPercent_1(request.getProgressBarPercent1().toString());
        }
        if (request.getProgressBarPercent2() != null) {
            record.setProgressBarPercent_2(request.getProgressBarPercent2().toString());
        }
        if (request.getProgressBarPercent3() != null) {
            record.setProgressBarPercent_3(request.getProgressBarPercent3().toString());
        }
        if (newRecord) {
            // record.setCreateDate(new Date());
            record.store();
        } else {
            record.update();
        }
        if (request.getReformDetails().size() > 0) {
            reformDAO.deleteReformDetails(record.getId());
            for (ReformDetailDTO d : request.getReformDetails()) {
                ReformDetailRecord detailRecord = (ReformDetailRecord) this.dslContext.newRecord(Tables.REFORM_DETAIL);
                detailRecord.setReformId(record.getId());
                detailRecord.setName(d.getName());
                detailRecord.setValue(d.getValue());
                detailRecord.store();
            }
        }

        return null;
    }


    public ReformDTO saveSession(AddSessionRequest request) {
        boolean newRecord = false;
        SessionRecord record = null;
        if (request.getId() != null) {
            record = reformDAO.getSessionObjectById(request.getId());
        }

        if (record == null) {
            record = (SessionRecord) this.dslContext.newRecord(Tables.SESSION);
            newRecord = true;
        }

        record.setName(request.getName());
        record.setReformId(request.getReformId());
        record.setWorkPercent(request.getWorkPercent());
        record.setName(request.getName());
        record.setStartDate(request.getStartDate());
        record.setEndDate(request.getEndDate());

        if (newRecord) {
            //record.setCreateDate(new Date());
            record.store();
        } else {
            record.update();
        }
        return null;
    }

    public SessionPollDTO saveSessionPoll(AddSessionPollRequest request) {

        SessionPollRecord record = dslContext.newRecord(Tables.SESSION_POLL);


        record.setName(request.getName());
        record.setSessionId(request.getSessionId());

        record.store();
        if (request.getAnswers().size() > 0) {

            for (PollAnswerDTO d : request.getAnswers()) {
                PollAnswerRecord pollAnswerRecord = dslContext.newRecord(Tables.POLL_ANSWER);
                pollAnswerRecord.setPollId(record.getId());
                pollAnswerRecord.setValue(d.getValue());
                pollAnswerRecord.store();
            }
        }
        return null;
    }

    public ReformDTO getReformById(int itemId) {
        ReformDTO selected = ReformDTO.translate(reformDAO.getReformById(itemId));

        return selected;
    }

    public List<ReformFileDTO> getReformFiles(int reformId) {
        return ReformFileDTO.translateArray(reformDAO.getReformFiles(reformId));
    }

    public List<SessionDTO> getReformSessions(int reformId) {
        return SessionDTO.translateArray(reformDAO.getReformSessions(reformId));
    }

    public List<SessionPollDTO> getSessionPolls(int sessionId) {
        List<SessionPollDTO> sessionPolls = SessionPollDTO.translateArray(reformDAO.getSessionPolls(sessionId));
        for (SessionPollDTO item : sessionPolls) {
            item.setAnswers(PollAnswerDTO.translateArray(reformDAO.getPollAnswers(item.getId())));
        }
        return sessionPolls;
    }

    public List<ReformTypeDTO> getReformTypes() {
        return ReformTypeDTO.translateArray(reformDAO.getReformTypes());
    }

    public HashMap<String, Object> getReforms(int start, int limit) {
        new HashMap();
        HashMap<String, Object> resultMap = new HashMap();
        HashMap<String, Object> map = this.reformDAO.getReforms(start, limit);
        List<ReformDTO> items = ReformDTO.translateArray((List) map.get("list"));
        for (ReformDTO item : items) {
            item.setReformDetails(ReformDetailDTO.translateArray(reformDAO.getReformDetails(item.getId())));
        }
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
        List<SessionDTO> sessions = SessionDTO.translateArray(reformDAO.getReformSessions(itemId));
        for (SessionDTO s : sessions) {
            deleteSession(s.getId());
        }
        reformDAO.deleteReform(itemId);
    }

    public void deleteSession(int itemId) {
        List<SessionPollDTO> polls = SessionPollDTO.translateArray(reformDAO.getSessionPolls(itemId));
        for (SessionPollDTO p : polls) {
            deleteSessionPoll(p.getId());
        }
        reformDAO.deleteSession(itemId);
    }

    public void deleteSessionPoll(int itemId) {
        reformDAO.deleteSessionPollAnswers(itemId);
        reformDAO.deleteSessionPoll(itemId);
    }


    public void addReformImage(int itemId, int fileTypeId, String originalFileName, MultipartFile file) {
        String fileName = originalFileName;
        if (fileTypeId != FileTypes.VIDEO.id()) {
            fileName = this.fileService.saveFile(file, itemId + "_1_");
        }

        try {
            if (fileName != null && !fileName.isEmpty() || fileTypeId == FileTypes.VIDEO.id()) {
                ReformFileRecord record = (ReformFileRecord) this.dslContext.newRecord(Tables.REFORM_FILE);
                record.setReformId(itemId);
                record.setFileName(fileName);
                record.setFileTypeId(fileTypeId);
                record.store();
            }
        } catch (Exception ex) {

        }

    }

    public void deleteReformFile(int itemId) {
        ReformFileRecord record = this.reformDAO.getReformFileObjectById(itemId);
        if (record != null) {
            this.fileService.deleteFile(record.getFileName());
        }

        this.reformDAO.deleteReformFile(itemId);
    }

    public void addSessionImage(int itemId, MultipartFile file) {
        String fileName = this.fileService.saveFile(file, itemId + "_2_");
        try {
            if (fileName != null && !fileName.isEmpty()) {
                SessionRecord record = reformDAO.getSessionObjectById(itemId);
                record.setImageName(fileName);
                record.store();
            }
        } catch (Exception ex) {

        }

    }

}

enum FileTypes {
    IMAGE(1),
    VIDEO(2);
    private int id;

    FileTypes(int index) {
        this.id = index;
    }

    public int id() {
        return this.id;
    }
}