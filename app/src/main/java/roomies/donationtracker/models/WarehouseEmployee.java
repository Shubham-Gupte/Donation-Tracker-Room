package roomies.donationtracker.models;

import java.util.List;

public class WarehouseEmployee extends roomies.donationtracker.models.User {
    public List<Object> intakeItems(List<Object> items) { return null; }
    public void notifyManager(String message) {
        //notify manager of too many/few items
    }
    public List<Object> shipItems(List<Object> items) { return null; }
}
