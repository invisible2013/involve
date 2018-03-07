package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.*;
import ge.economy.involve.core.api.request.AddInitiateRequest;
import ge.economy.involve.core.api.request.AddInitiativeVoteRequest;
import ge.economy.involve.core.api.request.AddPriorityVoteRequest;
import ge.economy.involve.core.api.request.AddSportTypeRequest;
import ge.economy.involve.core.dao.InitiateDAO;
import ge.economy.involve.core.dao.ParameterDAO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.InitiatedIssue;
import ge.economy.involve.database.database.tables.records.InitiateRecord;
import ge.economy.involve.database.database.tables.records.InitiatedIssueRecord;
import ge.economy.involve.database.database.tables.records.InitiativeVoteRecord;
import ge.economy.involve.database.database.tables.records.PriorityVoteRecord;
import ge.economy.involve.utils.email.SendEmail;
import ge.economy.involve.utils.email.SendEmailWithAttachment;
import javafx.scene.shape.Sphere;
import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeBodyPart;
import javax.mail.util.ByteArrayDataSource;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class InitiateService {
    @Autowired
    private InitiateDAO initiateDAO;
    @Autowired
    private PDFService pdfService;
    @Autowired
    private UserService userService;
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

        record.setName(request.getName());
        record.setDescription(request.getDescription());
        record.setNecessity(request.getNecessity());
        record.setAdvantages(request.getAdvantages());
        record.setUserId(request.getUserId());
        record.setGroupId(request.getGroupId());
        record.setSphereId(request.getSphereId());
        record.setIpAddress(request.getIpAddress());
        record.setClientUid(request.getClientUID());
        record.setOtherSphereName(request.getOtherSphereName());
        if (newRecord) {
            record.setCreateDate(new Date());
            record.store();
        } else {
            record.update();
        }
        try {
            InitiateDTO initiateDTO = getInitiateById(record.getId());
            UserDTO user = userService.getUserById(initiateDTO.getUserId());
            if (user != null) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                pdfService.writePdf(outputStream, getInitiateById(initiateDTO.getId()));
                SendEmail mailSender = new SendEmail();
                mailSender.setTo(user.getEmail());
                mailSender.setSubject("YOU.GOV.GE საკითხის ინიცირება");
                mailSender.setBody("ძვირფასო " + initiateDTO.getUserName() + ", \n\n" +
                        "„მადლობას გიხდით საკითხის დაინიცირებისთვის!\n\n" +
                        "ეკონომიკისა და მდგრადი განვითარების სამინისტრო განიხილავს თქვენს წინადადებას“\n\n\n" +
                        "\n");
                mailSender.sendWithPdf(outputStream);
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return null;
    }

    public HashMap<String, Object> getInitiates(int start, int limit) {
        new HashMap();
        HashMap<String, Object> resultMap = new HashMap();
        HashMap<String, Object> map = initiateDAO.getInitiate(start, limit);
        List<InitiateDTO> items = InitiateDTO.translateArray((List) map.get("list"));
        resultMap.put("list", items);
        resultMap.put("size", map.get("size"));
        return resultMap;
    }

    public InitiateDTO getInitiateById(int initiateId) {
        return InitiateDTO.translate(initiateDAO.getInitiateById(initiateId));
    }


    public void test() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        pdfService.writePdf(outputStream, getInitiateById(8));
        SendEmail mailSender = new SendEmail();
        mailSender.setTo("nino.lomineisvili@gmail.com");
        mailSender.setSubject("YOU.GOV.GE საკითხის ინიცირება");
        mailSender.setBody("ძვირფასო ანა ჩხაიძე, <br/>" +
                "<br/>" +
                "<br/>" +
                "მადლობას გიხდით საკითხის დაინიცირებისთვის!<br/>" +
                "<br/>" +
                "<br/>" +
                "ეკონომიკისა და მდგრადი განვითარების სამინისტრო განიხილავს თქვენს წინადადებას <br/>" +
                "<br/>");
        mailSender.sendWithPdf(outputStream);
    }

    public void deleteInitiate(int itemId) {
        initiateDAO.deleteInitiate(itemId);
    }


    public InitiatedIssueDTO saveIssue(AddInitiateRequest request) {
        boolean newRecord = false;
        InitiatedIssueRecord record = null;
        if (request.getId() != null) {
            record = initiateDAO.getInitiatedIssueObjectById(request.getId());
        }

        if (record == null) {
            record = dslContext.newRecord(Tables.INITIATED_ISSUE);
            newRecord = true;
        }

        record.setName(request.getName());
        record.setDescription(request.getDescription());
        record.setCreatorId(request.getUserId());

        if (newRecord) {
            record.setCreateDate(new Date());
            record.store();
        } else {
            record.update();
        }

        return null;
    }

    public InitiatedIssueDTO getInitiateIssueById(int initiateIssueId) {
        InitiatedIssueDTO item = InitiatedIssueDTO.translate(initiateDAO.getInitiateIssueById(initiateIssueId));
        item.setVoteCount(initiateDAO.getInitiatedIssueVoteCount(item.getId()));
        return item;
    }

    public HashMap<String, Object> getIssues(int start, int limit) {
        new HashMap();
        HashMap<String, Object> resultMap = new HashMap();
        HashMap<String, Object> map = initiateDAO.getIssue(start, limit);
        List<InitiatedIssueDTO> items = InitiatedIssueDTO.translateArray((List) map.get("list"));
        for (InitiatedIssueDTO item : items) {
            item.setVoteCount(initiateDAO.getInitiatedIssueVoteCount(item.getId()));
        }
        resultMap.put("list", items);
        resultMap.put("size", map.get("size"));
        return resultMap;
    }

    public void deleteIssue(int itemId) {
        initiateDAO.deleteIssue(itemId);
    }

    public List<SphereDTO> getSpheres() {
        return SphereDTO.translateArray(initiateDAO.getSpheres());
    }


    public InitiativeVoteDTO saveInitiativeIssueVote(AddInitiativeVoteRequest request) throws Exception {
        if (checkUserInitiateVote(request.getInitiatedIssueId(), request.getUserId())) {
            throw new Exception("მომხმარებელს უკვე მიცემული აქვს ხმა");
        }

        boolean newRecord = false;
        InitiativeVoteRecord record = null;
        if (request.getId() != null) {
            record = initiateDAO.getInitiativeVoteObjectById(request.getId());
        }

        if (record == null) {
            record = dslContext.newRecord(Tables.INITIATIVE_VOTE);
            newRecord = true;
        }

        record.setUserId(request.getUserId());
        record.setInitiatedIssueId(request.getInitiatedIssueId());
        record.setAgreed(request.getAgreed());
        record.setClientUid(request.getClientUID());
        record.setIpAddress(request.getIpAddress());

        if (newRecord) {
            record.setCreateDate(new Date());
            record.store();
        } else {
            record.update();
        }
        InitiativeVoteDTO dto = InitiativeVoteDTO.translate(record);
        dto.setVoteCount(initiateDAO.getInitiatedIssueVoteCount(dto.getId()));
        return dto;
    }

    public HashMap<String, String> getUserInitiateVote(int initiateIssueId, int userId) {
        HashMap<String, String> result = new HashMap<>();
        result.put("result", checkUserInitiateVote(initiateIssueId, userId) ? "true" : "false");
        return result;
    }

    public boolean checkUserInitiateVote(int initiateIssueId, int userId) {
        int voteCount = initiateDAO.getUserInitiatedVote(initiateIssueId, userId);
        return voteCount > 0 ? true : false;
    }

    public InitiatedIssueDTO savePriorityVote(AddPriorityVoteRequest request) {
        PriorityVoteRecord record = null;

        if (request.getId() != null) {
            record = initiateDAO.getPriorityVoteObjectById(request.getId());
        } else if (request.getUserId() != null && request.getUserId() != 0) {
            record = initiateDAO.getPriorityVoteObjectByUserId(request.getPriorityId(), request.getUserId());
        } else if (request.getClientUID().length() > 0) {
            record = initiateDAO.getPriorityVoteObjectByClientUID(request.getPriorityId(), request.getClientUID());
        }

        if (record == null) {
            record = dslContext.newRecord(Tables.PRIORITY_VOTE);
        }

        record.setUserId(request.getUserId());
        record.setPriorityId(request.getPriorityId());
        record.setAnswerId(request.getAnswerId());
        record.setClientUid(request.getClientUID());
        record.setIpAddress(request.getIpAddress());

        if (request.getId() == null) {
            record.setCreateDate(new Date());
            record.store();
        } else {
            record.update();
        }
        return null;
    }
}
