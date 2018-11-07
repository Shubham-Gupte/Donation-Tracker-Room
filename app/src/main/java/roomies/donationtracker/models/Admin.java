package roomies.donationtracker.models;

import java.util.List;

public class Admin extends User {
    private List<User> employeeList;

    /**
     * Creates an admin user
     * @param username admin username
     * @param password admin password
     * @param locations admin locations
     * @param employeeID the ID of the admin
     */
    public Admin(String username, String password, List<User> locations, String employeeID) {
        super(username, password, locations, employeeID);
        this.employeeList = null;
    }

    /**
     * Method to add an employee
     * @param newEmployee the employee to add
     */
    public void addEmployee(User newEmployee) {
        //add employee passed in to database
    }

    /**
     * Method to remove an employee
     * @param employee the user to remove
     * @return the removed user
     */
    public User removeEmployee(User employee) {
        //remove employee
        return null;
    }

    /**
     * Method to add a location
     * @param newLocation the location to add
     */
    public void addLocation(Location newLocation) {
        //add location to firebase
    };

    /**
     * Method to remove a location
     * @param location the location to remove
     * @return the removed location
     */
    public Location removeLocation(Location location) {
        //remove location from firebase
        return null;}

    /**
     * Method to lock a user
     * @param lockedUser the user to lock
     */
    public void lockAccount(User lockedUser) {
        //disable user account
    };

    /**
     * Method to unlock a user
     * @param unlockedUser the user to unlock
     */
    public void unlockAccount(User unlockedUser) {
        //enable previously disabled acct
    };


}
