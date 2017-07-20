package ge.economy.involve.core.api.request.eventsubscription;

import java.util.List;

/**
 * Created by mindia on 3/3/17.
 */
public class SubscribeEventRequest {

    private String email;
    private List<Integer> sportTypes;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getSportTypes() {
        return sportTypes;
    }

    public void setSportTypes(List<Integer> sportTypes) {
        this.sportTypes = sportTypes;
    }
}
