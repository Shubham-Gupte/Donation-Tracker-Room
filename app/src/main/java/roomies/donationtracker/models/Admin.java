package roomies.donationtracker.models;

import java.util.List;

public class Admin extends User {
    private List<User> employeeList;

    public Admin(String username, String password, List<User> locations, String employeeID) {
        super(username, password, locations, employeeID);
        this.employeeList = null;
    }

    public void addEmployee(User newEmployee) {
        //add employee passed in to database
    }
    public User removeEmployee(User employee) {
        //remove employee
        return null;
    }
    public void addLocation(Location newLocation) {
        //add location to firebase
    };
    public Location removeLocation(Location location) {
        //remove location from firebase
        return null;}
    public void lockAccount(User lockedUser) {
        //disable user account
    };
    public void unlockAccount(User unlockedUser) {
        //enable previously disabled acct
    };


}
