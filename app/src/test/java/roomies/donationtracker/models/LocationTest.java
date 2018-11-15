package roomies.donationtracker.models;

import org.junit.Assert;
import org.junit.Test;


public class LocationTest {

    Location LocationClass = new Location("locationName", "locationType", 1.9,  1.2,
            "street", "city", "state", "zip", "phoneNumber",
            "locationID");
    Location LocationClassNull = new Location("null", "null", 1.9,  1.2,
            null, null, null, null, null,
            "locationID");
    @Test
    public void getAddress() {
        Assert.assertEquals("street, city, state, zip", LocationClass.getAddress());
        Assert.assertEquals("No Address", LocationClassNull.getAddress());

    }

    Location validPhone = new Location("locationName", "locationType", 1.9,  1.2,
            "street", "city", "state", "zip", "(678)457-6830",
            "locationID");
    Location inValidPhone = new Location("locationName", "locationType", 1.9,  1.2,
            "street", "city", "state", "zip", "(67a)457-6830",
            "locationID");

    @Test
    public void getPhoneNumber() {
        Assert.assertEquals("(678)457-6830", validPhone.getPhoneNumber());
        Assert.assertEquals("(67a)457-6830 -- may be Invalid", inValidPhone.getPhoneNumber());
        Assert.assertEquals("No Phone Number", LocationClassNull.getPhoneNumber());
    }

    @Test
    public void getLocationType() {
        Location validPhone = new Location("locationName", "validLocationType", 1.9,  1.2,
                "street", "city", "state", "zip", "(678)457-6830",
                "locationID");
        Location inValidPhone = new Location("locationName", "InvalidLocationType", 1.9,  1.2,
                "street", "city", "state", "zip", "(67a)457-6830",
                "locationID");
        Assert.assertEquals("validLocationType", validPhone.getLocationType());
        Assert.assertEquals("InvalidLocationType", inValidPhone.getLocationType());
    }
}