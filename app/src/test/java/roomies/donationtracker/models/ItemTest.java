package roomies.donationtracker.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
/**
 * Test that focuses on setting up items.
 *
 * @author Will Hay
 * @version 1.0
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ItemTest {

    Item item = new Item ("Name", "Type", 12.1, "00/00/0000",
            "Location");

    @Test(expected = IllegalArgumentException.class)
    public void setDonationDateIncorrectFormat() {
        item.setDonationDate("12-12-12");
    }

    @Test
    public void setDonationDate() {
        String date = "12/12/2012";
        item.setDonationDate(date);
        assertEquals(date, item.getDonationDate());
    }
}

