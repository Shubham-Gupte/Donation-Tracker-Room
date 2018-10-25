package roomies.donationtracker.models;

import roomies.donationtracker.User;

public class IntakeEmployee extends User {
    public void intakeItem(Object item) {}
    public void notifyManager(String message) {
        //notify manager of too many/few items
    }
    public Object removeItem(Object item) { return null; }
}
