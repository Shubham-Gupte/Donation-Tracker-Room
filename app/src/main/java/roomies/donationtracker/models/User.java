package roomies.donationtracker.models;

import java.util.List;

public abstract class User {
    private String username;
    private String password;
    private Boolean acctState;
    private List<User> locations; //locations they can edit/look at
    private String employeeID;
    private List<User> myEmployees;

    /**
     * Method to create a new User
     * @param username the user's username
     * @param password the user's password
     * @param locations the locations where the user will be
     * @param employeeID the ID of the employee
     */
    public User(String username, String password, List<User> locations, String employeeID) {
        this.username = username;
        this.password = password;
        this.locations = locations;
        this.employeeID = employeeID;
        acctState = true;
    }

    public User(){};

}
