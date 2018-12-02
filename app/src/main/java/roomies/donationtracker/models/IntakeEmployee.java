package roomies.donationtracker.models;

/**
 * class that creates an Intake Item
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuu
 * @version 1.0
 */
public class IntakeEmployee extends User {
    /**
     * Method to intake an Object
     * @param item the item to intake item
     */
    public void intakeItem(Object item) {}

    /**
     * Method to notify a manager
     * @param message message to send to the manager
     */
    public void notifyManager(String message) {
        //notify manager of too many/few items
    }

    /**
     * The method to remove the item from a location
     * @param item the item to remove
     * @return the removed item
     */
    public Object removeItem(Object item) { return null; }
}
