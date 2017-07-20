package ge.economy.involve.core.services;

import ge.economy.involve.core.api.request.AddChampionshipRequest;
import ge.economy.involve.core.api.dto.ChampionshipDTO;
import ge.economy.involve.core.dao.ChampionshipDAO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.ChampionshipRecord;
import java.util.List;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampionshipService
{
    @Autowired
    private ChampionshipDAO championshipDAO;
    @Autowired
    private DSLContext dslContext;

    public ChampionshipDTO saveResult(AddChampionshipRequest request)
    {
        boolean newRecord = false;
        ChampionshipRecord record = null;
        if (request.getId() != 0) {
            record = this.championshipDAO.getChampionshiptById(request.getId());
        }
        if (record == null)
        {
            record = (ChampionshipRecord)this.dslContext.newRecord(Tables.CHAMPIONSHIP);
            newRecord = true;
        }
        record.setName(request.getName());
        record.setDescription(request.getDescription());
        record.setLocation(request.getLocation());
        record.setStartDate(request.getStartDate());
        record.setEndDate(request.getEndDate());
        record.setChampionshipTypeId(Integer.valueOf(request.getChampionshipTypeId()));
        record.setChampionshipSubTypeId(Integer.valueOf(request.getChampionshipSubTypeId()));
        if (newRecord) {
            record.store();
        } else {
            record.update();
        }
        return null;
    }

    public ChampionshipDTO getChampionshipById(int itemId)
    {
        return ChampionshipDTO.translate(this.championshipDAO.getChampionshiptById(itemId));
    }

    public List<ChampionshipDTO> getChampionships()
    {
        return ChampionshipDTO.translateArray(this.championshipDAO.getChampionships());
    }

    public List<ChampionshipDTO> getChampionshipsAlphabet()
    {
        return ChampionshipDTO.translateArray(this.championshipDAO.getChampionshipsAlphabet());
    }

    public void deleteChampionship(int itemId)
    {
        this.championshipDAO.deleteChampionship(itemId);
    }
}
