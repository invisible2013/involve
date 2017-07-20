package ge.economy.involve.core.services;

import ge.economy.involve.core.api.dto.UserDTO;
import ge.economy.involve.core.dao.UserDAO;
import ge.economy.involve.database.database.tables.records.UserRecord;
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


    public List<UserDTO> getUsers() {
        return UserDTO.translateArray(userDAO.getUsers());
    }


    public UserDTO getUser(String username, String password) {

        UserRecord userRecord = userDAO.getUser(username, password);

        if (userRecord == null) {
            return null;
        }
        return UserDTO.translate(userRecord);
    }
}
