package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.GenderDTO;
import ge.economy.involve.core.api.dto.UserDTO;
import ge.economy.involve.core.api.dto.UserTypeDTO;
import ge.economy.involve.core.api.request.AddUserRequest;
import ge.economy.involve.core.dao.UserDAO;
import ge.economy.involve.database.database.Tables;
import ge.economy.involve.database.database.tables.records.UsersRecord;
import ge.economy.involve.utils.MD5Provider;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nino on 7/10/16.
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private DSLContext dslContext;


    public UserDTO saveUser(AddUserRequest request) {
        boolean newRecord = false;
        UsersRecord record = null;
        if (request.getId() != null) {
            record = userDAO.getUserObjectById(request.getId());
        }

        if (record == null) {
            record = dslContext.newRecord(Tables.USERS);
            newRecord = true;
        }

        record.setUserTypeId(request.getTypeId());
        if (request.getTypeId() == UserDTO.USER_PHISICAL) {
            record.setFirstName(request.getFirstName());
            record.setLastName(request.getLastName());
            record.setGenderId(request.getGenderId());
        } else {
            record.setOrgName(request.getOrgName());
        }
        record.setIdNumber(request.getIdNumber());
        record.setPhone(request.getPhone());
        record.setEmail(request.getEmail());
        record.setUserTypeId(request.getTypeId());
        record.setUserGroupId(request.getGroupId());
        record.setStatusId(request.getStatusId());
        if (request.getPassword() != null) {
            record.setPassword(MD5Provider.doubleMd5(request.getPassword()));
        }
        if (request.getGroupId() == UserDTO.USER_GROUP_ADMIN) {
            record.setIsApproved(true);
        }


        if (newRecord) {
            record.store();
        } else {
            record.update();
        }
        return null;
    }

    public List<UserDTO> getUsers() {
        return UserDTO.translateArray(userDAO.getUsers());
    }


    public UserDTO getUser(String username, String password) {

        Record record = userDAO.getUser(username, MD5Provider.doubleMd5(password));

        if (record == null) {
            return null;
        }
        return UserDTO.translate(record);
    }

    public List<UserTypeDTO> getUserTypes() {
        return UserTypeDTO.translateArray(userDAO.getUserTypes());
    }

    public List<UserTypeDTO> getUserGroups() {
        return UserTypeDTO.translateArray(userDAO.getUserGroups());
    }

    public List<UserTypeDTO> getUserStatuses() {
        return UserTypeDTO.translateArray(userDAO.getUserStatus());
    }

    public List<GenderDTO> getGenders() {
        return GenderDTO.translateArray(userDAO.getGenders());
    }

    public void deleteUser(int itemId) {
        userDAO.deleteUser(itemId);
    }
}
