package roomies.donationtracker.models;

public class IntakeEmployee extends User {
    /**
     * Method to add item
     * @param item the item
     */
    public void intakeItem(Object item) {}

    /**
     * Notifies the manager with a message
     * @param message the message to send to the messenger
     */
    public void notifyManager(String message) {
        //notify manager of too many/few items
    }

    /**
     * Method to remove an object
     * @param item the item to remove
     * @return the remove object
     */
    public Object removeItem(Object item) { return null; }
}
