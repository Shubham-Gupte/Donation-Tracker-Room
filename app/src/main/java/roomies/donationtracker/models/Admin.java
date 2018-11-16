package roomies.donationtracker.models;

import java.util.List;
/**
 * class that creates an Admin
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuu
 * @version 1.0
 */
public class Admin extends User {
    private List<User> employeeList;

    /**
     * The constructor to create an admin
     * @param username username for the admin
     * @param password password
     * @param locations locations for the admin
     * @param employeeID ID for the employee
     */
    public Admin(String username, String password, List<User> locations, String employeeID) {
        super(username, password, locations, employeeID);
        this.employeeList = null;
    }

    /**
     * Adds an employee to a location
     * @param newEmployee the employee to add
     */
    public void addEmployee(User newEmployee) {
        //add employee passed in to database
    }

    /**
     * Removes an employee
     * @param employee the employee to remove
     * @return null
     */
    public User removeEmployee(User employee) {
        //remove employee
        return null;
    }

    /**
     * Adds the location for an admin
     * @param newLocation the location to add
     */
    public void addLocation(Location newLocation) {
        //add location to firebase
    };

    /**
     * THe location to remove
     * @param location the location
     * @return location
     */
    public Location removeLocation(Location location) {
        //remove location from firebase
        return null;}

    /**
     * MEthod to lock an account
     * @param lockedUser user to account
     */
    public void lockAccount(User lockedUser) {
        //disable user account
    };

    /**
     * Method to unlock an User
     * @param unlockedUser the user to unlock
     */
    public void unlockAccount(User unlockedUser) {
        //enable previously disabled acct
    };


}
