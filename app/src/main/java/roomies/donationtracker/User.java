package roomies.donationtracker;

import java.util.List;

public abstract class User {
    private String username;
    private String password;
    private Boolean acctState;
    private List<User> locations; //locations they can edit/look at
    private String employeeID;
    private List<User> myEmployees;

    public User(String username, String password, List<User> locations, String employeeID) {
        this.username = username;
        this.password = password;
        this.locations = locations;
        this.employeeID = employeeID;
        acctState = true;
    }

    public User(){};

}
