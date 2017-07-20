package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.RefereeDTO;
import ge.economy.involve.core.api.dto.RefereeSportTypeDTO;
import ge.economy.involve.core.api.request.AddRefereeRequest;
import ge.economy.involve.core.dao.RefereeDAO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.RefereeRecord;
import ge.economy.involve.database.database.tables.records.RefereeSportTypeRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefereeService
{
    @Autowired
    private RefereeDAO refereeDAO;
    @Autowired
    private DSLContext dslContext;

    public RefereeDTO saveReferee(AddRefereeRequest request)
    {
        boolean newRecord = false;
        RefereeRecord record = null;
        if (request.getId() != 0) {
            record = this.refereeDAO.getRefereeObjectById(request.getId());
        }
        if (record == null)
        {
            record = (RefereeRecord)this.dslContext.newRecord(Tables.REFEREE);
            newRecord = true;
        }
        record.setFirstName(request.getFirstName());
        record.setLastName(request.getLastName());
        record.setCategoryId(Integer.valueOf(request.getCategoryId()));
        record.setExperience(request.getExperiense());
        record.setBirthDate(request.getBirthDate());
        record.setRegionId(request.getRegionId());
        record.setGenderId(request.getGenderId());
        record.setCityId(request.getCityId());
        record.setDeathDate(request.getDeathDate());
        record.setCareerStartDate(request.getCareerStartDate());
        record.setCareerEndDate(request.getCareerEndDate());
        record.setComment(request.getComment());
        record.setBiography(request.getBiography());
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }
        if (request.getSportTypes() != null) {
            this.refereeDAO.deleteRefereSportTypesById(record.getId().intValue());
        }
        for (RefereeSportTypeDTO item : request.getSportTypes()) {
            try
            {
                if (item.getSportTypeId() != 0)
                {
                    RefereeSportTypeRecord refereeSportTypeRecord = (RefereeSportTypeRecord)this.dslContext.newRecord(Tables.REFEREE_SPORT_TYPE);
                    refereeSportTypeRecord.setRefereeId(record.getId());
                    refereeSportTypeRecord.setSportTypeId(Integer.valueOf(item.getSportTypeId()));
                    refereeSportTypeRecord.store();
                }
            }
            catch (Exception localException) {}
        }
        return null;
    }

    public HashMap<String, Object> searchReferees(String name,String firstLetter, int sportTypeId, int categoryId, int genderId, int regionId, int cityId, int start, int limit)
    {
        HashMap<String, Object> map = this.refereeDAO.searchReferee(name,firstLetter, sportTypeId, categoryId, genderId, regionId, cityId, start, limit);
        HashMap<String, Object> resultMap = new HashMap();
        List<RefereeDTO> referees = RefereeDTO.translateArray((List)map.get("list"));
        resultMap.put("list", referees);
        resultMap.put("size", map.get("size"));
        return resultMap;
    }

    public RefereeDTO getRefereeById(int refereeId)
    {
        RefereeDTO refereeDTO = RefereeDTO.translate(this.refereeDAO.getRefereeById(refereeId));
        refereeDTO.setSportTypes(getRefereeSportTypes(refereeId));
        return refereeDTO;
    }

    public void deleteRefereeById(int refereeId)
    {
        this.refereeDAO.deleteRefereById(refereeId);
    }

    public List<RefereeDTO> getReferees()
    {
        return RefereeDTO.translateArray(this.refereeDAO.getReferees());
    }

    public List<RefereeSportTypeDTO> getRefereeSportTypes(int refereeId)
    {
        List<Record> sportTypes = this.refereeDAO.getRefereeSportTypes(refereeId);
        List<RefereeSportTypeDTO> sportTypesDTO = RefereeSportTypeDTO.translateArray(sportTypes);
        return sportTypesDTO;
    }

    public List<RefereeDTO> getRefereeWithDependencyInfo(int refereeId)
    {
        List<Record> rs = this.refereeDAO.getRefereesWithDependencyInfo(Integer.valueOf(refereeId));
        ArrayList<RefereeDTO> dtos = new ArrayList();
        for (int i = 0; i < rs.size(); i++)
        {
            RefereeDTO rd = RefereeDTO.translate((Record)rs.get(i));

            RefereeSportTypeDTO sp = new RefereeSportTypeDTO();
            sp.setSportTypeId(((Integer)((Record)rs.get(i)).getValue(Tables.REFEREE_SPORT_TYPE.SPORT_TYPE_ID)).intValue());
            sp.setRefereeId(((Integer)((Record)rs.get(i)).getValue(Tables.REFEREE_SPORT_TYPE.REFEREE_ID)).intValue());
            if ((i > 0) && (((Record)rs.get(i)).getValue(Tables.REFEREE.ID) == ((Record)rs.get(i - 1)).getValue(Tables.REFEREE.ID)))
            {
                ((RefereeDTO)dtos.get(dtos.size() - 1)).getSportTypes().add(sp);
            }
            else
            {
                rd.getSportTypes().add(sp);
                dtos.add(rd);
            }
        }
        return dtos;
    }
}
