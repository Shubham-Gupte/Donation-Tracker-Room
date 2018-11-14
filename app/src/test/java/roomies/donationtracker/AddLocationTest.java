package roomies.donationtracker;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import roomies.donationtracker.models.Database;
import roomies.donationtracker.models.Location;

/**
 * Test that focuses on adding locations to the firebase database,
 * which will execute on the development machine (host).
 *
 * @author Polly Ouellette
 * @version 1.0
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AddLocationTest {
    Database db = new Database();

    @Test
    public void addsLocation() {

        //
        assertEquals(db.getLocationList().size(), 0);
        Location loc1 = new Location("Polly's place");
        db.addLocation(loc1);
        assertEquals(db.getLocationList().size(), 1);

        //will not add a null location
        db.addLocation(null);
        assertEquals(db.getLocationList().size(), 1);

        //will not add a duplicate
        db.addLocation(new Location("Polly's place"));
        assertEquals(db.getLocationList().size(), 1);
    }
}