package roomies.donationtracker.models;

import java.util.List;

public class Cashier extends User {
    /**
     * Gets the cost of an Item
     * @param item the item
     * @return cost of item
     */
    public float getItemCost(Object item) {
        return 0;
    }

    /**
     * calculates the cost of the order
     * @param order the list of items
     * @return the cost
     */
    public float calcOrderCost(List<Object> order) {
        return 0;
    }

    /**
     * Removes a list of items from the inventory
     * @param object the objects to remove
     * @return the object removed
     */
    public Object removeFromInventory(List<Object> object) {
        return null;
    }

    /**
     * sets the revenue for the store
     * @param change the new revenue
     * @return the revenue
     */
    public float changeStoreRevenue(float change) {
        return 0;
    }
}
