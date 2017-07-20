package ge.economy.involve.core.api.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ge.economy.involve.core.jsonhelper.JsonDateDeSerializeSupport;

import java.util.Date;

/**
 * Created by nino on 7/10/16.
 */
public class AddUserRequest {

    private String name;

    @JsonDeserialize(using = JsonDateDeSerializeSupport.class)
    private Date birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
