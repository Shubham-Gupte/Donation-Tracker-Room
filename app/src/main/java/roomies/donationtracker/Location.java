package roomies.donationtracker;

public class Location {
    private String locationName;
    private String locationType; // should change this to an enum later
    private String longitude;
    private String latitude;
    private String address;
    private String phoneNumber;

    public Location(String locationName, String locationType, String longitude, String latitude,
                    String street, String city, String state, String zip, String phoneNumber) {
        this.locationName = locationName;
        this.locationType = locationType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = street + ", " + city + ", " + state + ", " + zip;
        this.phoneNumber = phoneNumber;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
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
}
