package roomies.donationtracker.models;

import java.util.List;

public class Location {
    private String locationName;
    private String locationType; // should change this to an enum later
    private double longitude;
    private double latitude;
    private String address;
    private String phoneNumber;
    private String locationID;
    private float revenue;
    // employees who work here
    private List<User> employees;
    private List<Object> itemList;

    /**
     * This method creates a new Location
     * @param locationName the name of the location
     * @param locationType the type of location
     * @param longitude the longitude of the location
     * @param latitude the latitude of the location
     * @param street the street it is on
     * @param city the city it is on
     * @param state the state it is on
     * @param zip the zipcode for the location
     * @param phoneNumber the phoneNumber of the location
     * @param locationID the ID of the location
     */
    public Location(String locationName, String locationType, double longitude, double latitude,
                    String street, String city, String state, String zip, String phoneNumber, String locationID) {
        this.locationName = locationName;
        this.locationType = locationType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = street + ", " + city + ", " + state + ", " + zip;
        this.phoneNumber = phoneNumber;
        this.locationID = locationID;
    }

    /**
     * Method to return the location name
     * @return the name of the location
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Sets the list of employees to a locatiom
     * @param employees employees to add
     */
    public void setEmployees(List<User> employees) {this.employees = employees; }

    /**
     * Returns a list of employees for a location
     * @return the list of employees
     */
    public List<User> getEmployees() { return employees; }

    /**
     * Sets a list of items for the locations
     * @param itemList the items
     */
    public void setItemList(List<Object> itemList) {this.itemList = itemList; }

    /**
     * Returns a list of items
     * @return itesm List
     */
    public List<Object> getItemList() { return itemList; }

    /**
     * Sets the location Name
     * @param locationName
     */
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    /**
     * Gets the location type
     * @return the type of location
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * Sets the location type
     * @param locationType the location type
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    /**
     * Gets the longitude of the location
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of the location
     * @param longitude the longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the latitude of the location
     * @return latitude of the location
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of the location
     * @param latitude the latitude of the location
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the address of the location
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setting the address of the location
     * @param address the address of the location
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getting the phone number of the location
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the location
     * @param phoneNumber The number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * The method to convert the location to the String
     * @return location String
     */
    public String toString() {
        return locationName + ": " + address;
    }

    /**
     * Gets the location ID
     * @return location ID
     */
    public String getLocationID() {
        return locationID;
    }

    /**
     * Sets the location ID
     * @param locationID new ID
     */
    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }
}
