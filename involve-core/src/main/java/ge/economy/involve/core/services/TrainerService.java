package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.TrainerDTO;
import ge.economy.involve.core.api.dto.TrainerSportTypeDTO;
import ge.economy.involve.core.api.request.AddTrainerRequest;
import ge.economy.involve.core.dao.TrainerDAO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.TrainerRecord;
import ge.economy.involve.database.database.tables.records.TrainerSportTypeRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerService {
    @Autowired
    private TrainerDAO trainerDAO;
    @Autowired
    private DSLContext dslContext;

    public TrainerDTO saveTrainer(AddTrainerRequest request) {
        boolean newRecord = false;
        TrainerRecord record = null;
        if (request.getId() != 0) {
            record = this.trainerDAO.getTrainerObjectById(request.getId());
        }
        if (record == null) {
            record = (TrainerRecord) this.dslContext.newRecord(Tables.TRAINER);
            newRecord = true;
        }
        record.setFirstName(request.getFirstName());
        record.setLastName(request.getLastName());
        record.setBirthDate(request.getBirthDate());
        record.setGenderId(request.getGenderId());
        record.setRegionId(request.getRegionId());
        record.setCityId(request.getCityId());
        record.setQualificationId(Integer.valueOf(request.getQualificationId()));
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
        if ((request.getSportTypes() != null) && (request.getSportTypes().size() > 0)) {
            this.trainerDAO.deleteTrainerSportTypesById(record.getId().intValue());
        }
        for (TrainerSportTypeDTO item : request.getSportTypes()) {
            try {
                if (item.getSportTypeId() != 0) {
                    TrainerSportTypeRecord record1 = (TrainerSportTypeRecord) this.dslContext.newRecord(Tables.TRAINER_SPORT_TYPE);
                    record1.setTrainerId(record.getId());
                    record1.setSportTypeId(Integer.valueOf(item.getSportTypeId()));
                    record1.store();
                }
            } catch (Exception localException) {
            }
        }
        return null;
    }

    public HashMap<String, Object> searchTrainers(String name, String firstLetter, int sportTypeId, int categoryId, int genderId, int regionId, int cityId, int start, int limit) {
        HashMap<String, Object> map = this.trainerDAO.searchTrainers(name, firstLetter, sportTypeId, categoryId, genderId, regionId, cityId, start, limit);
        HashMap<String, Object> resultMap = new HashMap();
        List<TrainerDTO> trainers = TrainerDTO.translateArray((List) map.get("list"));
        resultMap.put("list", trainers);
        resultMap.put("size", map.get("size"));
        return resultMap;
    }

    public TrainerDTO getTrainerById(int trainerId) {
        TrainerDTO trainerDTO = TrainerDTO.translate(this.trainerDAO.getTrainerById(trainerId));
        trainerDTO.setSportTypes(getTrainerSportTypes(trainerId));
        return trainerDTO;
    }

    public List<TrainerDTO> getTrainers() {
        return TrainerDTO.translateArray(this.trainerDAO.getTrainers());
    }

    public List<TrainerSportTypeDTO> getTrainerSportTypes(int trainerId) {
        List<Record> sportTypes = this.trainerDAO.getTrainerSportTypes(trainerId);
        List<TrainerSportTypeDTO> sportTypesDTO = TrainerSportTypeDTO.translateArray(sportTypes);
        return sportTypesDTO;
    }

    public List<TrainerDTO> getTrainerWithDependencyInfo(int trainerId) {
        List<Record> rs = this.trainerDAO.getTrainerWithDependencyInfo(Integer.valueOf(trainerId));
        ArrayList<TrainerDTO> dtos = new ArrayList();
        for (int i = 0; i < rs.size(); i++) {
            TrainerDTO rd = TrainerDTO.translate((Record) rs.get(i));

            TrainerSportTypeDTO sp = new TrainerSportTypeDTO();
            sp.setSportTypeId(((Integer) ((Record) rs.get(i)).getValue(Tables.TRAINER_SPORT_TYPE.SPORT_TYPE_ID)).intValue());
            sp.setTrainerId(((Integer) ((Record) rs.get(i)).getValue(Tables.TRAINER_SPORT_TYPE.TRAINER_ID)).intValue());
            if ((i > 0) && (((Record) rs.get(i)).getValue(Tables.TRAINER.ID) == ((Record) rs.get(i - 1)).getValue(Tables.TRAINER.ID))) {
                ((TrainerDTO) dtos.get(dtos.size() - 1)).getSportTypes().add(sp);
            } else {
                rd.getSportTypes().add(sp);
                dtos.add(rd);
            }
        }
        return dtos;
    }

    public void deleteTrainerById(int trainerId) {
        this.trainerDAO.deleteTrainerById(trainerId);
    }
}
