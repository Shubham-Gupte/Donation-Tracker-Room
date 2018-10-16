package roomies.donationtracker;

public class Item {
    private String type; //enum later
    private float cost;
    private String donationDate;
    private String donationLocation;

    public Item (String type, float cost, String donationDate, String donationLocation) {
        this.type = type;
        this.cost = cost;
        this.donationDate = donationDate;
        this.donationLocation = donationLocation;
    }
}
