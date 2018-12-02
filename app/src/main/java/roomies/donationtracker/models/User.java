package roomies.donationtracker.models;

import java.util.List;
/**
 * class that creates a User
 *
 * @author Polly Ouellette, Arman Varzi, Shubham Gupte, Will Hay, Carl Roosipuu
 * @version 1.0
 */
public abstract class User {
    private String username;
    private String password;
    private Boolean acctState;
    private List<User> locations; //locations they can edit/look at
    private String employeeID;
    private List<User> myEmployees;

    /**
     * The method creates a new user
     * @param username the username for the new user
     * @param password the password for the new user
     * @param locations the locations the user should be for
     * @param employeeID
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
