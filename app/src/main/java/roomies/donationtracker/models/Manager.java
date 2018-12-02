package roomies.donationtracker.models;

import java.util.List;
/**
 * class that creates a Manager
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuu
 * @version 1.0
 */
public class Manager extends User {
    private List<User> employeeList;

    /**
     * Method to create a Manager
     * @param username the username for the manager
     * @param password the password for the manager
     * @param locations the locations for the manager
     * @param employeeID the ID of the manager
     */
    public Manager(String username, String password, List<User> locations, String employeeID) {
        super(username, password, locations, employeeID);
        this.employeeList = null;
    }

    /**
     * Method to allow the manager to move the items from one location to another
     * @param items the items to move
     * @param a initial locations
     * @param b location to move it to
     */
    public void moveItems(List<Object> items, Location a, Location b) {
        //move given items from location A to Location B
    }

    /**
     * Method to view the Revenue of the location
     * @param location the location we want
     * @return the revenue
     */
    public float viewRevenue(Location location) {
        //view revenue at given location
        return 0;
    }
}
