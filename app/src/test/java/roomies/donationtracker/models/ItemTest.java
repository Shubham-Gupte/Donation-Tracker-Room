package roomies.donationtracker.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

