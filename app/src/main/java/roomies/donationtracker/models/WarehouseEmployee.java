package roomies.donationtracker.models;

import java.util.List;

public class WarehouseEmployee extends roomies.donationtracker.models.User {
    /**
     * Method to intake Items
     * @param items the list of items to intake
     * @return null
     */
    public List<Object> intakeItems(List<Object> items) { return null; }

    /**
     * Method to notify Manager of items
     * @param message message to send
     */
    public void notifyManager(String message) {
        //notify manager of too many/few items
    }

    /**
     * Method to ship items
     * @param items the items to ship
     * @return null
     */
    public List<Object> shipItems(List<Object> items) { return null; }
}
