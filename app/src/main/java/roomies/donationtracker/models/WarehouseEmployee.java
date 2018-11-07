package roomies.donationtracker.models;

import java.util.List;

public class WarehouseEmployee extends roomies.donationtracker.models.User {
    /**
     * This is a method to take in a list of  employees
     * @param items the items to take in
     * @return null
     */
    public List<Object> intakeItems(List<Object> items) { return null; }

    /**
     * This method sends a notifcation to the manager
     * @param message The message
     */
    public void notifyManager(String message) {
        //notify manager of too many/few items
    }

    /**
     * Method to ship Items
     * @param items The Items to ship
     * @return null
     */
    public List<Object> shipItems(List<Object> items) { return null; }
}
