package ge.economy.involve.core.services;

import ge.economy.involve.core.api.request.AddSportTypeRequest;
import ge.economy.involve.core.dao.ParameterDAO;
import ge.economy.involve.core.api.dto.AwardDTO;
import ge.economy.involve.core.api.dto.ChampionshipDTO;
import ge.economy.involve.core.api.dto.ChampionshipTypeDTO;
import ge.economy.involve.core.api.dto.ChampionshipTypeRelationDTO;
import ge.economy.involve.core.api.dto.CityDTO;
import ge.economy.involve.core.api.dto.EventTypeDTO;
import ge.economy.involve.core.api.dto.GenderDTO;
import ge.economy.involve.core.api.dto.RangeTypeDTO;
import ge.economy.involve.core.api.dto.RankDTO;
import ge.economy.involve.core.api.dto.RefereeCategoryDTO;
import ge.economy.involve.core.api.dto.RegionDTO;
import ge.economy.involve.core.api.dto.SportTypeDTO;
import ge.economy.involve.core.api.dto.SportTypeFileDTO;
import ge.economy.involve.core.api.dto.StatisticCategoryDTO;
import ge.economy.involve.core.api.dto.TrainerQualificationDTO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.SportTypeFileRecord;
import ge.economy.involve.database.database.tables.records.SportTypeRecord;
import java.util.List;
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

    public List<SportTypeDTO> getSportTypes()
    {
        return SportTypeDTO.translateArray(this.parameterDAO.getSportTypes());
    }

    public List<SportTypeDTO> getSportTypesWithDescription()
    {
        return SportTypeDTO.translateArrayWithDescription(this.parameterDAO.getSportTypes());
    }

    public List<RegionDTO> getRegions()
    {
        return RegionDTO.translateArray(this.parameterDAO.getRegions());
    }

    public List<GenderDTO> getGenders()
    {
        return GenderDTO.translateArray(this.parameterDAO.getGenders());
    }

    public List<GenderDTO> getCitiesByRegion(int regionId)
    {
        return GenderDTO.translateArray(this.parameterDAO.getCitiesByRegion(regionId));
    }

    public List<CityDTO> getCities()
    {
        return CityDTO.translateArray(this.parameterDAO.getCities());
    }

    public List<RankDTO> getRank()
    {
        return RankDTO.translateArray(this.parameterDAO.getRanks());
    }

    public List<RangeTypeDTO> getRanges()
    {
        return RangeTypeDTO.translateArray(this.parameterDAO.getRanges());
    }

    public List<StatisticCategoryDTO> getStatisticCategories(int typeId)
    {
        return StatisticCategoryDTO.translateArray(this.parameterDAO.getStatisticCategories(typeId));
    }

    public List<RefereeCategoryDTO> getRefereeCategories()
    {
        return RefereeCategoryDTO.translateArray(this.parameterDAO.getRefereeCategories());
    }

    public List<TrainerQualificationDTO> getTrainerQualifications()
    {
        return TrainerQualificationDTO.translateArray(this.parameterDAO.getTrainerQualifications());
    }

    public List<AwardDTO> getAwards()
    {
        return AwardDTO.translateArray(this.parameterDAO.getAwards());
    }

    public List<ChampionshipDTO> getChampionships()
    {
        return ChampionshipDTO.translateArray(this.parameterDAO.getChampionships());
    }

    public List<EventTypeDTO> getEventTypes()
    {
        return EventTypeDTO.translateArray(this.parameterDAO.getEventTypes());
    }

    public List<ChampionshipTypeDTO> getChampionshipTypes()
    {
        return ChampionshipTypeDTO.translateArray(this.parameterDAO.getChampionshipTypes());
    }

    public List<ChampionshipTypeRelationDTO> getChampionshipSubTypes(int typeId)
    {
        return ChampionshipTypeRelationDTO.translateArray(this.parameterDAO.getChampionshipSubTypesRelation(typeId));
    }

    public SportTypeDTO saveSportType(AddSportTypeRequest request)
    {
        boolean newRecord = false;
        SportTypeRecord record = null;
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
        }
        return null;
    }

    public void addSportTypeFile(int itemId, int fileTypeId, MultipartFile file)
    {
        try
        {
            String fileName = this.fileService.saveFile(file, itemId + "_2");
            if ((fileName != null) && (!fileName.isEmpty()))
            {
                SportTypeFileRecord record = (SportTypeFileRecord)this.dslContext.newRecord(Tables.SPORT_TYPE_FILE);
                record.setSportTypeId(Integer.valueOf(itemId));
                record.setFileTypeId(Integer.valueOf(fileTypeId));
                record.setFileName(fileName);
                record.store();
            }
        }
        catch (Exception ex)
        {
            this.logger.error(ex);
        }
    }

    public SportTypeDTO getSportTypeById(int itemId)
    {
        SportTypeDTO sportTypeDTO = SportTypeDTO.translateWithDescription(this.parameterDAO.getSportTypeObjectById(itemId));
        sportTypeDTO.setFiles(SportTypeFileDTO.translateArray(this.parameterDAO.getSportTypeFiles(itemId)));
        return sportTypeDTO;
    }

    public List<SportTypeFileDTO> getSportTypeFiles(int itemId)
    {
        return SportTypeFileDTO.translateArray(this.parameterDAO.getSportTypeFiles(itemId));
    }

    public void deleteSportTypeFile(int itemId)
    {
        SportTypeFileRecord record = this.parameterDAO.getSportTypeFileObjectById(itemId);
        if (record != null) {
            this.fileService.deleteFile(record.getFileName());
        }
        this.parameterDAO.deleteSportTypeFile(itemId);
    }
}
