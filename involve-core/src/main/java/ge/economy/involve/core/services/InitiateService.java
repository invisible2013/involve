package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.*;
import ge.economy.involve.core.api.request.AddInitiateRequest;
import ge.economy.involve.core.api.request.AddSportTypeRequest;
import ge.economy.involve.core.dao.InitiateDAO;
import ge.economy.involve.core.dao.ParameterDAO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.InitiatedIssue;
import ge.economy.involve.database.database.tables.records.InitiateRecord;
import ge.economy.involve.database.database.tables.records.InitiatedIssueRecord;
import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@Service
public class InitiateService {
    @Autowired
    private InitiateDAO initiateDAO;
    @Autowired
    private DSLContext dslContext;

    private Logger logger = Logger.getLogger(InitiateService.class);

    public InitiateDTO saveInitiate(AddInitiateRequest request) {
        boolean newRecord = false;
        InitiateRecord record = null;
        if (request.getId() != null) {
            record = initiateDAO.getInitiateObjectById(request.getId());
        }

        if (record == null) {
            record = (InitiateRecord) dslContext.newRecord(Tables.INITIATE);
            newRecord = true;
        }

        record.setDescription(request.getDescription());
        record.setUserId(request.getUserId());
        record.setGroupId(request.getGroupId());
        record.setSphereId(request.getSphereId());

        if (newRecord) {
            // record.setCreateDate(new Date());
            record.store();
        } else {
            record.update();
        }

        return null;
    }

    public HashMap<String, Object> getInitiate(int start, int limit) {
        new HashMap();
        HashMap<String, Object> resultMap = new HashMap();
        HashMap<String, Object> map = initiateDAO.getInitiate(start, limit);
        List<InitiateDTO> items = InitiateDTO.translateArray((List) map.get("list"));
        resultMap.put("list", items);
        resultMap.put("size", map.get("size"));
        return resultMap;
    }

    public void deleteInitiate(int itemId) {
        initiateDAO.deleteInitiate(itemId);
    }


    public InitiateDTO saveIssue(AddInitiateRequest request) {
        boolean newRecord = false;
        InitiatedIssueRecord record = null;
        if (request.getId() != null) {
            record = initiateDAO.getInitiatedIssueObjectById(request.getId());
        }

        if (record == null) {
            record = dslContext.newRecord(Tables.INITIATED_ISSUE);
            newRecord = true;
        }

        record.setDescription(request.getDescription());
        record.setCreatorId(request.getUserId());

        if (newRecord) {
            // record.setCreateDate(new Date());
            record.store();
        } else {
            record.update();
        }

        return null;
    }


    public HashMap<String, Object> getIssues(int start, int limit) {
        new HashMap();
        HashMap<String, Object> resultMap = new HashMap();
        HashMap<String, Object> map = initiateDAO.getIssue(start, limit);
        List<InitiatedIssueDTO> items = InitiatedIssueDTO.translateArray((List) map.get("list"));
        resultMap.put("list", items);
        resultMap.put("size", map.get("size"));
        return resultMap;
    }

    public void deleteIssue(int itemId) {
        initiateDAO.deleteIssue(itemId);
    }
}
