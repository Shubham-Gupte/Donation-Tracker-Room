package roomies.donationtracker;

public abstract class User {
    private String username;
    private String password;
    private Boolean acctState;
    private Location location;
    private String employeeID;

    public User(String username, String password, Location location, String employeeID) {
        this.username = username;
        this.password = password;
        this.location = location;
        this.employeeID = employeeID;
        acctState = true;
    }
}
