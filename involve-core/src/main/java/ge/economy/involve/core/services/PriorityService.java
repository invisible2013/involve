package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.PriorityDTO;
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

    public HashMap<String, Object> getPriorities(int start, int limit) {
        new HashMap();
        HashMap<String, Object> resultMap = new HashMap();
        HashMap<String, Object> map = priorityDAO.getPriorities(start, limit);
        List<PriorityDTO> items = PriorityDTO.translateArray((List) map.get("list"));
        resultMap.put("list", items);
        resultMap.put("size", map.get("size"));
        return resultMap;
    }

    public void deletePriority(int itemId) {
        priorityDAO.deletePriority(itemId);
    }

}
