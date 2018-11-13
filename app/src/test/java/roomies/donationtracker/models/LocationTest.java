package roomies.donationtracker.models;

import org.junit.Assert;
import org.junit.Test;


public class LocationTest {

    Location LocationClass = new Location("locationName", "locationType", 1.9,  1.2,
            "street", "city", "state", "zip", "phoneNumber",
            "locationID");
    Location LocationClassNull = new Location("locationName", "locationType", 1.9,  1.2,
            null, null, null, null, "phoneNumber",
            "locationID");
    @Test
    public void getAddress() {
        Assert.assertEquals("street, city, state, zip", LocationClass.getAddress());
        Assert.assertEquals("No Address", LocationClassNull.getAddress());

    }
}