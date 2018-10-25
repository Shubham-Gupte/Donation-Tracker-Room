package roomies.donationtracker.models;

import java.util.List;

public class Manager extends User {
    private List<User> employeeList;

    public Manager(String username, String password, List<User> locations, String employeeID) {
        super(username, password, locations, employeeID);
        this.employeeList = null;
    }

    public void moveItems(List<Object> items, Location a, Location b) {
        //move given items from location A to Location B
    }
    public float viewRevenue(Location location) {
        //view revenue at given location
        return 0;
    }
}
