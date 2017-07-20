package ge.economy.involve.database.seraliazedobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nino on 3/3/17.
 */
public class EventSubscriptionObject {

    private List<EventSubscriptionSportTypeItem> sportTypes = new ArrayList<>();


    public boolean contains(Integer typeId) {
        for (EventSubscriptionSportTypeItem eventSubscriptionSportTypeItem : sportTypes) {
            if (eventSubscriptionSportTypeItem.getSportTypeId().equals(typeId)) {
                return true;
            }
        }
        return false;
    }

    public List<EventSubscriptionSportTypeItem> getSportTypes() {
        return sportTypes;
    }

    public void setSportTypes(List<EventSubscriptionSportTypeItem> sportTypes) {
        this.sportTypes = sportTypes;
    }
}
