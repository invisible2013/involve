package ge.economy.involve.core.services;

import ge.economy.involve.core.api.request.AddSportTypeRequest;
import ge.economy.involve.core.dao.ParameterDAO;
import ge.economy.involve.core.api.dto.UserTypeDTO;
import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParameterService
{
    @Autowired
    private ParameterDAO parameterDAO;
    @Autowired
    private DSLContext dslContext;
    @Autowired
    private FileService fileService;
    private Logger logger = Logger.getLogger(ParameterService.class);


    public UserTypeDTO saveSportType(AddSportTypeRequest request)
    {
        boolean newRecord = false;
       /* SportTypeRecord record = null;
        if (request.getId() != 0) {
            record = this.parameterDAO.getSportTypeObjectById(request.getId());
        }
        if (record == null)
        {
            record = (SportTypeRecord)this.dslContext.newRecord(Tables.SPORT_TYPE);
            newRecord = true;
        }
        record.setName(request.getName());
        record.setDescription(request.getDescription());
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }*/
        return null;
    }

    public void addSportTypeFile(int itemId, int fileTypeId, MultipartFile file)
    {
        try
        {
            String fileName = this.fileService.saveFile(file, itemId + "_2");
            if ((fileName != null) && (!fileName.isEmpty()))
            {
              /*  SportTypeFileRecord record = (SportTypeFileRecord)this.dslContext.newRecord(Tables.SPORT_TYPE_FILE);
                record.setSportTypeId(Integer.valueOf(itemId));
                record.setFileTypeId(Integer.valueOf(fileTypeId));
                record.setFileName(fileName);
                record.store();*/
            }
        }
        catch (Exception ex)
        {
            this.logger.error(ex);
        }
    }


    public void deleteSportTypeFile(int itemId)
    {
        /*SportTypeFileRecord record = this.parameterDAO.getSportTypeFileObjectById(itemId);
        if (record != null) {
            this.fileService.deleteFile(record.getFileName());
        }
        this.parameterDAO.deleteSportTypeFile(itemId);*/
    }
}
