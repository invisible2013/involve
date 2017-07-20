package ge.economy.involve.database.lookups;

/**
 * Created by nino on 7/10/16.
 */
public enum UserStatusList {

    ACTIVE(1, "active", "Active"),
    DISABLED(2, "disabled", "Disabled");


    int statusId;
    String name;
    String humanName;

    UserStatusList(int statusId, String name, String humanName) {
        this.statusId = statusId;
        this.name = name;
        this.humanName = humanName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
