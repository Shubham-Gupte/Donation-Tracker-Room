package roomies.donationtracker.models;

public class IntakeEmployee extends User {
    public void intakeItem(Object item) {}
    public void notifyManager(String message) {
        //notify manager of too many/few items
    }
    public Object removeItem(Object item) { return null; }
}
