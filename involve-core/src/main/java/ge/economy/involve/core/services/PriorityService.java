package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.PriorityDTO;
import ge.economy.involve.core.api.dto.PriorityVoteDTO;
import ge.economy.involve.core.api.dto.PriorityVotePojo;
import ge.economy.involve.core.api.request.AddPriorityRequest;
import ge.economy.involve.core.dao.PriorityDAO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.PriorityRecord;
import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class PriorityService {
    @Autowired
    private PriorityDAO priorityDAO;
    @Autowired
    private DSLContext dslContext;

    private Logger logger = Logger.getLogger(PriorityService.class);

    public PriorityDTO savePriority(AddPriorityRequest request) {
        PriorityRecord record = null;
        if (request.getId() != null) {
            record = priorityDAO.getPriorityObjectById(request.getId());
        }
        if (record == null) {
            record = (PriorityRecord) dslContext.newRecord(Tables.PRIORITY);
        }
        record.setDescription(request.getDescription());
        record.setName(request.getName());
        record.setUserId(request.getUserId());
        if (record.getId() == null) {
            record.setCreateDate(new Date());
            record.store();
        } else {
            record.update();
        }
        return null;
    }

    public void getPriorityVoteResult(List<PriorityDTO> items) {
        for (PriorityDTO item : items) {
            item.setPriorityVoteResult(priorityDAO.getPriorityVoteResult(item.getId()));
            List<PriorityVoteDTO> allVote = PriorityVoteDTO.translateArray(priorityDAO.getPriorityVotes(item.getId()));
            item.setAnswerCount(allVote != null ? allVote.size() : 0);
            double sumScores = 0;
            double sumVotes = 0;
            for (PriorityVotePojo pojo : item.getPriorityVoteResult()) {
                sumScores += pojo.getAnswerId() * pojo.getAnswerCount();
                sumVotes += pojo.getAnswerCount();
            }
            if (sumVotes > 0) {
                item.setAnswerId((int) Math.ceil(sumScores / sumVotes));
            }
        }
    }

    public HashMap<String, Object> getPriorities(int start, int limit) {
        new HashMap();
        HashMap<String, Object> resultMap = new HashMap();
        HashMap<String, Object> map = priorityDAO.getPriorities(start, limit);
        List<PriorityDTO> items = PriorityDTO.translateArray((List) map.get("list"));
        getPriorityVoteResult(items);
        resultMap.put("list", items);
        resultMap.put("size", map.get("size"));
        return resultMap;
    }

    public HashMap<String, Object> getPrioritiesWithResult(int start, int limit) {
        new HashMap();
        HashMap<String, Object> resultMap = new HashMap();
        HashMap<String, Object> map = priorityDAO.getPriorities(start, limit);
        List<PriorityDTO> items = PriorityDTO.translateArray((List) map.get("list"));
        getPriorityVoteResult(items);
        for (PriorityDTO item : items) {
            item.setPriorityVotes(PriorityVoteDTO.translateArray(priorityDAO.getPriorityVotes(item.getId())));
            item.setPriorityVoteResult(priorityDAO.getPriorityVoteResult(item.getId()));
        }
        resultMap.put("list", items);
        resultMap.put("size", map.get("size"));
        return resultMap;
    }

    public void deletePriority(int itemId) {
        priorityDAO.deletePriority(itemId);
    }

}
