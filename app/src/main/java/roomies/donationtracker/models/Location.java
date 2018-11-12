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

    public Location(String locationName, String locationType, double longitude, double latitude,
                    String street, String city, String state, String zip, String phoneNumber,
                    String locationID) {
        this.locationName = locationName;
        this.locationType = locationType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = street + ", " + city + ", " + state + ", " + zip;
        this.phoneNumber = phoneNumber;
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setEmployees(List<User> employees) {this.employees = employees; }

    public List<User> getEmployees() { return employees; }

    public void setItemList(List<Object> itemList) {this.itemList = itemList; }

    public List<Object> getItemList() { return itemList; }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        return locationName + ": " + address;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }
}
