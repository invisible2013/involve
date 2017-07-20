package ge.economy.involve.core.services;

import ge.economy.involve.core.api.request.AddOrganisationRequest;
import ge.economy.involve.core.dao.OrganisationDAO;
import ge.economy.involve.core.api.dto.OrganisationDTO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.OrganisationRecord;

import java.util.HashMap;
import java.util.List;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OrganisationService {
    @Autowired
    private OrganisationDAO organisationDAO;
    @Autowired
    private DSLContext dslContext;
    @Autowired
    private FileService fileService;

    public OrganisationDTO saveOrganisation(AddOrganisationRequest request) {
        boolean newRecord = false;
        OrganisationRecord record = null;
        if (request.getId() != 0) {
            record = this.organisationDAO.getOrganisationObjectById(request.getId());
        }
        if (record == null) {
            record = (OrganisationRecord) this.dslContext.newRecord(Tables.ORGANISATION);
            newRecord = true;
        }
        record.setName(request.getName());
        record.setAddress(request.getAddress());
        record.setWeb(request.getWeb());
        record.setMobilePhone(request.getMobilePhone());
        record.setMail(request.getEmail());
        record.setRegionId(request.getRegionId());
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }
        return OrganisationDTO.translate(record);
    }

    public List<OrganisationDTO> getOrganisations() {
        return OrganisationDTO.translateArray(this.organisationDAO.getOrganisations());
    }

    public HashMap<String, Object> getOrganisations(int start, int limit) {
        HashMap<String, Object> map = this.organisationDAO.getOrganisations(start, limit);
        List<OrganisationDTO> list = OrganisationDTO.translateArray((List) map.get("list"));
        map.put("list", list);
        return map;
    }

    public void deleteOrganisation(int itemId) {
        this.organisationDAO.deleteOrganisation(itemId);
    }

    public void addOrganisationImage(int itemId, MultipartFile file) {
        String fileName = fileService.saveFile(file, itemId + "_3");
        try {
            if ((fileName != null) && (!fileName.isEmpty())) {
                deleteOrganisationFile(itemId);
                OrganisationRecord record = this.organisationDAO.getOrganisationObjectById(itemId);
                record.setLogoUrl(fileName);
                record.update();
            }
        } catch (Exception localException) {
        }
    }

    public void deleteOrganisationFile(int itemId) {
        OrganisationRecord record = this.organisationDAO.getOrganisationObjectById(itemId);
        if (record != null && record.getLogoUrl() != null && record.getLogoUrl().length() > 0) {
            this.fileService.deleteFile(record.getLogoUrl());
            record.setLogoUrl("");
            record.update();
        }
    }
}
