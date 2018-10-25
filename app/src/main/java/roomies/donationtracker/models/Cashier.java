package roomies.donationtracker.models;

import java.util.List;

public class Cashier extends User {
    public float getItemCost(Object item) {
        return 0;
    }
    public float calcOrderCost(List<Object> order) {
        return 0;
    }
    public Object removeFromInventory(List<Object> object) {
        return null;
    }
    public float changeStoreRevenue(float change) {
        return 0;
    }
}
