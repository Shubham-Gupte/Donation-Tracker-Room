package roomies.donationtracker.models;

import java.util.List;
/**
 * class that creates a Cashier
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuu
 * @version 1.0
 */
public class Cashier extends User {
    /**
     * Method to return the cost of an item
     * @param item the item
     * @return the cost
     */
    public float getItemCost(Object item) {
        return 0;
    }

    /**
     * Calculates the cost of a list of items
     * @param order the list of items
     * @return the cost
     */
    public float calcOrderCost(List<Object> order) {
        return 0;
    }

    /**
     * Methods to remove an object from the inventory
     * @param object object to remove
     * @return the object
     */
    public Object removeFromInventory(List<Object> object) {
        return null;
    }

    /**
     * Method to changeStoreRevenue
     * @param change the desired revenue
     * @return 0
     */
    public float changeStoreRevenue(float change) {
        return 0;
    }
}
